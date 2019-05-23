package settings;

import codegeneration.InterfaceConfigurationEmitter;
import codegeneration.RouterConfigurationEmitter;
import symbols.IpAddress;
import symbols.PhysicalInterface;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class NssaAreaSetting extends  AreaSetting {


    public NssaAreaSetting(int value) {
        super(value);
    }

    @Override
    public void addEmitters(PhysicalInterface physicalInterface, Set<RouterConfigurationEmitter> routerEmitters, Set<InterfaceConfigurationEmitter> interfaceEmitters, Map<IpAddress, List<PhysicalInterface>> interfaceNetworkMap) {
        super.addEmitters(physicalInterface, routerEmitters, interfaceEmitters, interfaceNetworkMap);
        routerEmitters.add(new RouterConfigurationEmitter("Router OSPF 1"));
        routerEmitters.add(new RouterConfigurationEmitter(String.format("Area %d nssa", this.areaNumber)));
    }
}
