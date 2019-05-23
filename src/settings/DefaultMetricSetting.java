package settings;

import codegeneration.InterfaceConfigurationEmitter;
import codegeneration.RouterConfigurationEmitter;
import symbols.IpAddress;
import symbols.PhysicalInterface;
import symbols.Router;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class DefaultMetricSetting extends RouterSetting{
    public final int cost;

    public DefaultMetricSetting(Integer integer) {
        super(SettingType.OSPF_DEFAULT_METRIC);
        this.cost = integer;
    }

    @Override
    public void addEmitters(Router router, Set<RouterConfigurationEmitter> routerEmitters, Set<InterfaceConfigurationEmitter> interfaceEmitters, Map<IpAddress, List<PhysicalInterface>> interfaceNetworkMap) {
        RouterConfigurationEmitter emitter = new RouterConfigurationEmitter(
                "Router OSPF 1",
                String.format("Default-metric %d", cost)
        );

        routerEmitters.add(emitter);
    }
}
