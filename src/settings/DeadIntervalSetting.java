package settings;

public class DeadIntervalSetting extends InterfaceSetting {
    public final int interval;
    public DeadIntervalSetting(int num) {
        super(SettingType.OSPF_DEAD_INTERVAL);
        interval = num;
    }
}
