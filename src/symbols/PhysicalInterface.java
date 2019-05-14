package symbols;

import com.dat405.nldl.node.ASettingBlock;
import com.dat405.nldl.node.PIf;

import java.util.HashSet;
import java.util.Set;

public class PhysicalInterface {

    Set<Setting> settingTable = new HashSet<>();

    void enterSetting(ASettingBlock setting){
        settingTable.add(Setting.getSetting(setting));
    }
}
