package settings;

import org.junit.jupiter.api.Test;
import symbols.IpAddress;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SettingTest {

    @Test
    void totallyStubSetting() {
        List<Object> stub = Arrays.asList("OSPF", "Area", 22, "totally-stub");
        Setting setting = Setting.getSetting(stub);
        assertEquals(TotallyStubAreaSetting.class, setting.getClass());
    }

    @Test
    void helloIntervalSetting() {
        List<Object> hello = Arrays.asList("OSPF", "hello-interval", 22);
        Setting setting = Setting.getSetting(hello);
        assertEquals(HelloIntervalSetting.class, setting.getClass());
    }

    @Test
    void totallyNssaSetting() {
        List<Object> nssa = Arrays.asList("OSPF", "Area", 2, "nssa");
        Setting setting = Setting.getSetting(nssa);
        assertEquals(NssaAreaSetting.class, setting.getClass());
    }

    @Test
    void wrongSetting() {
        List<Object> hello = Arrays.asList("OSPF", "hej-interval", 22);
        assertThrows(RuntimeException.class, () ->  Setting.getSetting(hello));
    }

    @Test
    void routerIDSetting() {
        List<Object> routerId = Arrays.asList("Router", "ID", new IpAddress(10,20,30,10));
        Setting setting = Setting.getSetting(routerId);
        assertEquals(RouterIDSetting.class, setting.getClass());
    }

    @Test
    void prioritySetting() {
        List<Object> priorityString = Arrays.asList("OSPF", "priority", 1);
        assertEquals(PrioritySetting.class, Setting.getSetting(priorityString).getClass());
    }

    @Test
    void prioritySetting2() {
        List<Object> priorityString = Arrays.asList("OSPF", "PrioRitY", 1);
        assertEquals(PrioritySetting.class, Setting.getSetting(priorityString).getClass());
    }

    @Test void networkTypeSetting() {
        List<Object> networkTypeSetting = Arrays.asList("OSPF","Network", "non-broadcast");
        assertEquals(Setting.getSetting(networkTypeSetting).getClass(), NetworkTypeSetting.class);
    }
}