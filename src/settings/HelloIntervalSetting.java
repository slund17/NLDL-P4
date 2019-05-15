package settings;

public class HelloIntervalSetting extends InterfaceSetting {
    public final int interval;

    public HelloIntervalSetting(int num) {
        super(SettingType.OSPF_HELLO_INTERVAL);
        interval = num;
    }
}
