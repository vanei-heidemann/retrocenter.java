package com.javanei.retrocenter.mame.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "MAME_MACHINE", indexes = {
        @Index(name = "MAME_MACHINE_0001", unique = true, columnList = "MAME_ID,NAME")
})
public class MameMachineEntity implements Serializable, Comparable<MameMachineEntity> {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MAME_MACHINE_ID", nullable = false)
    private Long id;

    @Column(name = "NAME", length = 32, nullable = false)
    private String name;

    @Column(name = "SOURCEFILE", length = 48, nullable = false)
    private String sourcefile;

    @Column(name = "ISBIOS", length = 3, nullable = true)
    private String isbios;

    @Column(name = "ISDEVICE", length = 3, nullable = true)
    private String isdevice;

    @Column(name = "ISMECHANICAL", length = 3, nullable = true)
    private String ismechanical;

    @Column(name = "RUNNABLE", length = 3, nullable = true)
    private String runnable;

    @Column(name = "CLONEOF", length = 16, nullable = true)
    private String cloneof;

    @Column(name = "ROMOF", length = 16, nullable = true)
    private String romof;

    @Column(name = "SAMPLEOF", length = 32, nullable = true)
    private String sampleof;

    @Column(name = "DESCRIPTION", length = 160, nullable = true)
    private String description;

    @Column(name = "YEAR", length = 8, nullable = true)
    private String year;

