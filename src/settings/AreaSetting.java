package settings;

public class AreaSetting extends InterfaceSetting {
    public final int value;

    public AreaSetting(int value) {
        super(SettingType.OSPF_AREA);
        this.value = value;
    }
}
