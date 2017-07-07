package com.javanei.retrocenter.catalog.tosec.parser.flags;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * VersionFlag information is considered an extension of the filename.
 * It should be included in all cases where it is known and verified.
 * There are no parentheses involved, and the format should (generally) be "v x.yy ", with x being the major,
 * and yy the minor revision.
 * If the program uses a different approach, then this is what should be used, e.g. "Rev x", "vYYYYMMDD "etc.
 * <p>
 * • Legend of TOSEC, The v1.0
 * • Legend of TOSEC, The v1.03b
 * • Legend of TOSEC, The Rev 1
 * • Legend of TOSEC, The v20000101
 */
public class VersionFlag {
    private static final String[] versionPatterns = new String[]{
            "v\\d\\.\\d\\d",
            "v\\d\\d\\d\\d\\d\\d\\d\\d",
            "v\\d\\.\\d\\w",
            "v\\d\\.\\d",
            "v\\d",
            "Rev \\d"
    };
    private String mainName;
    private String version;

    private VersionFlag(String mainName, String version) {
        this.mainName = mainName;
        this.version = version;
    }

    public static VersionFlag parseVersion(String tag) {
        VersionFlag r = null;

        version_block:
        for (String smatch : versionPatterns) {
            Pattern pattern = Pattern.compile(smatch);
            Matcher matcher = pattern.matcher(tag);
            while (matcher.find()) {
                String version = matcher.group();
                if (tag.endsWith(version)) {
                    String mn = tag.substring(0, matcher.start()).trim();
                    r = new VersionFlag(mn, version);
                    break version_block;
                }
            }
        }
        return r;
    }

    public String getMainName() {
        return mainName;
    }

    public String getVersion() {
        return version;
    }
}
