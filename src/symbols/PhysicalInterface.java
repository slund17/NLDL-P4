package symbols;

import settings.InterfaceSetting;
import settings.Setting;

import java.util.HashSet;
import java.util.Set;

public class PhysicalInterface {

    private final Router router;

    Set<InterfaceSetting> settingTable = new HashSet<>();
    IpAddress ip = null;
    Integer mask = null;

    public PhysicalInterface(Router router) {
        this.router = router;
    }

    public void enterSetting(InterfaceSetting setting){
        settingTable.add(setting);
    }

    public void enterIP(IpAddress ip){
        this.ip = ip;
    }

    public void enterMask(int value){
        if(value>32 || value<0){
            throw new RuntimeException("Mask incorrect");
        }
        this.mask = value;
    }
}
