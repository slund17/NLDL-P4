package symbols;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IpAddressTest {

    IpAddress ipAddress = new IpAddress(10, 20, 30, 40);
    IpAddress ipAddress2 = new IpAddress(10, 20, 30, 50);


    @Test
    void equals1() {
        assertNotEquals(ipAddress, ipAddress2);
    }

    @Test
    void equals2() {
        assertEquals(ipAddress, new IpAddress(10, 20, 30, 40));
    }

    @Test
    void bitAnd() {
        assertEquals(ipAddress, ipAddress.bitAnd(ipAddress));
    }

    @Test
    void inverse() {
        assertEquals(ipAddress.inversed(), new IpAddress(245, 235, 225, 215));
    }
}