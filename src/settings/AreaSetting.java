package settings;

import codegeneration.InterfaceConfigurationEmitter;
import codegeneration.RouterConfigurationEmitter;
import symbols.IpAddress;
import symbols.PhysicalInterface;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class AreaSetting extends InterfaceSetting {
    public int areaNumber;

    public AreaSetting(int value) {
        super(SettingType.OSPF_AREA);
        this.areaNumber = value;
    }

    @Override
    public void addEmitters(PhysicalInterface physicalInterface, Set<RouterConfigurationEmitter> routerEmitters, Set<InterfaceConfigurationEmitter> interfaceEmitters, Map<IpAddress, List<PhysicalInterface>> interfaceNetworkMap) {
        IpAddress net = physicalInterface.getNetworkAddress();
        IpAddress wild = physicalInterface.getSubnetMask().inversed();
        routerEmitters.add(new RouterConfigurationEmitter(String.format(
                "Network %d.%d.%d.%d %d.%d.%d.%d Area %d",
                net.seg1, net.seg2, net.seg3, net.seg4,
                wild.seg1, wild.seg2, wild.seg3, wild.seg4,
                areaNumber)));
    }
}
