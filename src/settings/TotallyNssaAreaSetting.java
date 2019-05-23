package settings;

import codegeneration.InterfaceConfigurationEmitter;
import codegeneration.RouterConfigurationEmitter;
import symbols.IpAddress;
import symbols.PhysicalInterface;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class TotallyNssaAreaSetting extends AreaSetting{
    public TotallyNssaAreaSetting(int value) {
        super(value);
    }


    @Override
    public void addEmitters(PhysicalInterface physicalInterface, Set<RouterConfigurationEmitter> routerEmitters, Set<InterfaceConfigurationEmitter> interfaceEmitters, Map<IpAddress, List<PhysicalInterface>> interfaceNetworkMap) {
        super.addEmitters(physicalInterface, routerEmitters, interfaceEmitters, interfaceNetworkMap);
        boolean isAreaBorderRouter = false;
        routerEmitters.add(new RouterConfigurationEmitter("Router OSPF 1"));

        for (PhysicalInterface anInterface : physicalInterface.getRouter().getInterfaces()) {
            for (InterfaceSetting setting : anInterface.getSettings()) {
                if(setting instanceof AreaSetting){
                    if(((AreaSetting) setting).areaNumber != this.areaNumber){
                        isAreaBorderRouter = true;
                    }
                    break;
                }
            }
            if (isAreaBorderRouter) break;
        }

        if (!isAreaBorderRouter){
            routerEmitters.add(new RouterConfigurationEmitter(String.format("Area %d nssa", this.areaNumber)));
        } else {
            routerEmitters.add(new RouterConfigurationEmitter(String.format("Area %d nssa no-summary", this.areaNumber)));
        }


    }
}
