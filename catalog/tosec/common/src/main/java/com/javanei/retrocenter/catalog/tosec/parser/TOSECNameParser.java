package com.javanei.retrocenter.catalog.tosec.parser;

import com.javanei.retrocenter.catalog.tosec.common.TOSECGame;
import com.javanei.retrocenter.catalog.tosec.parser.flags.CopyrightStatusFlagEnum;
import com.javanei.retrocenter.catalog.tosec.parser.flags.CountryFlagEnum;
import com.javanei.retrocenter.catalog.tosec.parser.flags.DateFlag;
import com.javanei.retrocenter.catalog.tosec.parser.flags.DemoFlagEnum;
import com.javanei.retrocenter.catalog.tosec.parser.flags.DevelopmentStatusFlagEnum;
import com.javanei.retrocenter.catalog.tosec.parser.flags.DumpInfoFlag;
import com.javanei.retrocenter.catalog.tosec.parser.flags.LanguageFlagEnum;
import com.javanei.retrocenter.catalog.tosec.parser.flags.MediaLabelFlag;
import com.javanei.retrocenter.catalog.tosec.parser.flags.MediaTypeFlag;
import com.javanei.retrocenter.catalog.tosec.parser.flags.PublisherFlag;
import com.javanei.retrocenter.catalog.tosec.parser.flags.SystemFlagEnum;
import com.javanei.retrocenter.catalog.tosec.parser.flags.TagValue;
import com.javanei.retrocenter.catalog.tosec.parser.flags.VersionFlag;
import com.javanei.retrocenter.catalog.tosec.parser.flags.VideoFlagEnum;
import java.util.LinkedList;
import java.util.List;

public class TOSECNameParser {
    //Title version (demo) (date)(publisher)(system)(video)(country)(language)(copyright)(devstatus)(media type)(media label)[cr][f][h][m][p][t][tr][o][u][v][b][a][!][more info]
    private static List<TagValue> parseNameInTags(String name) {
        List<TagValue> r = new LinkedList<>();

        StringBuilder sb = new StringBuilder();
        char[] separators = null;
        for (char c : name.toCharArray()) {
            switch (c) {
                case '(':
                    if (separators != null) {
                        sb.append(c);
                    } else {
                        if (sb.toString().trim().length() > 0) {
                            r.add(new TagValue(sb.toString().trim()));
                        }
                        separators = new char[]{'(', ')'};
                        sb = new StringBuilder();
                    }
                    break;
                case ')':
                    if (separators != null && separators[1] != ')') {
                        sb.append(c);
                    } else {
                        if (sb.toString().trim().length() > 0) {
                            r.add(new TagValue(sb.toString().trim(), separators));
                        }
                        sb = new StringBuilder();
                        separators = null;
                    }
                    break;
                case '[':
                    if (separators != null) {
                        sb.append(c);
                    } else {
                        if (sb.toString().trim().length() > 0) {
                            r.add(new TagValue(sb.toString().trim()));
                        }
                        separators = new char[]{'[', ']'};
                        sb = new StringBuilder();
                    }
                    break;
                case ']':
                    if (separators != null && separators[1] != ']') {
                        sb.append(c);
                    } else {
                        if (sb.toString().trim().length() > 0) {
                            r.add(new TagValue(sb.toString().trim(), separators));
                        }
                        sb = new StringBuilder();
                        separators = null;
                    }
                    break;
                default:
                    sb.append(c);
                    break;
            }
        }
        if (sb.toString().trim().length() > 0)
            r.add(new TagValue(sb.toString().trim(), separators));
        return r;
    }

    private static boolean parseReleaseDate(TOSECGame game, String tag) {
        if (game.getReleaseDate() != null && !game.getReleaseDate().isEmpty()) {
            return false;
        }
        String date = DateFlag.parseDate(tag);
        if (date != null) {
            game.setReleaseDate(date);
            return true;
        }
        return false;
    }

