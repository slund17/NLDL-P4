package settings;

import codegeneration.InterfaceConfigurationEmitter;
import codegeneration.RouterConfigurationEmitter;
import symbols.IpAddress;
import symbols.PhysicalInterface;
import symbols.Router;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class RouterIDSetting extends RouterSetting{
    private final IpAddress ip;

    public RouterIDSetting(IpAddress ip) {
        super(SettingType.ROUTER_ID);
        this.ip = ip;
    }

    @Override
    public void addEmitters(Router router, Set<RouterConfigurationEmitter> routerEmitters, Set<InterfaceConfigurationEmitter> interfaceEmitters, Map<IpAddress, List<PhysicalInterface>> interfaceNetworkMap) {
        routerEmitters.add(new RouterConfigurationEmitter(String.format("Router-ID %d.%d.%d.%d", ip.seg1, ip.seg2, ip.seg3, ip.seg4)));
    }
}
