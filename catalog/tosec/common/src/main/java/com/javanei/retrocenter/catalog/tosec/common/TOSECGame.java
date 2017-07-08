package com.javanei.retrocenter.catalog.tosec.common;

import com.javanei.retrocenter.catalog.tosec.parser.flags.DumpInfoFlagEnum;
import java.beans.Transient;
import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class TOSECGame implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String mainName;
    private String demoStatus;
    private String releaseDate;
    private String version;
    private List<String> publishers = new LinkedList<>();
    private Map<String, String> dumpInfo = new HashMap<>();
    private String system;
    private String video;
    private List<String> regions = new LinkedList<>();
    private List<String> languages = new LinkedList<>();
    private String copyright;
    private String devStatus;
    private String mediaType;
    private String mediaLabel;
    private List<String> complements = new LinkedList<>();

    public TOSECGame() {
    }

    public TOSECGame(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMainName() {
        return mainName;
    }

    public void setMainName(String mainName) {
        this.mainName = mainName;
    }

    public String getDemoStatus() {
        return demoStatus;
    }

    public void setDemoStatus(String demoStatus) {
        this.demoStatus = demoStatus;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<String> getPublishers() {
        return publishers;
    }

    public void setPublishers(List<String> publishers) {
        this.publishers = publishers;
    }

    public void addPublisher(String publisher) {
        this.publishers.add(publisher);
    }

    public Map<String, String> getDumpInfo() {
        return dumpInfo;
    }

    public void setDumpInfo(Map<String, String> dumpInfo) {
        this.dumpInfo = dumpInfo;
    }

    @Transient
    public boolean isCracked() {
        return getCracked() != null;
    }

    @Transient
    public String getCracked() {
        return this.dumpInfo.get(DumpInfoFlagEnum.Cracked.getName());
    }

    public void setCracked(String cracked) {
        dumpInfo.put(DumpInfoFlagEnum.Cracked.getName(), cracked);
    }

    @Transient
    public String getFixed() {
        return this.dumpInfo.get(DumpInfoFlagEnum.Fixed.getName());
    }

    @Transient
    public boolean isFixed() {
        return getFixed() != null;
    }

    public void setFixed(String fixed) {
        this.dumpInfo.put(DumpInfoFlagEnum.Fixed.getName(), fixed);
    }

    @Transient
    public String getHacked() {
        return this.dumpInfo.get(DumpInfoFlagEnum.Hacked.getName());
    }

    @Transient
    public boolean isHacked() {
        return getHacked() != null;
    }

    public void setHacked(String hacked) {
        this.dumpInfo.put(DumpInfoFlagEnum.Hacked.getName(), hacked);
    }

    @Transient
    public String getModified() {
        return this.dumpInfo.get(DumpInfoFlagEnum.Modified.getName());
    }

    @Transient
    public boolean isModified() {
        return getModified() != null;
    }

    public void setModified(String modified) {
        this.dumpInfo.put(DumpInfoFlagEnum.Modified.getName(), modified);
    }

    @Transient
    public String getPirated() {
        return this.dumpInfo.get(DumpInfoFlagEnum.Pirated.getName());
    }

    @Transient
    public boolean isPirated() {
        return getPirated() != null;
    }

    public void setPirated(String pirated) {
        this.dumpInfo.put(DumpInfoFlagEnum.Pirated.getName(), pirated);
    }

    @Transient
    public String getTrained() {
        return this.dumpInfo.get(DumpInfoFlagEnum.Trained.getName());
    }

    @Transient
    public boolean isTrained() {
        return getTrained() != null;
    }

    public void setTrained(String trained) {
        this.dumpInfo.put(DumpInfoFlagEnum.Trained.getName(), trained);
    }

    @Transient
    public String getTranslated() {
        return this.dumpInfo.get(DumpInfoFlagEnum.Translated.getName());
    }

    @Transient
    public boolean isTranslated() {
        return getTranslated() != null;
    }

    public void setTranslated(String translated) {
        this.dumpInfo.put(DumpInfoFlagEnum.Translated.getName(), translated);
    }

    @Transient
    public String getOverDump() {
        return this.dumpInfo.get(DumpInfoFlagEnum.OverDump.getName());
    }

    @Transient
    public boolean isOverDump() {
        return getOverDump() != null;
    }

    public void setOverDump(String overDump) {
        this.dumpInfo.put(DumpInfoFlagEnum.OverDump.getName(), overDump);
    }

    @Transient
    public String getUnderDump() {
        return this.dumpInfo.get(DumpInfoFlagEnum.UnderDump.getName());
    }

    @Transient
    public boolean isUnderDump() {
        return getUnderDump() != null;
    }

    public void setUnderDump(String underDump) {
        this.dumpInfo.put(DumpInfoFlagEnum.UnderDump.getName(), underDump);
    }

    @Transient
    public String getVirus() {
        return this.dumpInfo.get(DumpInfoFlagEnum.Virus.getName());
    }

    public boolean isVirus() {
        return getVirus() != null;
    }

    public void setVirus(String virus) {
        this.dumpInfo.put(DumpInfoFlagEnum.Virus.getName(), virus);
    }

    @Transient
    public String getBadDump() {
        return this.dumpInfo.get(DumpInfoFlagEnum.BadDump.getName());
    }

    @Transient
    public boolean isBadDump() {
        return getBadDump() != null;
    }

    public void setBadDump(String badDump) {
        this.dumpInfo.put(DumpInfoFlagEnum.BadDump.getName(), badDump);
    }

    @Transient
    public String getAlternate() {
        return this.dumpInfo.get(DumpInfoFlagEnum.Alternate.getName());
    }

    @Transient
    public boolean isAlternate() {
        return getAlternate() != null;
    }

    public void setAlternate(String alternate) {
        this.dumpInfo.put(DumpInfoFlagEnum.Alternate.getName(), alternate);
    }

    @Transient
    public String getVerifiedGoodDump() {
        return this.dumpInfo.get(DumpInfoFlagEnum.VerifiedGoodDump.getName());
    }

    @Transient
    public boolean isVerifiedGoodDump() {
        return getVerifiedGoodDump() != null;
    }

    public void setVerifiedGoodDump(String verifiedGoodDump) {
        this.dumpInfo.put(DumpInfoFlagEnum.VerifiedGoodDump.getName(), verifiedGoodDump);
    }

    public void addDumpStatus(DumpInfoFlagEnum flag, String value) {
        this.dumpInfo.put(flag.getName(), value);
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public List<String> getRegions() {
        return regions;
    }

    public void setRegions(List<String> regions) {
        this.regions = regions;
    }

    public void addRegion(String region) {
        this.regions.add(region);
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public void addLanguage(String language) {
        this.languages.add(language);
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getDevStatus() {
        return devStatus;
    }

    public void setDevStatus(String devStatus) {
        this.devStatus = devStatus;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getMediaLabel() {
        return mediaLabel;
    }

    public void setMediaLabel(String mediaLabel) {
        this.mediaLabel = mediaLabel;
    }

    public List<String> getComplements() {
        return complements;
    }

    public void setComplements(List<String> complements) {
        this.complements = complements;
    }

    public void addComplement(String complement) {
        this.complements.add(complement);
    }

    @Override
    public String toString() {
        return "TOSECGame{" +
                "name='" + name + '\'' +
                ", mainName='" + mainName + '\'' +
                ", demoStatus='" + demoStatus + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", version='" + version + '\'' +
                ", publishers=" + publishers +
                ", dumpInfo=" + dumpInfo +
                ", system='" + system + '\'' +
                ", video='" + video + '\'' +
                ", regions=" + regions +
                ", languages=" + languages +
                ", copyright='" + copyright + '\'' +
                ", devStatus='" + devStatus + '\'' +
                ", mediaType='" + mediaType + '\'' +
                ", mediaLabel='" + mediaLabel + '\'' +
                ", complements=" + complements +
                '}';
    }
}
