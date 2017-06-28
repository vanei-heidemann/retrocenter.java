package com.javanei.retrocenter.mame.service;

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
import com.javanei.retrocenter.mame.MameRom;
import com.javanei.retrocenter.mame.MameSample;
import com.javanei.retrocenter.mame.MameSound;
import com.javanei.retrocenter.mame.parser.MameParser;
import java.io.InputStream;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MameServiceConfiguration.class})
public class MameServiceTest {
    private static Mame datafile;
    private static MameMachine machine005;
    private static MameRom rom005;
    private static MameBiosset biosset005;
    private static MameDisk disk005;
    private static MameDeviceref deviceref005;
    private static MameSample sample005;
    private static MameChip chip005;
    private static MameDisplay display005;
    private static MameSound sound005;
    private static MameInput input005;
    private static MameInputControl inputControl005;
    private static MameDipswitch dipswitch005;
    private static MameDipvalue dipvalue005;
    private static MameConfiguration configuration005;
    private static MameConfsetting confsetting005;
    private static MamePort port005;
    private static MameAnalog analog005;
    private static MameAdjuster adjuster005;
    private static MameDriver driver005;
    private static MameDevice device005;
    private static MameDeviceInstance deviceInstance005;
    private static MameDeviceExtension deviceExtension005;

    @Autowired
    private MameService mameService;


    @BeforeClass
    public static void initialize() throws Exception {
        MameParser parser = new MameParser();

        InputStream is = MameServiceTest.class.getClassLoader().getResourceAsStream("mame-info.xml");
        datafile = parser.parse(is);
        for (MameMachine m : datafile.getMachines()) {
            if (m.getName().equals("005")) {
                machine005 = m;
                break;
            }
        }
        for (MameRom rom : machine005.getRoms()) {
            if (rom.getName().equals("1346b.cpu-u25")) {
                rom005 = rom;
                break;
            }
        }
        biosset005 = machine005.getBiossets().iterator().next();
        disk005 = machine005.getDisks().iterator().next();
        deviceref005 = machine005.getDevicerefs().get(0);
        sample005 = machine005.getSamples().get(0);
        chip005 = machine005.getChips().iterator().next();
        display005 = machine005.getDisplays().iterator().next();
        sound005 = machine005.getSound();
        input005 = machine005.getInput();
        inputControl005 = input005.getControls().iterator().next();
        dipswitch005 = machine005.getDipswitches().get(0);
        dipvalue005 = dipswitch005.getDipvalues().get(0);
        configuration005 = machine005.getConfigurations().iterator().next();
        confsetting005 = configuration005.getConfsettings().iterator().next();
        port005 = machine005.getPorts().get(0);
        analog005 = port005.getAnalogs().get(0);
        adjuster005 = machine005.getAdjusters().iterator().next();
        driver005 = machine005.getDriver();
        device005 = machine005.getDevices().iterator().next();
        deviceInstance005 = device005.getInstances().iterator().next();
        deviceExtension005 = device005.getExtensions().iterator().next();
    }

    @Test
    public void create() {
        Mame mame = mameService.create(datafile);

        Assert.assertEquals("build", datafile.getBuild(), mame.getBuild());
        Assert.assertEquals("debug", datafile.getDebug(), mame.getDebug());
        Assert.assertEquals("mameconfig", datafile.getMameconfig(), mame.getMameconfig());
    }

    @Test
    public void createMachine() {
        Mame mame = mameService.create(datafile);
        Assert.assertEquals("machines", datafile.getMachines().size(), mame.getMachines().size());

        MameMachine m = mame.getMachines().iterator().next();

        Assert.assertEquals("name", machine005.getName(), m.getName());
        Assert.assertEquals("sourcefile", machine005.getSourcefile(), m.getSourcefile());
        Assert.assertEquals("sampleof", machine005.getSampleof(), m.getSampleof());
        Assert.assertEquals("description", machine005.getDescription(), m.getDescription());
        Assert.assertEquals("isbios", machine005.getIsbios(), m.getIsbios());
        Assert.assertEquals("isdevice", machine005.getIsdevice(), m.getIsdevice());
        Assert.assertEquals("ismechanical", machine005.getIsmechanical(), m.getIsmechanical());
        Assert.assertEquals("runnable", machine005.getRunnable(), m.getRunnable());
        Assert.assertEquals("cloneof", machine005.getCloneof(), m.getCloneof());
        Assert.assertEquals("romof", machine005.getRomof(), m.getRomof());
        Assert.assertEquals("sampleof", machine005.getSampleof(), m.getSampleof());
        Assert.assertEquals("year", machine005.getYear(), m.getYear());
        Assert.assertEquals("manufacturer", machine005.getManufacturer(), m.getManufacturer());
    }

