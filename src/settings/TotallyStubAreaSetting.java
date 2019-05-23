package settings;

import codegeneration.InterfaceConfigurationEmitter;
import codegeneration.RouterConfigurationEmitter;
import symbols.IpAddress;
import symbols.PhysicalInterface;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class TotallyStubAreaSetting extends AreaSetting{
    public TotallyStubAreaSetting(int value) {
        super(value);
    }

    @Override
    public void addEmitters(PhysicalInterface physicalInterface, Set<RouterConfigurationEmitter> routerEmitters, Set<InterfaceConfigurationEmitter> interfaceEmitters, Map<IpAddress, List<PhysicalInterface>> interfaceNetworkMap) {
        super.addEmitters(physicalInterface, routerEmitters, interfaceEmitters, interfaceNetworkMap);

        boolean isAreaBorderRouter = false;

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


        if(!isAreaBorderRouter){
            routerEmitters.add(new RouterConfigurationEmitter(
                    "Router OSPF 1",
                    String.format("Area %d stub", areaNumber)));
        } else {
            routerEmitters.add(new RouterConfigurationEmitter(
                    "Router OSPF 1",
                    String.format("Area %d stub no-summary", areaNumber)));
        }
    }
}
