package settings;

import symbols.Setting;
import symbols.SettingType;

public class AreaSetting extends Setting {
    public final int value;

    public AreaSetting(int value) {
        super(SettingType.OSPF_AREA);
        this.value = value;
    }
}
