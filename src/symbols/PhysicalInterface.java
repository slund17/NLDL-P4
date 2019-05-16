package symbols;

import settings.InterfaceSetting;
import settings.Setting;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PhysicalInterface {

    private final Router router;
    private final InterfaceIndex interfaceIndex;

    private static Map<Integer, IpAddress> subnetTable = new HashMap<>();

    static {
        subnetTable.put(1, new IpAddress(128, 0, 0, 0));
        subnetTable.put(2, new IpAddress(192, 0, 0, 0));
        subnetTable.put(3, new IpAddress(224, 0, 0, 0));
        subnetTable.put(4, new IpAddress(240, 0, 0, 0));
        subnetTable.put(5, new IpAddress(248, 0, 0, 0));
        subnetTable.put(6, new IpAddress(252, 0, 0, 0));
        subnetTable.put(7, new IpAddress(254, 0, 0, 0));
        subnetTable.put(8, new IpAddress(255, 0, 0, 0));
        subnetTable.put(9, new IpAddress(255, 128, 0, 0));
        subnetTable.put(10, new IpAddress(255, 192, 0, 0));
        subnetTable.put(11, new IpAddress(255, 224, 0, 0));
        subnetTable.put(12, new IpAddress(255, 240, 0, 0));
        subnetTable.put(13, new IpAddress(255, 248, 0, 0));
        subnetTable.put(14, new IpAddress(255, 252, 0, 0));
        subnetTable.put(15, new IpAddress(255, 254, 0, 0));
        subnetTable.put(16, new IpAddress(255, 255, 0, 0));
        subnetTable.put(17, new IpAddress(255, 255, 128, 0));
        subnetTable.put(18, new IpAddress(255, 255, 192, 0));
        subnetTable.put(19, new IpAddress(255, 255, 224, 0));
        subnetTable.put(20, new IpAddress(255, 255, 240, 0));
        subnetTable.put(21, new IpAddress(255, 255, 248, 0));
        subnetTable.put(22, new IpAddress(255, 255, 252, 0));
        subnetTable.put(23, new IpAddress(255, 255, 254, 0));
        subnetTable.put(24, new IpAddress(255, 255, 255, 0));
        subnetTable.put(25, new IpAddress(255, 255, 255, 128));
        subnetTable.put(26, new IpAddress(255, 255, 255, 192));
        subnetTable.put(27, new IpAddress(255, 255, 255, 224));
        subnetTable.put(28, new IpAddress(255, 255, 255, 240));
        subnetTable.put(29, new IpAddress(255, 255, 255, 248));
        subnetTable.put(30, new IpAddress(255, 255, 255, 252));
        subnetTable.put(31, new IpAddress(255, 255, 255, 254));
        subnetTable.put(32, new IpAddress(255, 255, 255, 255));
    }

    Set<InterfaceSetting> settingTable = new HashSet<>();
    IpAddress ip = null;
    Integer mask = null;

    public PhysicalInterface(Router router, InterfaceIndex inf) {
        this.interfaceIndex = inf;
        this.router = router;
    }


    public Set<InterfaceSetting> getSettings(){
        return settingTable;
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

    public Router getRouter() {
        return router;
    }

    public InterfaceIndex getInterfaceIndex() {
        return interfaceIndex;
    }

    public IpAddress getSubnetMask(){
        return subnetTable.get(mask);
    }

    public IpAddress getNetworkAddress(){
        return getSubnetMask().bitAnd(ip);
    }

    public IpAddress getIp() {
        return ip;
    }
}
