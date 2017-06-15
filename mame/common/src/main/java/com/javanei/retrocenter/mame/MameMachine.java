package com.javanei.retrocenter.mame;

import com.javanei.retrocenter.common.util.StringUtil;
import com.javanei.retrocenter.common.util.ValidValuesUtil;
import java.io.Serializable;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class MameMachine implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String sourcefile;
    private String isbios; // (yes|no) "no"
    private String isdevice; // (yes|no) "no"
    private String ismechanical; // (yes|no) "no"
    private String runnable; // (yes|no) "yes"
    private String cloneof;
    private String romof;
    private String sampleof;

    private String description;
    private String year;
    private String manufacturer;

    private Set<MameBiosset> biossets = new HashSet<>();
    private List<MameRom> roms = new LinkedList<>();
    private List<MameDisk> disks = new LinkedList<>();
    private List<MameDeviceref> devicerefs = new LinkedList<>();
    private List<MameSample> samples = new LinkedList<>();
    private List<MameChip> chips = new LinkedList<>();
    private List<MameDisplay> displays = new LinkedList<>();
    private MameSound sound;
    private MameInput input;
    private List<MameDipswitch> dipswitches = new LinkedList<>();
    private Set<MameConfiguration> configurations = new HashSet<>();
    // Há duplicado!
    private Set<MamePort> ports = new HashSet<>();
    private Set<MameAdjuster> adjusters = new HashSet<>();
    private MameDriver driver;
    private Set<MameDevice> devices = new HashSet<>();
    private Set<MameSlot> slots = new HashSet<>();
    private Set<MameSoftwarelist> softwarelists = new HashSet<>();
    private Set<MameRamoption> ramoptions = new HashSet<>();

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
        this.isbios = ValidValuesUtil.validateValue(isbios, ValidValuesUtil.YES_NO);
    }

    public String getIsdevice() {
        return isdevice;
    }

    public void setIsdevice(String isdevice) {
        this.isdevice = ValidValuesUtil.validateValue(isdevice, ValidValuesUtil.YES_NO);
    }

    public String getIsmechanical() {
        return ismechanical;
    }

    public void setIsmechanical(String ismechanical) {
        this.ismechanical = ValidValuesUtil.validateValue(ismechanical, ValidValuesUtil.YES_NO);
    }

    public String getRunnable() {
        return runnable;
    }

    public void setRunnable(String runnable) {
        this.runnable = ValidValuesUtil.validateValue(runnable, ValidValuesUtil.YES_NO);
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

    public Set<MameBiosset> getBiossets() {
        return biossets;
    }

    public void setBiossets(Set<MameBiosset> biossets) {
        this.biossets = biossets;
    }

    public boolean addBiosset(MameBiosset biosset) {
        return this.biossets.add(biosset);
    }

    public List<MameRom> getRoms() {
        return roms;
    }

    public void setRoms(List<MameRom> roms) {
        this.roms = roms;
    }

    public void addRom(MameRom rom) {
        this.roms.add(rom);
    }

    public List<MameDisk> getDisks() {
        return disks;
    }

    public void setDisks(List<MameDisk> disks) {
        this.disks = disks;
    }

    public void addDisk(MameDisk disk) {
        this.disks.add(disk);
    }

    public List<MameDeviceref> getDevicerefs() {
        return devicerefs;
    }

    public void setDevicerefs(List<MameDeviceref> devicerefs) {
        this.devicerefs = devicerefs;
    }

    public void addDeviceref(MameDeviceref deviceref) {
        this.devicerefs.add(deviceref);
    }

    public List<MameSample> getSamples() {
        return samples;
    }

    public void setSamples(List<MameSample> samples) {
        this.samples = samples;
    }

    public void addSample(MameSample sample) {
        this.samples.add(sample);
    }

    public List<MameChip> getChips() {
        return chips;
    }

    public void setChips(List<MameChip> chips) {
        this.chips = chips;
    }

    public void addChip(MameChip chip) {
        this.chips.add(chip);
    }

    public List<MameDisplay> getDisplays() {
        return displays;
    }

    public void setDisplays(List<MameDisplay> displays) {
        this.displays = displays;
    }

    public void addDisplay(MameDisplay display) {
        this.displays.add(display);
    }

    public MameSound getSound() {
        return sound;
    }

    public void setSound(MameSound sound) {
        this.sound = sound;
    }

    public MameInput getInput() {
        return input;
    }

    public void setInput(MameInput input) {
        this.input = input;
    }

    public List<MameDipswitch> getDipswitches() {
        return dipswitches;
    }

    public void setDipswitches(List<MameDipswitch> dipswitches) {
        this.dipswitches = dipswitches;
    }

    public void addDipswitch(MameDipswitch dipswitch) {
        this.dipswitches.add(dipswitch);
    }

    public Set<MameConfiguration> getConfigurations() {
        return configurations;
    }

    public void setConfigurations(Set<MameConfiguration> configurations) {
        this.configurations = configurations;
    }

    public boolean addConfiguration(MameConfiguration configuration) {
        return this.configurations.add(configuration);
    }

    public Set<MamePort> getPorts() {
        return ports;
    }

    public void setPorts(Set<MamePort> ports) {
        this.ports = ports;
    }

    public boolean addPort(MamePort port) {
        return this.ports.add(port);
    }

    public Set<MameAdjuster> getAdjusters() {
        return adjusters;
    }

    public void setAdjusters(Set<MameAdjuster> adjusters) {
        this.adjusters = adjusters;
    }

    public boolean addAdjuster(MameAdjuster adjuster) {
        return this.adjusters.add(adjuster);
    }

    public MameDriver getDriver() {
        return driver;
    }

    public void setDriver(MameDriver driver) {
        this.driver = driver;
    }

    public Set<MameDevice> getDevices() {
        return devices;
    }

    public void setDevices(Set<MameDevice> devices) {
        this.devices = devices;
    }

    public boolean addDevice(MameDevice device) {
        return this.devices.add(device);
    }

    public Set<MameSlot> getSlots() {
        return slots;
    }

    public void setSlots(Set<MameSlot> slots) {
        this.slots = slots;
    }

    public boolean addSlot(MameSlot slot) {
        return this.slots.add(slot);
    }

    public Set<MameSoftwarelist> getSoftwarelists() {
        return softwarelists;
    }

    public void setSoftwarelists(Set<MameSoftwarelist> softwarelists) {
        this.softwarelists = softwarelists;
    }

    public boolean addSoftwarelist(MameSoftwarelist softwarelist) {
        return this.softwarelists.add(softwarelist);
    }

    public Set<MameRamoption> getRamoptions() {
        return ramoptions;
    }

    public void setRamoptions(Set<MameRamoption> ramoptions) {
        this.ramoptions = ramoptions;
    }

    public boolean addRamoption(MameRamoption ramoption) {
        return this.ramoptions.add(ramoption);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        MameMachine that = (MameMachine) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\t<machine");
        if (this.name != null) {
            sb.append(" name=\"").append(this.name).append("\"");
        }
        if (this.sourcefile != null) {
            sb.append(" sourcefile=\"").append(this.sourcefile).append("\"");
        }
        if (this.isbios != null && !this.isbios.equals("no")) {
            sb.append(" isbios=\"").append(this.isbios).append("\"");
        }
        if (this.isdevice != null && !this.isdevice.equals("no")) {
            sb.append(" isdevice=\"").append(this.isdevice).append("\"");
        }
        if (this.ismechanical != null && !this.ismechanical.equals("no")) {
            sb.append(" ismechanical=\"").append(this.ismechanical).append("\"");
        }
        if (this.runnable != null && !this.runnable.equals("yes")) {
            sb.append(" runnable=\"").append(this.runnable).append("\"");
        }
        if (this.cloneof != null) {
            sb.append(" cloneof=\"").append(this.cloneof).append("\"");
        }
        if (this.romof != null) {
            sb.append(" romof=\"").append(this.romof).append("\"");
        }
        if (this.sampleof != null) {
            sb.append(" sampleof=\"").append(this.sampleof).append("\"");
        }
        sb.append(">").append(StringUtil.LINE_SEPARATOR);
        if (this.description != null) {
            sb.append("\t\t<description>").append(StringUtil.escapeXMLEntities(this.description)).append("</description>").append(StringUtil.LINE_SEPARATOR);
        }
        if (this.year != null) {
            sb.append("\t\t<year>").append(this.year).append("</year>").append(StringUtil.LINE_SEPARATOR);
        }
        if (this.manufacturer != null) {
            sb.append("\t\t<manufacturer>").append(StringUtil.escapeXMLEntities(this.manufacturer)).append("</manufacturer>").append(StringUtil.LINE_SEPARATOR);
        }

        for (MameBiosset biosset : this.biossets) {
            sb.append(biosset.toString());
        }
        for (MameRom rom : this.roms) {
            sb.append(rom.toString());
        }
        for (MameDisk disk : disks) {
            sb.append(disk.toString());
        }
        for (MameDeviceref deviceref : this.devicerefs) {
            sb.append(deviceref.toString());
        }
        for (MameSample sample : this.samples) {
            sb.append(sample.toString());
        }
        for (MameChip chip : this.chips) {
            sb.append(chip.toString());
        }
        for (MameDisplay display : this.displays) {
            sb.append(display.toString());
        }
        if (sound != null) {
            sb.append(sound.toString());
        }
        if (input != null) {
            sb.append(input.toString());
        }
        for (MameDipswitch dipswitch : this.dipswitches) {
            sb.append(dipswitch.toString());
        }
        for (MameConfiguration configuration : this.configurations) {
            sb.append(configuration.toString());
        }
        for (MamePort port : this.ports) {
            sb.append(port.toString());
        }
        for (MameAdjuster adjuster : this.adjusters) {
            sb.append(adjuster.toString());
        }
        if (driver != null) {
            sb.append(driver.toString());
        }
        for (MameDevice device : this.devices) {
            sb.append(device.toString());
        }
        for (MameSlot slot : this.slots) {
            sb.append(slot.toString());
        }
        for (MameSoftwarelist softwarelist : this.softwarelists) {
            sb.append(softwarelist.toString());
        }
        for (MameRamoption ramoption : this.ramoptions) {
            sb.append(ramoption.toString());
        }
        sb.append("\t</machine>").append(StringUtil.LINE_SEPARATOR);
        return sb.toString();
    }
}
