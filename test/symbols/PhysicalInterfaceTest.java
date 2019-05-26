package symbols;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import settings.HelloIntervalSetting;

import static org.junit.jupiter.api.Assertions.*;

class PhysicalInterfaceTest {

    Router router = new Router("R1");
    InterfaceIndex interfaceIndex = new InterfaceIndex(2, 10, InterfaceType.GIGABIT);
    PhysicalInterface physicalInterface = new PhysicalInterface(router, interfaceIndex);

    @BeforeEach
    void setup() {
        physicalInterface.enterIP(new IpAddress(1, 2, 3, 4));
    }

    @Test
    void getSetting1() {
        assertEquals(0, physicalInterface.getSettings().size());
    }

    @Test
    void enterSetting() {
        physicalInterface.enterSetting(new HelloIntervalSetting(200));
        assertTrue(physicalInterface.getSettings().contains(new HelloIntervalSetting(100)));
    }

    @Test
    void enterSetting2() {
        physicalInterface.enterSetting(new HelloIntervalSetting(300));
        physicalInterface.enterSetting(new HelloIntervalSetting(200));
        assertEquals(1, physicalInterface.getSettings().size());
    }

    @Test
    void enterIP() {
        physicalInterface.enterIP(new IpAddress(10, 10, 20, 30));
        assertEquals(new IpAddress(10, 10, 20, 30), physicalInterface.getIp());
    }

    @Test
    void enterMask() {
        physicalInterface.enterMask(20);
        assertEquals(new IpAddress(255, 255, 240, 0), physicalInterface.getSubnetMask());
    }

    @Test
    void noMask() {
        PhysicalInterface physicalInterface = new PhysicalInterface(null, new InterfaceIndex(0,1, InterfaceType.FAST_ETHERNET));
        assertThrows(RuntimeException.class, physicalInterface::getSubnetMask);
    }
}