package symbols;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InterfaceIndexTest {

    InterfaceIndex interfaceIndex = new InterfaceIndex(0, 1, InterfaceType.ETHERNET);
    InterfaceIndex interfaceIndex2 = new InterfaceIndex(0, 1, InterfaceType.ETHERNET);

    @Test
    void getIndex1() {
        assertEquals(0, interfaceIndex.getIndex1());
    }

    @Test
    void getIndex2() {
        assertEquals(1, interfaceIndex.getIndex2() );
    }

    @Test
    void equals() {
        assertEquals(interfaceIndex, interfaceIndex2);
    }

    @Test
    void getInterfaceType() {
        assertEquals(InterfaceType.ETHERNET, interfaceIndex.getInterfaceType());
    }
}