package settings;

import codegeneration.InterfaceConfigurationEmitter;
import codegeneration.RouterConfigurationEmitter;
import symbols.IpAddress;
import symbols.PhysicalInterface;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class StubAreaSetting extends AreaSetting {

    public StubAreaSetting(int value) {
        super(value);
    }

    @Override
    public void addEmitters(PhysicalInterface physicalInterface, Set<RouterConfigurationEmitter> routerEmitters, Set<InterfaceConfigurationEmitter> interfaceEmitters, Map<IpAddress, List<PhysicalInterface>> interfaceNetworkMap) {
        super.addEmitters(physicalInterface, routerEmitters, interfaceEmitters, interfaceNetworkMap);
        routerEmitters.add(new RouterConfigurationEmitter(
                "Router OSPF 1",
                String.format("Area %d stub", areaNumber)));
    }
}
