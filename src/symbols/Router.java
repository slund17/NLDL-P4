package symbols;

import com.dat405.nldl.node.ASettingBlock;
import com.dat405.nldl.node.PIf;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Router {
    Map<InterfaceIndex, PhysicalInterface > interfaceTable = new HashMap<>();
    Set<Setting> settingTable = new HashSet<>();

    void enterInterface(InterfaceIndex inf){
        interfaceTable.put(inf, new PhysicalInterface(this));
    }

    void enterSetting(ASettingBlock setting){
        settingTable.add(Setting.getSetting(setting));
    }

}