    @Test
    public void createBiosset() {
        Mame mame = mameService.create(datafile);
        MameMachine machine = mame.getMachines().iterator().next();
        MameBiosset biosset = machine.getBiossets().iterator().next();

        Assert.assertEquals("name", biosset005.getName(), biosset.getName());
        Assert.assertEquals("description", biosset005.getDescription(), biosset.getDescription());
        Assert.assertEquals("default", biosset005.getDefault(), biosset.getDefault());
    }

    @Test
    public void createRom() {
        Mame mame = mameService.create(datafile);
        MameMachine machine = mame.getMachines().iterator().next();
        MameRom rom = machine.getRoms().get(0);

        Assert.assertEquals("name", rom005.getName(), rom.getName());
        Assert.assertEquals("bios", rom005.getBios(), rom.getBios());
        Assert.assertEquals("size", rom005.getSize(), rom.getSize());
        Assert.assertEquals("crc", rom005.getCrc(), rom.getCrc());
        Assert.assertEquals("sha1;", rom005.getSha1(), rom.getSha1());
        Assert.assertEquals("merge", rom005.getMerge(), rom.getMerge());
        Assert.assertEquals("region", rom005.getRegion(), rom.getRegion());
        Assert.assertEquals("offset", rom005.getOffset(), rom.getOffset());
        Assert.assertEquals("status", rom005.getStatus(), rom.getStatus());
        Assert.assertEquals("optional", rom005.getOptional(), rom.getOptional());
    }

    @Test
    public void createDisk() {
        Mame mame = mameService.create(datafile);
        MameMachine machine = mame.getMachines().iterator().next();
        MameDisk disk = machine.getDisks().iterator().next();

        Assert.assertEquals("name", disk005.getName(), disk.getName());
        Assert.assertEquals("sha1", disk005.getSha1(), disk.getSha1());
        Assert.assertEquals("merge", disk005.getMerge(), disk.getMerge());
        Assert.assertEquals("region", disk005.getRegion(), disk.getRegion());
        Assert.assertEquals("index", disk005.getIndex(), disk.getIndex());
        Assert.assertEquals("writable", disk005.getWritable(), disk.getWritable());
        Assert.assertEquals("status", disk005.getStatus(), disk.getStatus());
        Assert.assertEquals("optional", disk005.getOptional(), disk.getOptional());
    }

    @Test
    public void createDeviceRef() {
        Mame mame = mameService.create(datafile);
        MameMachine machine = mame.getMachines().iterator().next();
        MameDeviceref deviceref = machine.getDevicerefs().get(0);

        Assert.assertEquals("name", deviceref005.getName(), deviceref.getName());
    }

    @Test
    public void createSample() {
        Mame mame = mameService.create(datafile);
        MameMachine machine = mame.getMachines().iterator().next();
        MameSample sample = machine.getSamples().get(0);

        Assert.assertEquals("name", sample005.getName(), sample.getName());
    }

    @Test
    public void createChip() {
        Mame mame = mameService.create(datafile);
        MameMachine machine = mame.getMachines().iterator().next();
        MameChip chip = machine.getChips().iterator().next();

        Assert.assertEquals("name", chip005.getName(), chip.getName());
        Assert.assertEquals("tag", chip005.getTag(), chip.getTag());
        Assert.assertEquals("type", chip005.getType(), chip.getType());
        Assert.assertEquals("clock", chip005.getClock(), chip.getClock());
    }

