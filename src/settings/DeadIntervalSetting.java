package settings;

import symbols.Setting;
import symbols.SettingType;

public class DeadIntervalSetting extends Setting {
    public final int interval;
    public DeadIntervalSetting(int num) {
        super(SettingType.OSPF_DEAD_INTERVAL);
        interval = num;
    }
}
