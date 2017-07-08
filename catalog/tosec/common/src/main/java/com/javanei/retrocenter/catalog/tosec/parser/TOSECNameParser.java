package com.javanei.retrocenter.catalog.tosec.parser;

import com.javanei.retrocenter.catalog.tosec.common.TOSECGame;
import com.javanei.retrocenter.catalog.tosec.parser.flags.TOSECCopyrightStatusFlagEnum;
import com.javanei.retrocenter.catalog.tosec.parser.flags.TOSECCountryFlagEnum;
import com.javanei.retrocenter.catalog.tosec.parser.flags.TOSECDateFlag;
import com.javanei.retrocenter.catalog.tosec.parser.flags.TOSECDemoFlagEnum;
import com.javanei.retrocenter.catalog.tosec.parser.flags.TOSECDevStatusFlagEnum;
import com.javanei.retrocenter.catalog.tosec.parser.flags.TOSECDumpInfoFlag;
import com.javanei.retrocenter.catalog.tosec.parser.flags.TOSECLanguageFlagEnum;
import com.javanei.retrocenter.catalog.tosec.parser.flags.TOSECMediaLabelFlag;
import com.javanei.retrocenter.catalog.tosec.parser.flags.TOSECMediaTypeFlag;
import com.javanei.retrocenter.catalog.tosec.parser.flags.TOSECPublisherFlag;
import com.javanei.retrocenter.catalog.tosec.parser.flags.TOSECSystemFlagEnum;
import com.javanei.retrocenter.catalog.tosec.parser.flags.TOSECVersionFlag;
import com.javanei.retrocenter.catalog.tosec.parser.flags.TOSECVideoFlagEnum;
import com.javanei.retrocenter.catalog.tosec.parser.flags.TagValue;
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
        String date = TOSECDateFlag.parseDate(tag);
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
            TOSECDemoFlagEnum demoStatus = TOSECDemoFlagEnum.fromName(tag);
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

        TOSECVersionFlag versionFlag = TOSECVersionFlag.parseVersion(game.getMainName());
        if (versionFlag != null) {
            game.setMainName(versionFlag.getMainName());
            game.setVersion(versionFlag.getVersion());
        }

        if (pos < tags.size()) {
            // A próxima tag é o publisher
            String tag = tags.get(pos).getValue().trim();
            List<TOSECPublisherFlag> pubs = TOSECPublisherFlag.parsePublishers(tag);
            if (tag != null) {
                game.setPublishers(TOSECPublisherFlag.toStringList(pubs));
            }
            pos++;
        }


        while (pos < tags.size()) {
            TagValue tag = tags.get(pos);
            TagValue nextTag = pos < (tags.size() - 1) ? tags.get(pos + 1) : null;
            validate_block:
            {
                if (tag.getStartSeparator() == '(') {
                    TOSECSystemFlagEnum sys = TOSECSystemFlagEnum.fromName(tag.getValue().trim());
                    if (sys != null) {
                        game.setSystem(sys.getName());
                        break validate_block;
                    }
                    TOSECVideoFlagEnum video = TOSECVideoFlagEnum.fromName(tag.getValue().trim());
                    if (video != null) {
                        game.setVideo(video.getName());
                        break validate_block;
                    }
                    List<TOSECCountryFlagEnum> countries = TOSECCountryFlagEnum.fromName(tag.getValue().trim());
                    if (countries != null) {
                        for (TOSECCountryFlagEnum c : countries) {
                            game.addRegion(c.getName());
                        }
                        break validate_block;
                    }
                    List<TOSECLanguageFlagEnum> languages = TOSECLanguageFlagEnum.fromName(tag.getValue().trim());
                    if (languages != null) {
                        for (TOSECLanguageFlagEnum l : languages) {
                            game.addLanguage(l.getName());
                        }
                        break validate_block;
                    }
                    TOSECCopyrightStatusFlagEnum copyright = TOSECCopyrightStatusFlagEnum.fromName(tag.getValue().trim());
                    if (copyright != null) {
                        game.setCopyright(copyright.getName());
                        break validate_block;
                    }
                    TOSECDevStatusFlagEnum devStatus = TOSECDevStatusFlagEnum.fromName(tag.getValue().trim());
                    if (devStatus != null) {
                        game.setDevStatus(devStatus.getName());
                        break validate_block;
                    }
                    TOSECMediaTypeFlag mediaType = TOSECMediaTypeFlag.parseMediaType(tag.getValue().trim());
                    if (mediaType != null) {
                        game.setMediaType(mediaType.getName());
                        break validate_block;
                    }
                    if (nextTag == null || nextTag.getStartSeparator() != '(') {
                        // Media label é a última string
                        if (game.getMediaType() != null) {
                            game.setMediaLabel(tag.getValue().trim());
                        } else {
                            String mediaLabel = TOSECMediaLabelFlag.parseMediaLabel(tag.getValue());
                            if (mediaLabel != null) {
                                game.setMediaLabel(mediaLabel);
                                break validate_block;
                            }
                        }
                    }
                    game.addComplement(tag.getValue().trim());
                } else {
                    String s = tag.getValue();
                    TOSECDumpInfoFlag flag = TOSECDumpInfoFlag.parseInfo(s);
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
