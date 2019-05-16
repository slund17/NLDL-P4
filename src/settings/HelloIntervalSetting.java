package settings;

import codegeneration.InterfaceConfigurationEmitter;
import codegeneration.RouterConfigurationEmitter;
import symbols.IpAddress;
import symbols.PhysicalInterface;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class HelloIntervalSetting extends InterfaceSetting {
    public final int interval;

    public HelloIntervalSetting(int num) {
        super(SettingType.OSPF_HELLO_INTERVAL);
        interval = num;
    }

    @Override
    public void addEmitters(PhysicalInterface physicalInterface, Set<RouterConfigurationEmitter> routerEmitters, Set<InterfaceConfigurationEmitter> interfaceEmitters, Map<IpAddress, List<PhysicalInterface>> interfaceNetworkMap) {
        interfaceEmitters.add(new InterfaceConfigurationEmitter(physicalInterface, String.format("Ip OSPF Hello-Interval %d", interval)));
    }
}