    @Column(name = "MANUFACTURER", length = 80, nullable = true)
    private String manufacturer;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH, CascadeType.REMOVE}, mappedBy = "machine")
    private Set<MameBiossetEntity> biossets = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH, CascadeType.REMOVE}, mappedBy = "machine")
    private List<MameRomEntity> roms = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH, CascadeType.REMOVE}, mappedBy = "machine")
    private Set<MameDiskEntity> disks = new LinkedHashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH, CascadeType.REMOVE}, mappedBy = "machine")
    private List<MameDevicerefEntity> devicerefs = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH, CascadeType.REMOVE}, mappedBy = "machine")
    private List<MameSampleEntity> samples = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH, CascadeType.REMOVE}, mappedBy = "machine")
    private Set<MameChipEntity> chips = new LinkedHashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH, CascadeType.REMOVE}, mappedBy = "machine")
    private Set<MameDisplayEntity> displays = new LinkedHashSet<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH, CascadeType.REMOVE}, mappedBy = "machine", optional = true)
    private MameSoundEntity sound;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH, CascadeType.REMOVE}, mappedBy = "machine", optional = true)
    private MameInputEntity input;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH, CascadeType.REMOVE}, mappedBy = "machine")
    private List<MameDipswitchEntity> dipswitches = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH, CascadeType.REMOVE}, mappedBy = "machine")
    private Set<MameConfigurationEntity> configurations = new LinkedHashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH, CascadeType.REMOVE}, mappedBy = "machine")
    private List<MamePortEntity> ports = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH, CascadeType.REMOVE}, mappedBy = "machine")
    private Set<MameAdjusterEntity> adjusters = new LinkedHashSet<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH, CascadeType.REMOVE}, mappedBy = "machine", optional = true)
    private MameDriverEntity driver;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH, CascadeType.REMOVE}, mappedBy = "machine")
    private Set<MameDeviceEntity> devices = new LinkedHashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH, CascadeType.REMOVE}, mappedBy = "machine")
    private Set<MameSlotEntity> slots = new LinkedHashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH, CascadeType.REMOVE}, mappedBy = "machine")
    private Set<MameSoftwarelistEntity> softwarelists = new LinkedHashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH, CascadeType.REMOVE}, mappedBy = "machine")
    private Set<MameRamoptionEntity> ramoptions = new LinkedHashSet<>();

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "MAME_ID")
    private MameEntity mame;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSourcefile() {
        return sourcefile;
    }

    public void setSourcefile(String sourcefile) {
        this.sourcefile = sourcefile;
    }

    public String getIsbios() {
        return isbios;
    }

    public void setIsbios(String isbios) {
        this.isbios = isbios;
    }

    public String getIsdevice() {
        return isdevice;
    }

    public void setIsdevice(String isdevice) {
        this.isdevice = isdevice;
    }

    public String getIsmechanical() {
        return ismechanical;
    }

    public void setIsmechanical(String ismechanical) {
        this.ismechanical = ismechanical;
    }

    public String getRunnable() {
        return runnable;
    }

    public void setRunnable(String runnable) {
        this.runnable = runnable;
    }

    public String getCloneof() {
        return cloneof;
    }

    public void setCloneof(String cloneof) {
        this.cloneof = cloneof;
    }

    public String getRomof() {
        return romof;
    }

    public void setRomof(String romof) {
        this.romof = romof;
    }

    public String getSampleof() {
        return sampleof;
    }

    public void setSampleof(String sampleof) {
        this.sampleof = sampleof;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Set<MameBiossetEntity> getBiossets() {
        return biossets;
    }

    public void setBiossets(Set<MameBiossetEntity> biossets) {
        this.biossets = biossets;
    }

    public List<MameRomEntity> getRoms() {
        return roms;
    }

    public void setRoms(List<MameRomEntity> roms) {
        this.roms = roms;
    }

    public Set<MameDiskEntity> getDisks() {
        return disks;
    }

    public void setDisks(Set<MameDiskEntity> disks) {
        this.disks = disks;
    }

    public List<MameDevicerefEntity> getDevicerefs() {
        return devicerefs;
    }

    public void setDevicerefs(List<MameDevicerefEntity> devicerefs) {
        this.devicerefs = devicerefs;
    }

    public List<MameSampleEntity> getSamples() {
        return samples;
    }

    public void setSamples(List<MameSampleEntity> samples) {
        this.samples = samples;
    }

    public Set<MameChipEntity> getChips() {
        return chips;
    }

    public void setChips(Set<MameChipEntity> chips) {
        this.chips = chips;
    }

    public Set<MameDisplayEntity> getDisplays() {
        return displays;
    }

    public void setDisplays(Set<MameDisplayEntity> displays) {
        this.displays = displays;
    }

    public MameSoundEntity getSound() {
        return sound;
    }

    public void setSound(MameSoundEntity sound) {
        this.sound = sound;
    }

    public MameInputEntity getInput() {
        return input;
    }

    public void setInput(MameInputEntity input) {
        this.input = input;
    }

    public List<MameDipswitchEntity> getDipswitches() {
        return dipswitches;
    }

    public void setDipswitches(List<MameDipswitchEntity> dipswitches) {
        this.dipswitches = dipswitches;
    }

    public Set<MameConfigurationEntity> getConfigurations() {
        return configurations;
    }

    public void setConfigurations(Set<MameConfigurationEntity> configurations) {
        this.configurations = configurations;
    }

    public List<MamePortEntity> getPorts() {
        return ports;
    }

    public void setPorts(List<MamePortEntity> ports) {
        this.ports = ports;
    }

    public Set<MameAdjusterEntity> getAdjusters() {
        return adjusters;
    }

    public void setAdjusters(Set<MameAdjusterEntity> adjusters) {
        this.adjusters = adjusters;
    }

    public MameDriverEntity getDriver() {
        return driver;
    }

    public void setDriver(MameDriverEntity driver) {
        this.driver = driver;
    }

    public Set<MameDeviceEntity> getDevices() {
        return devices;
    }

    public void setDevices(Set<MameDeviceEntity> devices) {
        this.devices = devices;
    }

    public Set<MameSlotEntity> getSlots() {
        return slots;
    }

    public void setSlots(Set<MameSlotEntity> slots) {
        this.slots = slots;
    }

    public Set<MameSoftwarelistEntity> getSoftwarelists() {
        return softwarelists;
    }

    public void setSoftwarelists(Set<MameSoftwarelistEntity> softwarelists) {
        this.softwarelists = softwarelists;
    }

    public Set<MameRamoptionEntity> getRamoptions() {
        return ramoptions;
    }

    public void setRamoptions(Set<MameRamoptionEntity> ramoptions) {
        this.ramoptions = ramoptions;
    }

    public MameEntity getMame() {
        return mame;
    }

    public void setMame(MameEntity mame) {
        this.mame = mame;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        MameMachineEntity that = (MameMachineEntity) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public int compareTo(MameMachineEntity o) {
        if (this == o)
            return 0;

        int i = 0;
        if (this.id != null && o.id == null)
            i = 1;
        else if (this.id == null && o.id != null)
            i = -1;
        else if (this.id != null)
            i = this.id.compareTo(o.id);
        if (i == 0)
            i = this.name.compareTo(o.name);
        return i;
    }
}
