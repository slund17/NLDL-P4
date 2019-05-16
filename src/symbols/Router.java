package symbols;

import settings.RouterSetting;
import settings.Setting;

import java.util.*;

public class Router {
    Map<InterfaceIndex, PhysicalInterface > interfaceTable = new HashMap<>();
    Set<RouterSetting> settingTable = new HashSet<>();

    private final String name;

    public Router(String name) {
        this.name = name;
    }

    public PhysicalInterface enterInterface(InterfaceIndex inf){
        PhysicalInterface physicalInterface =  new PhysicalInterface(this, inf);
        interfaceTable.put(inf, physicalInterface);
        return physicalInterface;
    }

    public Collection<PhysicalInterface> getInterfaces(){
        return interfaceTable.values();
    }

    public Set<RouterSetting> getSettings(){
        return settingTable;
    }

    public PhysicalInterface retrieveInterface(InterfaceIndex inf){
        return interfaceTable.get(inf);
    }

    public void enterSetting(RouterSetting setting){
        settingTable.add(setting);
    }

    public String getName() {
        return name;
    }
}

