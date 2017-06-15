package com.javanei.retrocenter.mame.parser;

import com.javanei.retrocenter.common.DuplicatedItemException;
import com.javanei.retrocenter.common.UnknownTagException;
import com.javanei.retrocenter.common.util.ReflectionUtil;
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
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class MameParser {
    private static MameBiosset parseBiosset(Node node) {
        MameBiosset biosset = new MameBiosset();
        ReflectionUtil.setValueByAttributes(biosset, node.getAttributes());
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
            ReflectionUtil.setValueByAttributes(mame, nMame.getAttributes());
            NodeList mameChildList = nMame.getChildNodes();
            for (int j = 0; j < mameChildList.getLength(); j++) {
                Node machineNode = mameChildList.item(j);
                if (machineNode.getNodeName().equals("machine")) {
                    MameMachine machine = new MameMachine();
                    if (!mame.addMachine(machine)) {
                        throw new DuplicatedItemException("mame.machine: " + machine.getName());
                    }
                    ReflectionUtil.setValueByAttributes(machine, machineNode.getAttributes());

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
                                    MameBiosset biosset = parseBiosset(machineChild);
                                    if (!machine.addBiosset(biosset)) {
                                        throw new DuplicatedItemException("mame.machine.biosset: " + biosset.getName() + " for machine: " + machine.getName());
                                    }
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
                                    MamePort port = parsePort(machineChild);
                                    if (!machine.addPort(port)) {
                                        throw new DuplicatedItemException("mame.machine.adjuster: " + port + " for machine: " + machine.getName());
                                    }
                                    break;
                                case "adjuster":
                                    MameAdjuster adjuster = parseAdjuster(machineChild);
                                    if (!machine.addAdjuster(adjuster)) {
                                        throw new DuplicatedItemException("mame.machine.adjuster: " + adjuster + " for machine: " + machine.getName());
                                    }
                                    break;
                                case "driver":
                                    machine.setDriver(parseDriver(machineChild));
                                    break;
                                case "device":
                                    MameDevice device = parseDevice(machineChild);
                                    if (!machine.addDevice(device)) {
                                        throw new DuplicatedItemException("mame.machine.device: " + device + " for machine: " + machine.getName());
                                    }
                                    break;
                                case "slot":
                                    MameSlot slot = parseSlot(machineChild);
                                    if (!machine.addSlot(slot)) {
                                        throw new DuplicatedItemException("mame.machine.slot: " + slot + " for machine: " + machine.getName());
                                    }
                                    break;
                                case "softwarelist":
                                    MameSoftwarelist softwarelist = parseSoftwarelist(machineChild);
                                    if (!machine.addSoftwarelist(softwarelist)) {
                                        throw new DuplicatedItemException("mame.machine.softwarelist: " + softwarelist.getName() + " for machine: " + machine.getName());
                                    }
                                    break;
                                case "ramoption":
                                    MameRamoption ramoption = parseRamoption(machineChild);
                                    if (!machine.addRamoption(ramoption)) {
                                        throw new DuplicatedItemException("mame.machine.ramoption: " + ramoption.getContent() + " for machine: " + machine.getName());
                                    }
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

    private MameRom parseRom(Node node) {
        MameRom rom = new MameRom();
        ReflectionUtil.setValueByAttributes(rom, node.getAttributes());
        return rom;
    }

    private MameDisk parseDisk(Node node) {
        MameDisk disk = new MameDisk();
        ReflectionUtil.setValueByAttributes(disk, node.getAttributes());
        return disk;
    }

    private MameDeviceref parseDeviceref(Node node) {
        MameDeviceref ref = new MameDeviceref();
        ReflectionUtil.setValueByAttributes(ref, node.getAttributes());
        return ref;
    }

    private MameSample parseSample(Node node) {
        MameSample sample = new MameSample();
        ReflectionUtil.setValueByAttributes(sample, node.getAttributes());
        return sample;
    }

    private MameChip parseChip(Node node) {
        MameChip chip = new MameChip();
        ReflectionUtil.setValueByAttributes(chip, node.getAttributes());
        return chip;
    }

    private MameDisplay parseDisplay(Node node) {
        MameDisplay display = new MameDisplay();
        ReflectionUtil.setValueByAttributes(display, node.getAttributes());
        return display;
    }

    private MameSound parseSound(Node node) {
        MameSound sound = new MameSound();
        ReflectionUtil.setValueByAttributes(sound, node.getAttributes());
        return sound;
    }

    private MameInput parseInput(Node node) {
        MameInput input = new MameInput();
        ReflectionUtil.setValueByAttributes(input, node.getAttributes());
        NodeList list = node.getChildNodes();
        for (int i = 0; i < list.getLength(); i++) {
            Node child = list.item(i);
            if (child.getNodeName() != null) {
                if (child.getNodeName().equals("control")) {
                    input.addControl(parseInputControl(child));
                } else if (child.getNodeName().equals("#text")) {
                } else {
                    throw new UnknownTagException(child.getNodeName());
                }
            }
        }
        return input;
    }

    private MameInputControl parseInputControl(Node node) {
        MameInputControl control = new MameInputControl();
        ReflectionUtil.setValueByAttributes(control, node.getAttributes());
        return control;
    }

    private MameDipswitch parseDipswitch(Node node) {
        MameDipswitch dipswitch = new MameDipswitch();
        ReflectionUtil.setValueByAttributes(dipswitch, node.getAttributes());
        NodeList list = node.getChildNodes();
        for (int i = 0; i < list.getLength(); i++) {
            Node child = list.item(i);
            if (child.getNodeName() != null) {
                if (child.getNodeName().equals("dipvalue")) {
                    dipswitch.addDipvalue(parseDipvalue(child));
                } else if (child.getNodeName().equals("#text")) {
                } else {
                    throw new UnknownTagException(child.getNodeName());
                }
            }
        }
        return dipswitch;
    }

    private MameDipvalue parseDipvalue(Node node) {
        MameDipvalue dipvalue = new MameDipvalue();
        ReflectionUtil.setValueByAttributes(dipvalue, node.getAttributes());
        return dipvalue;
    }

    private MameConfiguration parseConfiguration(Node node) {
        MameConfiguration configuration = new MameConfiguration();
        ReflectionUtil.setValueByAttributes(configuration, node.getAttributes());
        NodeList list = node.getChildNodes();
        for (int i = 0; i < list.getLength(); i++) {
            Node child = list.item(i);
            if (child.getNodeName() != null) {
                if (child.getNodeName().equals("confsetting")) {
                    configuration.addConfsetting(parseConfsetting(child));
                } else if (child.getNodeName().equals("#text")) {
                } else {
                    throw new UnknownTagException(child.getNodeName());
                }
            }
        }
        return configuration;
    }

    private MameConfsetting parseConfsetting(Node node) {
        MameConfsetting confsetting = new MameConfsetting();
        ReflectionUtil.setValueByAttributes(confsetting, node.getAttributes());
        return confsetting;
    }

    private MamePort parsePort(Node node) {
        MamePort port = new MamePort();
        ReflectionUtil.setValueByAttributes(port, node.getAttributes());
        NodeList list = node.getChildNodes();
        for (int i = 0; i < list.getLength(); i++) {
            Node child = list.item(i);
            if (child.getNodeName() != null) {
                if (child.getNodeName().equals("analog")) {
                    MameAnalog analog = parseAnalog(child);
                    if (!port.addAnalog(analog)) {
                        //throw new DuplicatedItemException("mame.machine.port.analog: " + analog + " for port: " + port.getTag());
                        System.err.println("WWW Duplicated analog " + analog + " for port " + port.getTag());
                    }
                } else if (child.getNodeName().equals("#text")) {
                } else {
                    throw new UnknownTagException(child.getNodeName());
                }
            }
        }
        return port;
    }

    private MameAnalog parseAnalog(Node node) {
        MameAnalog analog = new MameAnalog();
        ReflectionUtil.setValueByAttributes(analog, node.getAttributes());
        return analog;
    }

    private MameAdjuster parseAdjuster(Node node) {
        MameAdjuster adjuster = new MameAdjuster();
        ReflectionUtil.setValueByAttributes(adjuster, node.getAttributes());
        return adjuster;
    }

    private MameDriver parseDriver(Node node) {
        MameDriver driver = new MameDriver();
        ReflectionUtil.setValueByAttributes(driver, node.getAttributes());
        return driver;
    }

    private MameDevice parseDevice(Node node) {
        MameDevice device = new MameDevice();
        ReflectionUtil.setValueByAttributes(device, node.getAttributes());
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
                    throw new UnknownTagException(child.getNodeName());
                }
            }
        }
        return device;
    }

    private MameDeviceInstance parseDeviceInstance(Node node) {
        MameDeviceInstance instance = new MameDeviceInstance();
        ReflectionUtil.setValueByAttributes(instance, node.getAttributes());
        return instance;
    }

    private MameDeviceExtension parseDeviceExtension(Node node) {
        MameDeviceExtension extension = new MameDeviceExtension();
        ReflectionUtil.setValueByAttributes(extension, node.getAttributes());
        return extension;
    }

    private MameSlot parseSlot(Node node) {
        MameSlot slot = new MameSlot();
        ReflectionUtil.setValueByAttributes(slot, node.getAttributes());
        NodeList list = node.getChildNodes();
        for (int i = 0; i < list.getLength(); i++) {
            Node child = list.item(i);
            if (child.getNodeName() != null) {
                if (child.getNodeName().equals("slotoption")) {
                    MameSlotoption slotoption = parseSlotoption(child);
                    if (!slot.addSlotoption(slotoption)) {
                        throw new DuplicatedItemException("mame.machine.slot.slotoption: " + slotoption);
                    }
                } else if (child.getNodeName().equals("#text")) {
                } else {
                    throw new UnknownTagException(child.getNodeName());
                }
            }
        }
        return slot;
    }

    private MameSlotoption parseSlotoption(Node node) {
        MameSlotoption slotoption = new MameSlotoption();
        ReflectionUtil.setValueByAttributes(slotoption, node.getAttributes());
        return slotoption;
    }

    private MameSoftwarelist parseSoftwarelist(Node node) {
        MameSoftwarelist softwarelist = new MameSoftwarelist();
        ReflectionUtil.setValueByAttributes(softwarelist, node.getAttributes());
        return softwarelist;
    }

    private MameRamoption parseRamoption(Node node) {
        MameRamoption ramoption = new MameRamoption();
        ramoption.setContent(node.getTextContent().trim());
        ReflectionUtil.setValueByAttributes(ramoption, node.getAttributes());
        return ramoption;
    }
}
