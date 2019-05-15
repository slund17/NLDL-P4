package settings;

import symbols.IpAddress;

public class DNSServerSetting extends RouterSetting {

    private final IpAddress ip;

    public DNSServerSetting(IpAddress ip) {
        super(SettingType.DNS_SERVER);
        this.ip = ip;
    }
}
