package settings;

import codegeneration.InterfaceConfigurationEmitter;
import codegeneration.RouterConfigurationEmitter;
import symbols.IpAddress;
import symbols.PhysicalInterface;
import symbols.Router;

import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class RouterSetting  extends Setting {
    protected RouterSetting(SettingType settingType) {
        super(settingType);
    }

    public abstract void addEmitters(Router router, Set<RouterConfigurationEmitter> routerEmitters, Set<InterfaceConfigurationEmitter> interfaceEmitters, Map<IpAddress, List<PhysicalInterface>> interfaceNetworkMap);
}
