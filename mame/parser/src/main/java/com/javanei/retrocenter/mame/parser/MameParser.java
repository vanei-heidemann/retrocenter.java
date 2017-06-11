package com.javanei.retrocenter.mame.parser;

import com.javanei.retrocenter.mame.Mame;
import com.javanei.retrocenter.mame.MameAdjuster;
import com.javanei.retrocenter.mame.MameAnalog;
import com.javanei.retrocenter.mame.MameBiosset;
import com.javanei.retrocenter.mame.MameChip;
import com.javanei.retrocenter.mame.MameConfiguration;
import com.javanei.retrocenter.mame.MameConfsetting;
import com.javanei.retrocenter.mame.MameDevice;
import com.javanei.retrocenter.mame.MameDeviceExtension;
import com.javanei.retrocenter.mame.MameDeviceInstance;
import com.javanei.retrocenter.mame.MameDeviceref;
import com.javanei.retrocenter.mame.MameDipswitch;
import com.javanei.retrocenter.mame.MameDipvalue;
import com.javanei.retrocenter.mame.MameDisk;
import com.javanei.retrocenter.mame.MameDisplay;
import com.javanei.retrocenter.mame.MameDriver;
import com.javanei.retrocenter.mame.MameInput;
import com.javanei.retrocenter.mame.MameInputControl;
import com.javanei.retrocenter.mame.MameMachine;
import com.javanei.retrocenter.mame.MamePort;
import com.javanei.retrocenter.mame.MameRamoption;
import com.javanei.retrocenter.mame.MameRom;
import com.javanei.retrocenter.mame.MameSample;
import com.javanei.retrocenter.mame.MameSlot;
import com.javanei.retrocenter.mame.MameSlotoption;
import com.javanei.retrocenter.mame.MameSoftwarelist;
import com.javanei.retrocenter.mame.MameSound;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class MameParser {
    private static MameBiosset parseBiosset(Node node) {
        MameBiosset biosset = new MameBiosset();
        NamedNodeMap attrs = node.getAttributes();
        if (attrs != null) {
            if (attrs.getNamedItem("name") != null)
                biosset.setName(attrs.getNamedItem("name").getNodeValue());
            if (attrs.getNamedItem("description") != null)
                biosset.setDescription(attrs.getNamedItem("description").getNodeValue());
            if (attrs.getNamedItem("default") != null)
                biosset.setDefault(attrs.getNamedItem("default").getNodeValue());
        }
        return biosset;
    }

    public Mame parse(File file) throws Exception {
        try (FileInputStream is = new FileInputStream(file)) {
            return parse(is);
        }
    }

    public Mame parse(InputStream is) throws Exception {
        Mame mame = new Mame();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setIgnoringComments(true);
        factory.setValidating(false);
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(is);
        NodeList nodes = doc.getChildNodes();

        for (int i = 0; i < nodes.getLength(); i++) {
            Node nMame = nodes.item(i);
            if (nMame.getChildNodes().getLength() == 0)
                continue;
            NamedNodeMap attrs = nMame.getAttributes();
            if (attrs != null) {
                if (attrs.getNamedItem("build") != null) {
                    mame.setBuild(attrs.getNamedItem("build").getNodeValue());
                }
                if (attrs.getNamedItem("debug") != null)
                    mame.setDebug(attrs.getNamedItem("debug").getNodeValue());
                if (attrs.getNamedItem("mameconfig") != null)
                    mame.setMameconfig(attrs.getNamedItem("mameconfig").getNodeValue());
            }
            NodeList mameChildList = nMame.getChildNodes();
            for (int j = 0; j < mameChildList.getLength(); j++) {
                Node machineNode = mameChildList.item(j);
                if (machineNode.getNodeName().equals("machine")) {
                    MameMachine machine = new MameMachine();
                    mame.addMachine(machine);
                    attrs = machineNode.getAttributes();
                    if (attrs != null) {
                        for (int k = 0; k < attrs.getLength(); k++) {
                            Node node = attrs.item(k);
                            if (node != null && node.getNodeName() != null) {
                                switch (node.getNodeName()) {
                                    case "name":
                                        machine.setName(node.getNodeValue());
                                        break;
                                    case "sourcefile":
                                        machine.setSourcefile(node.getNodeValue());
                                        break;
                                    case "isbios":
                                        machine.setIsbios(node.getNodeValue());
                                        break;
                                    case "isdevice":
                                        machine.setIsdevice(node.getNodeValue());
                                        break;
                                    case "ismechanical":
                                        machine.setIsmechanical(node.getNodeValue());
                                        break;
                                    case "runnable":
                                        machine.setRunnable(node.getNodeValue());
                                        break;
                                    case "cloneof":
                                        machine.setCloneof(node.getNodeValue());
                                        break;
                                    case "romof":
                                        machine.setRomof(node.getNodeValue());
                                        break;
                                    case "sampleof":
                                        machine.setSampleof(node.getNodeValue());
                                        break;
                                    case "#text":
                                        break;
                                    default:
                                        throw new IllegalArgumentException("Unknown Attribute: " + node.getNodeName());
                                }
                            }
                        }
                    }

                    NodeList machineChildNodes = machineNode.getChildNodes();
                    for (int k = 0; k < machineChildNodes.getLength(); k++) {
                        Node machineChild = machineChildNodes.item(k);
                        if (machineChild.getNodeName() != null) {
                            switch (machineChild.getNodeName()) {
                                case "description":
                                    machine.setDescription(machineChild.getTextContent().trim());
                                    break;
                                case "year":
                                    machine.setYear(machineChild.getTextContent().trim());
                                    break;
                                case "manufacturer":
                                    machine.setManufacturer(machineChild.getTextContent().trim());
                                    break;
                                case "biosset":
                                    machine.addBiosset(parseBiosset(machineChild));
                                    break;
                                case "rom":
                                    machine.addRom(parseRom(machineChild));
                                    break;
                                case "disk":
                                    machine.addDisk(parseDisk(machineChild));
                                    break;
                                case "device_ref":
                                    machine.addDeviceref(parseDeviceref(machineChild));
                                    break;
                                case "sample":
                                    machine.addSample(parseSample(machineChild));
                                    break;
                                case "chip":
                                    machine.addChip(parseChip(machineChild));
                                    break;
                                case "display":
                                    machine.addDisplay(parseDisplay(machineChild));
                                    break;
                                case "sound":
                                    machine.setSound(parseSound(machineChild));
                                    break;
                                case "input":
                                    machine.setInput(parseInput(machineChild));
                                    break;
                                case "dipswitch":
                                    machine.addDipswitch(parseDipswitch(machineChild));
                                    break;
                                case "configuration":
                                    machine.addConfiguration(parseConfiguration(machineChild));
                                    break;
                                case "port":
                                    machine.addPort(parsePort(machineChild));
                                    break;
                                case "adjuster":
                                    machine.addAdjuster(parseAdjuster(machineChild));
                                    break;
                                case "driver":
                                    machine.setDriver(parseDriver(machineChild));
                                    break;
                                case "device":
                                    machine.addDevice(parseDevice(machineChild));
                                    break;
                                case "slot":
                                    machine.addSlot(parseSlot(machineChild));
                                    break;
                                case "softwarelist":
                                    machine.addSoftwarelist(parseSoftwarelist(machineChild));
                                    break;
                                case "ramoption":
                                    machine.addRamoption(parseRamoption(machineChild));
                                    break;
                                case "#text":
                                    // Espaço em branco no xml
                                    break;
                                default:
                                    throw new IllegalArgumentException("Unknown Machine Node: " + machineChild.getNodeName() + "->[" + machineChild.getTextContent() + "]");
                            }
                        }
                    }
                }
            }
        }
        return mame;
    }

    private MameRom parseRom(Node node) throws Exception {
        MameRom rom = new MameRom();
        NamedNodeMap attrs = node.getAttributes();
        for (int i = 0; i < attrs.getLength(); i++) {
            Node n = attrs.item(i);
            String name = n.getNodeName();
            String value = n.getNodeValue();
            switch (name) {
                case "name":
                    rom.setName(value.trim());
                    break;
                case "bios":
                    rom.setBios(value);
                    break;
                case "size":
                    rom.setSize(value.trim());
                    break;
                case "crc":
                    rom.setCrc(value.trim());
                    break;
                case "sha1":
                    rom.setSha1(value.trim());
                    break;
                case "merge":
                    rom.setMerge(value);
                    break;
                case "region":
                    rom.setRegion(value);
                    break;
                case "offset":
                    rom.setOffset(value);
                    break;
                case "status":
                    rom.setStatus(value.trim());
                    break;
                case "optional":
                    rom.setOptional(value);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown Attribute: " + node.getNodeName());
            }
        }
        return rom;
    }

    private MameDisk parseDisk(Node node) throws Exception {
        MameDisk disk = new MameDisk();
        NamedNodeMap attrs = node.getAttributes();
        for (int i = 0; i < attrs.getLength(); i++) {
            Node n = attrs.item(i);
            String name = n.getNodeName();
            String value = n.getNodeValue();
            switch (name) {
                case "name":
                    disk.setName(value.trim());
                    break;
                case "sha1":
                    disk.setSha1(value.trim());
                    break;
                case "merge":
                    disk.setMerge(value);
                    break;
                case "region":
                    disk.setRegion(value);
                    break;
                case "index":
                    disk.setIndex(value);
                    break;
                case "writable":
                    disk.setWritable(value);
                    break;
                case "status":
                    disk.setStatus(value.trim());
                    break;
                case "optional":
                    disk.setOptional(value);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown Disk Attribute: " + name + "=" + value);
            }
        }
        return disk;
    }

    private MameDeviceref parseDeviceref(Node node) {
        MameDeviceref ref = new MameDeviceref();
        NamedNodeMap attrs = node.getAttributes();
        for (int i = 0; i < attrs.getLength(); i++) {
            Node n = attrs.item(i);
            String name = n.getNodeName();
            String value = n.getNodeValue();
            switch (name) {
                case "name":
                    ref.setName(value);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown Deviceref Attribute: " + name + "=" + value);
            }
        }
        return ref;
    }

    private MameSample parseSample(Node node) {
        MameSample sample = new MameSample();
        NamedNodeMap attrs = node.getAttributes();
        for (int i = 0; i < attrs.getLength(); i++) {
            Node n = attrs.item(i);
            String name = n.getNodeName();
            String value = n.getNodeValue();
            switch (name) {
                case "name":
                    sample.setName(value);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown Sample Attribute: " + name + "=" + value);
            }
        }
        return sample;
    }

    private MameChip parseChip(Node node) {
        MameChip chip = new MameChip();
        NamedNodeMap attrs = node.getAttributes();
        for (int i = 0; i < attrs.getLength(); i++) {
            Node n = attrs.item(i);
            String name = n.getNodeName();
            String value = n.getNodeValue();
            switch (name) {
                case "name":
                    chip.setName(value);
                    break;
                case "tag":
                    chip.setTag(value);
                    break;
                case "type":
                    chip.setType(value);
                    break;
                case "clock":
                    chip.setClock(value);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown Chip Attribute: " + name + "=" + value);
            }
        }
        return chip;
    }

    private MameDisplay parseDisplay(Node node) {
        MameDisplay display = new MameDisplay();
        NamedNodeMap attrs = node.getAttributes();
        for (int i = 0; i < attrs.getLength(); i++) {
            Node n = attrs.item(i);
            String name = n.getNodeName();
            String value = n.getNodeValue();
            switch (name) {
                case "tag":
                    display.setTag(value);
                    break;
                case "type":
                    display.setType(value);
                    break;
                case "rotate":
                    display.setRotate(value);
                    break;
                case "flipx":
                    display.setFlipx(value);
                    break;
                case "width":
                    display.setWidth(value);
                    break;
                case "height":
                    display.setHeight(value);
                    break;
                case "refresh":
                    display.setRefresh(value);
                    break;
                case "pixclock":
                    display.setPixclock(value);
                    break;
                case "htotal":
                    display.setHtotal(value);
                    break;
                case "hbend":
                    display.setHbend(value);
                    break;
                case "hbstart":
                    display.setHbstart(value);
                    break;
                case "vtotal":
                    display.setVtotal(value);
                    break;
                case "vbend":
                    display.setVbend(value);
                    break;
                case "vbstart":
                    display.setVbstart(value);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown Display Attribute: " + name + "=" + value);
            }
        }
        return display;
    }

    private MameSound parseSound(Node node) {
        MameSound sound = new MameSound();
        NamedNodeMap attrs = node.getAttributes();
        for (int i = 0; i < attrs.getLength(); i++) {
            Node n = attrs.item(i);
            String name = n.getNodeName();
            String value = n.getNodeValue();
            switch (name) {
                case "channels":
                    sound.setChannels(value);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown Sound Attribute: " + name + "=" + value);
            }
        }
        return sound;
    }

    private MameInput parseInput(Node node) {
        MameInput input = new MameInput();
        NamedNodeMap attrs = node.getAttributes();
        for (int i = 0; i < attrs.getLength(); i++) {
            Node n = attrs.item(i);
            String name = n.getNodeName();
            String value = n.getNodeValue();
            switch (name) {
                case "service":
                    input.setService(value);
                    break;
                case "tilt":
                    input.setTilt(value);
                    break;
                case "players":
                    input.setPlayers(value);
                    break;
                case "coins":
                    input.setCoins(value);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown Input Attribute: " + name + "=" + value);
            }
        }
        NodeList list = node.getChildNodes();
        for (int i = 0; i < list.getLength(); i++) {
            Node child = list.item(i);
            if (child.getNodeName() != null) {
                if (child.getNodeName().equals("control")) {
                    input.addControl(parseInputControl(child));
                } else if (child.getNodeName().equals("#text")) {
                } else {
                    throw new IllegalArgumentException("Unknown Input Node: " + child.getNodeName());
                }
            }
        }
        return input;
    }

    private MameInputControl parseInputControl(Node node) {
        MameInputControl control = new MameInputControl();
        NamedNodeMap attrs = node.getAttributes();
        for (int i = 0; i < attrs.getLength(); i++) {
            Node n = attrs.item(i);
            String name = n.getNodeName();
            String value = n.getNodeValue();
            switch (name) {
                case "type":
                    control.setType(value);
                    break;
                case "player":
                    control.setPlayer(value);
                    break;
                case "buttons":
                    control.setButtons(value);
                    break;
                case "minimum":
                    control.setMinimum(value);
                    break;
                case "maximum":
                    control.setMaximum(value);
                    break;
                case "sensitivity":
                    control.setSensitivity(value);
                    break;
                case "keydelta":
                    control.setKeydelta(value);
                    break;
                case "reverse":
                    control.setReverse(value);
                    break;
                case "ways":
                    control.setWays(value);
                    break;
                case "ways2":
                    control.setWays2(value);
                    break;
                case "ways3":
                    control.setWays3(value);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown Control Attribute: " + name + "=" + value);
            }
        }
        return control;
    }

    private MameDipswitch parseDipswitch(Node node) {
        MameDipswitch dipswitch = new MameDipswitch();
        NamedNodeMap attrs = node.getAttributes();
        for (int i = 0; i < attrs.getLength(); i++) {
            Node n = attrs.item(i);
            String name = n.getNodeName();
            String value = n.getNodeValue();
            switch (name) {
                case "name":
                    dipswitch.setName(value);
                    break;
                case "tag":
                    dipswitch.setTag(value);
                    break;
                case "mask":
                    dipswitch.setMask(value);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown Dipswitch Attribute: " + name + "=" + value);
            }
        }
        NodeList list = node.getChildNodes();
        for (int i = 0; i < list.getLength(); i++) {
            Node child = list.item(i);
            if (child.getNodeName() != null) {
                if (child.getNodeName().equals("dipvalue")) {
                    dipswitch.addDipvalue(parseDipvalue(child));
                } else if (child.getNodeName().equals("#text")) {
                } else {
                    throw new IllegalArgumentException("Unknown Unknown Dipswitch Node: " + child.getNodeName() + "->[" + child.getTextContent() + "]");
                }
            }
        }
        return dipswitch;
    }

    private MameDipvalue parseDipvalue(Node node) {
        MameDipvalue dipvalue = new MameDipvalue();
        NamedNodeMap attrs = node.getAttributes();
        for (int i = 0; i < attrs.getLength(); i++) {
            Node n = attrs.item(i);
            String name = n.getNodeName();
            String value = n.getNodeValue();
            switch (name) {
                case "name":
                    dipvalue.setName(value);
                    break;
                case "value":
                    dipvalue.setValue(value);
                    break;
                case "default":
                    dipvalue.setDefault(value);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown Dipvalue Attribute: " + name + "=" + value);
            }
        }
        return dipvalue;
    }

    private MameConfiguration parseConfiguration(Node node) {
        MameConfiguration configuration = new MameConfiguration();
        NamedNodeMap attrs = node.getAttributes();
        for (int i = 0; i < attrs.getLength(); i++) {
            Node n = attrs.item(i);
            String name = n.getNodeName();
            String value = n.getNodeValue();
            switch (name) {
                case "name":
                    configuration.setName(value);
                    break;
                case "tag":
                    configuration.setTag(value);
                    break;
                case "mask":
                    configuration.setMask(value);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown Configuration Attribute: " + name + "=" + value);
            }
        }
        NodeList list = node.getChildNodes();
        for (int i = 0; i < list.getLength(); i++) {
            Node child = list.item(i);
            if (child.getNodeName() != null) {
                if (child.getNodeName().equals("confsetting")) {
                    configuration.addConfsetting(parseConfsetting(child));
                } else if (child.getNodeName().equals("#text")) {
                } else {
                    throw new IllegalArgumentException("Unknown Configuration Node: " + child.getNodeName() + "->[" + child.getTextContent() + "]");
                }
            }
        }
        return configuration;
    }

    private MameConfsetting parseConfsetting(Node node) {
        MameConfsetting confsetting = new MameConfsetting();
        NamedNodeMap attrs = node.getAttributes();
        for (int i = 0; i < attrs.getLength(); i++) {
            Node n = attrs.item(i);
            String name = n.getNodeName();
            String value = n.getNodeValue();
            switch (name) {
                case "name":
                    confsetting.setName(value);
                    break;
                case "value":
                    confsetting.setValue(value);
                    break;
                case "default":
                    confsetting.setDefault(value);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown Confsetting Attribute: " + name + "=" + value);
            }
        }
        return confsetting;
    }

    private MamePort parsePort(Node node) {
        MamePort port = new MamePort();
        NamedNodeMap attrs = node.getAttributes();
        for (int i = 0; i < attrs.getLength(); i++) {
            Node n = attrs.item(i);
            String name = n.getNodeName();
            String value = n.getNodeValue();
            switch (name) {
                case "tag":
                    port.setTag(value);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown Port Attribute: " + name + "=" + value);
            }
        }
        NodeList list = node.getChildNodes();
        for (int i = 0; i < list.getLength(); i++) {
            Node child = list.item(i);
            if (child.getNodeName() != null) {
                if (child.getNodeName().equals("analog")) {
                    port.addAnalog(parseAnalog(child));
                } else if (child.getNodeName().equals("#text")) {
                } else {
                    throw new IllegalArgumentException("Unknown Port Node: " + child.getNodeName());
                }
            }
        }
        return port;
    }

    private MameAnalog parseAnalog(Node node) {
        MameAnalog analog = new MameAnalog();
        NamedNodeMap attrs = node.getAttributes();
        for (int i = 0; i < attrs.getLength(); i++) {
            Node n = attrs.item(i);
            String name = n.getNodeName();
            String value = n.getNodeValue();
            switch (name) {
                case "mask":
                    analog.setMask(value);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown Analog Attribute: " + name + "=" + value);
            }
        }
        return analog;
    }

    private MameAdjuster parseAdjuster(Node node) {
        MameAdjuster adjuster = new MameAdjuster();
        NamedNodeMap attrs = node.getAttributes();
        for (int i = 0; i < attrs.getLength(); i++) {
            Node n = attrs.item(i);
            String name = n.getNodeName();
            String value = n.getNodeValue();
            switch (name) {
                case "name":
                    adjuster.setName(value);
                    break;
                case "default":
                    adjuster.setDefault(value);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown Adjuster Attribute: " + name + "=" + value);
            }
        }
        return adjuster;
    }

    private MameDriver parseDriver(Node node) {
        MameDriver driver = new MameDriver();
        NamedNodeMap attrs = node.getAttributes();
        for (int i = 0; i < attrs.getLength(); i++) {
            Node n = attrs.item(i);
            String name = n.getNodeName();
            String value = n.getNodeValue();
            switch (name) {
                case "status":
                    driver.setStatus(value);
                    break;
                case "emulation":
                    driver.setEmulation(value);
                    break;
                case "color":
                    driver.setColor(value);
                    break;
                case "sound":
                    driver.setSound(value);
                    break;
                case "graphic":
                    driver.setGraphic(value);
                    break;
                case "cocktail":
                    driver.setCocktail(value);
                    break;
                case "protection":
                    driver.setProtection(value);
                    break;
                case "savestate":
                    driver.setSavestate(value);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown Driver Attribute: " + name + "=" + value);
            }
        }
        return driver;
    }

    private MameDevice parseDevice(Node node) {
        MameDevice device = new MameDevice();
        NamedNodeMap attrs = node.getAttributes();
        for (int i = 0; i < attrs.getLength(); i++) {
            Node n = attrs.item(i);
            String name = n.getNodeName();
            String value = n.getNodeValue();
            switch (name) {
                case "type":
                    device.setType(value);
                    break;
                case "tag":
                    device.setTag(value);
                    break;
                case "fixed_image":
                    device.setFixed_image(value);
                    break;
                case "mandatory":
                    device.setMandatory(value);
                    break;
                case "interface":
                    device.setInterface(value);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown Device Attribute: " + name + "=" + value);
            }
        }
        NodeList list = node.getChildNodes();
        for (int i = 0; i < list.getLength(); i++) {
            Node child = list.item(i);
            if (child.getNodeName() != null) {
                if (child.getNodeName().equals("instance")) {
                    device.addInstance(parseDeviceInstance(child));
                } else if (child.getNodeName().equals("extension")) {
                    device.addExtension(parseDeviceExtension(child));
                } else if (child.getNodeName().equals("#text")) {
                } else {
                    throw new IllegalArgumentException("Unknown Device Tag: " + child.getNodeName());
                }
            }
        }
        return device;
    }

    private MameDeviceInstance parseDeviceInstance(Node node) {
        MameDeviceInstance instance = new MameDeviceInstance();
        NamedNodeMap attrs = node.getAttributes();
        for (int i = 0; i < attrs.getLength(); i++) {
            Node n = attrs.item(i);
            String name = n.getNodeName();
            String value = n.getNodeValue();
            switch (name) {
                case "name":
                    instance.setName(value);
                    break;
                case "briefname":
                    instance.setBriefname(value);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown Device Instance Attribute: " + name + "=" + value);
            }
        }
        return instance;
    }

    private MameDeviceExtension parseDeviceExtension(Node node) {
        MameDeviceExtension extension = new MameDeviceExtension();
        NamedNodeMap attrs = node.getAttributes();
        for (int i = 0; i < attrs.getLength(); i++) {
            Node n = attrs.item(i);
            String name = n.getNodeName();
            String value = n.getNodeValue();
            switch (name) {
                case "name":
                    extension.setName(value);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown Device Extension Attribute: " + name + "=" + value);
            }
        }
        return extension;
    }

    private MameSlot parseSlot(Node node) {
        MameSlot slot = new MameSlot();
        NamedNodeMap attrs = node.getAttributes();
        for (int i = 0; i < attrs.getLength(); i++) {
            Node n = attrs.item(i);
            String name = n.getNodeName();
            String value = n.getNodeValue();
            switch (name) {
                case "name":
                    slot.setName(value);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown Slot Attribute: " + name + "=" + value);
            }
        }
        NodeList list = node.getChildNodes();
        for (int i = 0; i < list.getLength(); i++) {
            Node child = list.item(i);
            if (child.getNodeName() != null) {
                if (child.getNodeName().equals("slotoption")) {
                    slot.addSlotoption(parseSlotoption(child));
                } else if (child.getNodeName().equals("#text")) {
                } else {
                    throw new IllegalArgumentException("Unknown Slot Node: " + child.getNodeName() + "->[" + child.getTextContent() + "]");
                }
            }
        }
        return slot;
    }

    private MameSlotoption parseSlotoption(Node node) {
        MameSlotoption slotoption = new MameSlotoption();
        NamedNodeMap attrs = node.getAttributes();
        for (int i = 0; i < attrs.getLength(); i++) {
            Node n = attrs.item(i);
            String name = n.getNodeName();
            String value = n.getNodeValue();
            switch (name) {
                case "name":
                    slotoption.setName(value);
                    break;
                case "devname":
                    slotoption.setDevname(value);
                    break;
                case "default":
                    slotoption.setDefault(value);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown Slotoption Attribute: " + name + "=" + value);
            }
        }
        return slotoption;
    }

    private MameSoftwarelist parseSoftwarelist(Node node) {
        MameSoftwarelist softwarelist = new MameSoftwarelist();
        NamedNodeMap attrs = node.getAttributes();
        for (int i = 0; i < attrs.getLength(); i++) {
            Node n = attrs.item(i);
            String name = n.getNodeName();
            String value = n.getNodeValue();
            switch (name) {
                case "name":
                    softwarelist.setName(value);
                    break;
                case "status":
                    softwarelist.setStatus(value);
                    break;
                case "filter":
                    softwarelist.setFilter(value);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown Softwarelist Attribute: " + name + "=" + value);
            }
        }
        return softwarelist;
    }

    private MameRamoption parseRamoption(Node node) {
        MameRamoption ramoption = new MameRamoption();
        ramoption.setContent(node.getTextContent().trim());
        NamedNodeMap attrs = node.getAttributes();
        for (int i = 0; i < attrs.getLength(); i++) {
            Node n = attrs.item(i);
            String name = n.getNodeName();
            String value = n.getNodeValue();
            switch (name) {
                case "default":
                    ramoption.setDefault(value);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown Ramoption Attribute: " + name + "=" + value);
            }
        }
        return ramoption;
    }
}