    public TOSECGame parseName(String name) {
        TOSECGame game = new TOSECGame(name);

        List<TagValue> tags = parseNameInTags(name);

        StringBuilder mainName = new StringBuilder();
        mainName.append(tags.get(0));

        int pos = 1;
        while (pos < tags.size()) {
            // Trata a informação de Demo e Release date
            String tag = tags.get(pos).getValue().trim();
            DemoFlagEnum demoStatus = DemoFlagEnum.fromName(tag);
            if (demoStatus != null) {
                game.setDemoStatus(demoStatus.getName());
                pos++;
                continue;
            } else {
                if (parseReleaseDate(game, tag)) {
                    pos++;
                    break;
                }
            }
            mainName.append(" ").append(tag);
            pos++;
        }
        game.setMainName(mainName.toString());

        VersionFlag versionFlag = VersionFlag.parseVersion(game.getMainName());
        if (versionFlag != null) {
            game.setMainName(versionFlag.getMainName());
            game.setVersion(versionFlag.getVersion());
        }

        if (pos < tags.size()) {
            // A próxima tag é o publisher
            String tag = tags.get(pos).getValue().trim();
            List<PublisherFlag> pubs = PublisherFlag.parsePublishers(tag);
            if (tag != null) {
                game.setPublishers(PublisherFlag.toStringList(pubs));
            }
            pos++;
        }


        while (pos < tags.size()) {
            TagValue tag = tags.get(pos);
            TagValue nextTag = pos < (tags.size() - 1) ? tags.get(pos + 1) : null;
            validate_block:
            {
                if (tag.getStartSeparator() == '(') {
                    SystemFlagEnum sys = SystemFlagEnum.fromName(tag.getValue().trim());
                    if (sys != null) {
                        game.setSystem(sys.getName());
                        break validate_block;
                    }
                    VideoFlagEnum video = VideoFlagEnum.fromName(tag.getValue().trim());
                    if (video != null) {
                        game.setVideo(video.getName());
                        break validate_block;
                    }
                    List<CountryFlagEnum> countries = CountryFlagEnum.fromName(tag.getValue().trim());
                    if (countries != null) {
                        for (CountryFlagEnum c : countries) {
                            game.addRegion(c.getName());
                        }
                        break validate_block;
                    }
                    List<LanguageFlagEnum> languages = LanguageFlagEnum.fromName(tag.getValue().trim());
                    if (languages != null) {
                        for (LanguageFlagEnum l : languages) {
                            game.addLanguage(l.getName());
                        }
                        break validate_block;
                    }
                    CopyrightStatusFlagEnum copyright = CopyrightStatusFlagEnum.fromName(tag.getValue().trim());
                    if (copyright != null) {
                        game.setCopyright(copyright.getName());
                        break validate_block;
                    }
                    DevelopmentStatusFlagEnum devStatus = DevelopmentStatusFlagEnum.fromName(tag.getValue().trim());
                    if (devStatus != null) {
                        game.setDevStatus(devStatus.getName());
                        break validate_block;
                    }
                    MediaTypeFlag mediaType = MediaTypeFlag.parseMediaType(tag.getValue().trim());
                    if (mediaType != null) {
                        game.setMediaType(mediaType.getName());
                        break validate_block;
                    }
                    if (nextTag == null || nextTag.getStartSeparator() != '(') {
                        // Media label é a última string
                        if (game.getMediaType() != null) {
                            game.setMediaLabel(tag.getValue().trim());
                        } else {
                            String mediaLabel = MediaLabelFlag.parseMediaLabel(tag.getValue());
                            if (mediaLabel != null) {
                                game.setMediaLabel(mediaLabel);
                                break validate_block;
                            }
                        }
                    }
                    game.addComplement(tag.getValue().trim());
                } else {
                    String s = tag.getValue();
                    DumpInfoFlag flag = DumpInfoFlag.parseInfo(s);
                    if (flag != null) {
                        game.addDumpStatus(flag.getDumpInfo(), flag.getValue());
                    } else {
                        game.addComplement(tag.getValue().trim());
                    }
                }
            }
            pos++;
        }

        return game;
    }
}