    @Test
    public void createDisplay() {
        Mame mame = mameService.create(datafile);
        MameMachine machine = mame.getMachines().iterator().next();
        MameDisplay display = machine.getDisplays().iterator().next();

        Assert.assertEquals("tag", display005.getTag(), display.getTag());
        Assert.assertEquals("type", display005.getType(), display.getType());
        Assert.assertEquals("rotate", display005.getRotate(), display.getRotate());
        Assert.assertEquals("flipx", display005.getFlipx(), display.getFlipx());
        Assert.assertEquals("width", display005.getWidth(), display.getWidth());
        Assert.assertEquals("height", display005.getHeight(), display.getHeight());
        Assert.assertEquals("refresh", display005.getRefresh(), display.getRefresh());
        Assert.assertEquals("pixclock", display005.getPixclock(), display.getPixclock());
        Assert.assertEquals("htotal", display005.getHtotal(), display.getHtotal());
        Assert.assertEquals("hbend", display005.getHbend(), display.getHbend());
        Assert.assertEquals("hbstart", display005.getHbstart(), display.getHbstart());
        Assert.assertEquals("vtotal", display005.getVtotal(), display.getVtotal());
        Assert.assertEquals("vbend", display005.getVbend(), display.getVbend());
        Assert.assertEquals("vbstart", display005.getVbstart(), display.getVbstart());
    }

    @Test
    public void createSound() {
        Mame mame = mameService.create(datafile);
        MameMachine machine = mame.getMachines().iterator().next();
        MameSound sound = machine.getSound();

        Assert.assertEquals("channels", sound005.getChannels(), sound.getChannels());
    }

    @Test
    public void createInput() {
        Mame mame = mameService.create(datafile);
        MameMachine machine = mame.getMachines().iterator().next();
        MameInput input = machine.getInput();

        Assert.assertEquals("service", input005.getService(), input.getService());
        Assert.assertEquals("tilt", input005.getTilt(), input.getTilt());
        Assert.assertEquals("players", input005.getPlayers(), input.getPlayers());
        Assert.assertEquals("coins", input005.getCoins(), input.getCoins());
    }

    @Test
    public void createInputControl() {
        Mame mame = mameService.create(datafile);
        MameMachine machine = mame.getMachines().iterator().next();
        MameInput input = machine.getInput();
        MameInputControl control = input.getControls().iterator().next();

        Assert.assertEquals("type", inputControl005.getType(), control.getType());

        Assert.assertEquals("player", inputControl005.getPlayer(), control.getPlayer());
        Assert.assertEquals("buttons", inputControl005.getButtons(), control.getButtons());
        Assert.assertEquals("reqbuttons", inputControl005.getReqbuttons(), control.getReqbuttons());
        Assert.assertEquals("minimum", inputControl005.getMinimum(), control.getMinimum());
        Assert.assertEquals("maximum", inputControl005.getMaximum(), control.getMaximum());
        Assert.assertEquals("sensitivity", inputControl005.getSensitivity(), control.getSensitivity());
        Assert.assertEquals("keydelta", inputControl005.getKeydelta(), control.getKeydelta());
        Assert.assertEquals("reverse", inputControl005.getReverse(), control.getReverse());
        Assert.assertEquals("ways", inputControl005.getWays(), control.getWays());
        Assert.assertEquals("ways2", inputControl005.getWays2(), control.getWays2());
        Assert.assertEquals("ways3", inputControl005.getWays3(), control.getWays3());
    }

    @Test
    public void createDipswitch() {
        Mame mame = mameService.create(datafile);
        MameMachine machine = mame.getMachines().iterator().next();
        MameDipswitch dipswitch = machine.getDipswitches().get(0);

        Assert.assertEquals("name", dipswitch005.getName(), dipswitch.getName());
        Assert.assertEquals("tag", dipswitch005.getTag(), dipswitch.getTag());
        Assert.assertEquals("mask", dipswitch005.getMask(), dipswitch.getMask());
    }

    @Test
    public void createDipvalue() {
        Mame mame = mameService.create(datafile);
        MameMachine machine = mame.getMachines().iterator().next();
        MameDipswitch dipswitch = machine.getDipswitches().get(0);
        MameDipvalue dipvalue = dipswitch.getDipvalues().get(0);

        Assert.assertEquals("name", dipvalue005.getName(), dipvalue.getName());
        Assert.assertEquals("value", dipvalue005.getValue(), dipvalue.getValue());
        Assert.assertEquals("default", dipvalue005.getDefault(), dipvalue.getDefault());
    }

