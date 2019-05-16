package settings;

import codegeneration.InterfaceConfigurationEmitter;
import codegeneration.RouterConfigurationEmitter;
import symbols.IpAddress;
import symbols.PhysicalInterface;
import symbols.Router;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DNSServerSetting extends RouterSetting {

    private final IpAddress ip;

    public DNSServerSetting(IpAddress ip) {
        super(SettingType.DNS_SERVER);
        this.ip = ip;
    }

    @Override
    public void addEmitters(Router router, Set<RouterConfigurationEmitter> routerEmitters, Set<InterfaceConfigurationEmitter> interfaceEmitters, Map<IpAddress, List<PhysicalInterface>> interfaceNetworkMap) {
        RouterConfigurationEmitter emitter = new RouterConfigurationEmitter(
                String.format("ip name-server %d.%d.%d.%d", ip.seg1, ip.seg2, ip.seg3, ip.seg4)
        );

        routerEmitters.add(emitter);

        //config terminal
        //ip name-server %ip%
        //end
    }
}
