package settings;

import symbols.Setting;
import symbols.SettingType;

public class HelloIntervalSetting extends Setting {
    public final int interval;

    public HelloIntervalSetting(int num) {
        super(SettingType.OSPF_HELLO_INTERVAL);
        interval = num;
    }
}
