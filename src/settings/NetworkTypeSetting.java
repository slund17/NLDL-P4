package settings;

import codegeneration.InterfaceConfigurationEmitter;
import codegeneration.RouterConfigurationEmitter;
import symbols.IpAddress;
import symbols.PhysicalInterface;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class NetworkTypeSetting extends InterfaceSetting {

    private final NetworkType type;

    public NetworkTypeSetting(String type) {
        super(SettingType.OSPF_NETWORK_TYPE);

        switch (type.toUpperCase()){
            case "BROADCAST": this.type=NetworkType.BROADCAST; break;
            case "POINT-TO-POINT": this.type=NetworkType.POINT_TO_POINT; break;
            case "NON-BROADCAST": this.type=NetworkType.NON_BROADCAST; break;
            case "POINT-TO-MULTIPOINT": this.type=NetworkType.POINT_TO_MULTIPOINT; break;
            default: throw new RuntimeException("Unknown network type");
        }
    }

    public NetworkTypeSetting(String type1, String type2) {
        super(SettingType.OSPF_NETWORK_TYPE);
        type = NetworkType.POINT_TO_MULTIPOINT_NON_BROADCAST;
    }

    @Override
    public void addEmitters(PhysicalInterface physicalInterface, Set<RouterConfigurationEmitter> routerEmitters, Set<InterfaceConfigurationEmitter> interfaceEmitters, Map<IpAddress, List<PhysicalInterface>> interfaceNetworkMap) {
        switch (type){
            case POINT_TO_MULTIPOINT_NON_BROADCAST:
            case NON_BROADCAST:

                RouterConfigurationEmitter emitter = new RouterConfigurationEmitter("Router OSPF 1");
                for (PhysicalInterface anInterface : interfaceNetworkMap.get(physicalInterface.getNetworkAddress())) {
                    if(anInterface != physicalInterface){
                        IpAddress ip = anInterface.getIp();
                        emitter.addCommand(String.format("Neighbor %d.%d.%d.%d", ip.seg1, ip.seg2, ip.seg3, ip.seg4));
                    }
                }
                routerEmitters.add(emitter);


            case POINT_TO_POINT:
            case POINT_TO_MULTIPOINT:
            case BROADCAST:
            default:
                interfaceEmitters.add(new InterfaceConfigurationEmitter(physicalInterface, String.format("Ip OSPF Network %s", type.toString())));
        }
    }
}
