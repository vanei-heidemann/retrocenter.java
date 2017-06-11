package com.javanei.retrocenter.mame;

import com.javanei.retrocenter.common.util.StringUtil;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class MameDevice implements Serializable {
    private static final long serialVersionUID = 1L;

    private String type;
    private String tag;
    private Integer fixed_image;
    private Integer mandatory;
    private String _interface;

    private List<MameDeviceInstance> instances = new LinkedList<>();
    private List<MameDeviceExtension> extensions = new LinkedList<>();

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Integer getFixed_image() {
        return fixed_image;
    }

    public void setFixed_image(String fixed_image) {
        if (fixed_image != null)
            this.fixed_image = new Integer(fixed_image);
    }

    public void setFixed_image(Integer fixed_image) {
        this.fixed_image = fixed_image;
    }

    public Integer getMandatory() {
        return mandatory;
    }

    public void setMandatory(String mandatory) {
        if (mandatory != null)
            this.mandatory = new Integer(mandatory);
    }

    public void setMandatory(Integer mandatory) {
        this.mandatory = mandatory;
    }

    public String getInterface() {
        return _interface;
    }

    public void setInterface(String _interface) {
        this._interface = _interface;
    }

    public List<MameDeviceInstance> getInstances() {
        return instances;
    }

    public void setInstances(List<MameDeviceInstance> instances) {
        this.instances = instances;
    }

    public void addInstance(MameDeviceInstance instance) {
        this.instances.add(instance);
    }

    public List<MameDeviceExtension> getExtensions() {
        return extensions;
    }

    public void setExtensions(List<MameDeviceExtension> extensions) {
        this.extensions = extensions;
    }

    public void addExtension(MameDeviceExtension extension) {
        this.extensions.add(extension);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\t\t<device");
        if (this.type != null) {
            sb.append(" type=\"").append(this.type).append("\"");
        }
        if (this.tag != null) {
            sb.append(" tag=\"").append(this.tag).append("\"");
        }
        if (this.fixed_image != null) {
            sb.append(" fixed_image=\"").append(this.fixed_image).append("\"");
        }
        if (this.mandatory != null) {
            sb.append(" mandatory=\"").append(this.mandatory).append("\"");
        }
        if (this._interface != null) {
            sb.append(" interface=\"").append(this._interface).append("\"");
        }
        sb.append(">").append(StringUtil.LINE_SEPARATOR);

        for (MameDeviceInstance instance : this.instances) {
            sb.append(instance.toString());
        }
        for (MameDeviceExtension extension : this.extensions) {
            sb.append(extension.toString());
        }

        sb.append("\t\t</device>").append(StringUtil.LINE_SEPARATOR);
        return sb.toString();
    }
}