    @Test
    public void createConfiguration() {
        Mame mame = mameService.create(datafile);
        MameMachine machine = mame.getMachines().iterator().next();
        MameConfiguration configuration = machine.getConfigurations().iterator().next();

        Assert.assertEquals("name", configuration005.getName(), configuration.getName());
        Assert.assertEquals("tag", configuration005.getTag(), configuration.getTag());
        Assert.assertEquals("mask", configuration005.getMask(), configuration.getMask());
    }

    @Test
    public void createConfsetting() {
        Mame mame = mameService.create(datafile);
        MameMachine machine = mame.getMachines().iterator().next();
        MameConfiguration configuration = machine.getConfigurations().iterator().next();
        MameConfsetting confsetting = configuration.getConfsettings().iterator().next();

        Assert.assertEquals("name", confsetting005.getName(), confsetting.getName());
        Assert.assertEquals("value", confsetting005.getValue(), confsetting.getValue());
        Assert.assertEquals("default", confsetting005.getDefault(), confsetting.getDefault());
    }

    @Test
    public void createPort() {
        Mame mame = mameService.create(datafile);
        MameMachine machine = mame.getMachines().iterator().next();
        MamePort port = machine.getPorts().get(0);

        Assert.assertEquals("tag", port005.getTag(), port.getTag());
    }

    @Test
    public void createAnalog() {
        Mame mame = mameService.create(datafile);
        MameMachine machine = mame.getMachines().iterator().next();
        MamePort port = machine.getPorts().get(0);
        MameAnalog analog = port.getAnalogs().get(0);

        Assert.assertEquals("mask", analog005.getMask(), analog.getMask());
    }

    @Test
    public void createAdjuster() {
        Mame mame = mameService.create(datafile);
        MameMachine machine = mame.getMachines().iterator().next();
        MameAdjuster adjuster = machine.getAdjusters().iterator().next();

        Assert.assertEquals("name", adjuster005.getName(), adjuster.getName());
        Assert.assertEquals("default", adjuster005.getDefault(), adjuster.getDefault());
    }

    @Test
    public void createDriver() {
        Mame mame = mameService.create(datafile);
        MameMachine machine = mame.getMachines().iterator().next();
        MameDriver driver = machine.getDriver();

        Assert.assertEquals("status", driver005.getStatus(), driver.getStatus());
        Assert.assertEquals("emulation", driver005.getEmulation(), driver.getEmulation());
        Assert.assertEquals("color", driver005.getColor(), driver.getColor());
        Assert.assertEquals("sound", driver005.getSound(), driver.getSound());
        Assert.assertEquals("graphic", driver005.getGraphic(), driver.getGraphic());
        Assert.assertEquals("cocktail", driver005.getCocktail(), driver.getCocktail());
        Assert.assertEquals("protection", driver005.getProtection(), driver.getProtection());
        Assert.assertEquals("savestate", driver005.getSavestate(), driver.getSavestate());
    }

    @Test
    public void createDevice() {
        Mame mame = mameService.create(datafile);
        MameMachine machine = mame.getMachines().iterator().next();
        MameDevice device = machine.getDevices().iterator().next();

        Assert.assertEquals("type", device005.getType(), device.getType());
        Assert.assertEquals("tag", device005.getTag(), device.getTag());
        Assert.assertEquals("fixed_image", device005.getFixed_image(), device.getFixed_image());
        Assert.assertEquals("mandatory", device005.getMandatory(), device.getMandatory());
        Assert.assertEquals("interface", device005.getInterface(), device.getInterface());
    }

    @Test
    public void createDeviceInstance() {
        Mame mame = mameService.create(datafile);
        MameMachine machine = mame.getMachines().iterator().next();
        MameDevice device = machine.getDevices().iterator().next();
        MameDeviceInstance instance = device.getInstances().iterator().next();

        Assert.assertEquals("name", deviceInstance005.getName(), instance.getName());
        Assert.assertEquals("briefname", deviceInstance005.getBriefname(), instance.getBriefname());
    }

    @Test
    public void createDeviceExtension() {
        Mame mame = mameService.create(datafile);
        MameMachine machine = mame.getMachines().iterator().next();
        MameDevice device = machine.getDevices().iterator().next();
        MameDeviceExtension extension = device.getExtensions().iterator().next();

        Assert.assertEquals("name", deviceExtension005.getName(), extension.getName());
    }
}
