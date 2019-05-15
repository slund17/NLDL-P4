package symbols;

import settings.RouterSetting;
import settings.Setting;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Router {
    Map<InterfaceIndex, PhysicalInterface > interfaceTable = new HashMap<>();
    Set<RouterSetting> settingTable = new HashSet<>();

    public PhysicalInterface enterInterface(InterfaceIndex inf){
        PhysicalInterface physicalInterface =  new PhysicalInterface(this);
        interfaceTable.put(inf, physicalInterface);
        return physicalInterface;
    }

    public PhysicalInterface retrieveInterface(InterfaceIndex inf){
        return interfaceTable.get(inf);
    }

    public void enterSetting(RouterSetting setting){
        settingTable.add(setting);
    }

}

