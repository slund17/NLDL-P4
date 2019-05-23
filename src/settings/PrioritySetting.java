package settings;

import codegeneration.InterfaceConfigurationEmitter;
import codegeneration.RouterConfigurationEmitter;
import symbols.IpAddress;
import symbols.PhysicalInterface;
import symbols.Router;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class PrioritySetting extends InterfaceSetting {
    public final int priorityVal;



    public PrioritySetting(int integer) {
        super(SettingType.OSPF_PRIORITY);
        this.priorityVal = integer;
    }

    @Override
    public void addEmitters(PhysicalInterface physicalInterface, Set<RouterConfigurationEmitter> routerEmitters, Set<InterfaceConfigurationEmitter> interfaceEmitters, Map<IpAddress, List<PhysicalInterface>> interfaceNetworkMap) {
        interfaceEmitters.add(new InterfaceConfigurationEmitter(physicalInterface, String.format("Ip OSPF Priority %d", priorityVal)));
    }
}
