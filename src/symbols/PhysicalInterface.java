package symbols;

import com.dat405.nldl.node.ASettingBlock;
import com.dat405.nldl.node.PIf;
import com.dat405.nldl.node.PIp;
import com.dat405.nldl.node.TConst;

import java.util.HashSet;
import java.util.Set;

public class PhysicalInterface {

    private final Router router;

    Set<Setting> settingTable = new HashSet<>();
    IpAddress ip = null;
    Integer mask = null;

    public PhysicalInterface(Router router) {
        this.router = router;
    }

    void enterSetting(ASettingBlock setting){
        settingTable.add(Setting.getSetting(setting));
    }

    void enterIP(PIp pip, Group group){
        this.ip = new IpAddress(pip, group);
    }

    void enterMask(TConst mask){
        int value = Integer.valueOf(mask.getText());
        if(value>32 || value<0){
            throw new RuntimeException("Mask incorrect");
        }
        this.mask = value;
    }
}
