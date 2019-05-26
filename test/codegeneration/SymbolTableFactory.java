package codegeneration;

import org.junit.jupiter.api.Test;
import settings.*;
import symbolTables.RouterSymbolTable;
import symbols.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SymbolTableFactory {

    public static final String ROUTER_NAME1 = "RarRouter";
    public static final String ROUTER_NAME2 = "C3PO";
    public static final String ROUTER_NAME3 = "R2D2";
    public static final String ROUTER_NAME4 = "blank";

    // Create full symbol tables with routers and connections
    public static RouterSymbolTable table1() {
        Router router = new Router(ROUTER_NAME1);
        router.enterSetting(new DNSServerSetting(new IpAddress(10,10,10,10)));
        InterfaceIndex interfaceIndex = new InterfaceIndex(0,1, InterfaceType.FAST_ETHERNET);
        router.enterInterface(interfaceIndex);
        PhysicalInterface physicalInterface = router.retrieveInterface(interfaceIndex);
        physicalInterface.enterIP( new IpAddress(10,20,30,40));
        physicalInterface.enterMask(30);
        physicalInterface.enterSetting(new HelloIntervalSetting(100));
        physicalInterface.enterSetting(new DeadIntervalSetting(200));
        physicalInterface.enterSetting(new StubAreaSetting(2));
        RouterSymbolTable routerSymbolTable = new RouterSymbolTable();
        routerSymbolTable.enterSymbol(router.getName(), router);
        return routerSymbolTable;
    }

    public static RouterSymbolTable table2() {
        // Router 1
        Router router = new Router(ROUTER_NAME2);
        router.enterSetting(new DefaultMetricSetting(30));
        InterfaceIndex interfaceIndex = new InterfaceIndex(2,3, InterfaceType.GIGABIT);
        router.enterInterface(interfaceIndex);
        PhysicalInterface physicalInterface = router.retrieveInterface(interfaceIndex);
        physicalInterface.enterIP( new IpAddress(100,200,155,20));
        physicalInterface.enterMask(30);
        physicalInterface.enterSetting(new HelloIntervalSetting(20));
        physicalInterface.enterSetting(new DeadIntervalSetting(50));
        physicalInterface.enterSetting(new NssaAreaSetting(90));

        // Router 2
        Router router2 = new Router(ROUTER_NAME3);
        InterfaceIndex interfaceIndex2 = new InterfaceIndex(1, 2, InterfaceType.FAST_ETHERNET);
        router2.enterInterface(interfaceIndex2);
        PhysicalInterface physicalInterface2 = router2.retrieveInterface(interfaceIndex2);
        physicalInterface2.enterIP(new IpAddress(100, 100, 200, 155));
        physicalInterface2.enterSetting(new HelloIntervalSetting(100));;
        physicalInterface2.enterSetting(new DeadIntervalSetting(20));
        physicalInterface2.enterMask(30);

        InterfaceIndex interfaceIndex3 = new InterfaceIndex(1,2,InterfaceType.ETHERNET);
        PhysicalInterface physicalInterface3 = new PhysicalInterface(router2, interfaceIndex3);
        physicalInterface3.enterMask(20);
        router2.enterInterface(interfaceIndex3);
        physicalInterface3 = router2.retrieveInterface(interfaceIndex3);
        physicalInterface3.enterMask(30);
        physicalInterface3.enterIP(new IpAddress(10, 200, 100, 50));

        Router blankRouter = new Router(ROUTER_NAME4);

        RouterSymbolTable routerSymbolTable = new RouterSymbolTable();
        routerSymbolTable.enterSymbol(router2.getName(), router2);
        routerSymbolTable.enterSymbol(router.getName(), router);
        routerSymbolTable.enterSymbol(blankRouter.getName(), blankRouter);
        return routerSymbolTable;
    }

    @Test
    void tableSize1() {
        assertEquals(1, table1().getRouters().size());
    }

    @Test
    void tableSize2() {
        assertEquals(3, table2().getRouters().size());
    }

    @Test
    void setRouterName1inTable1() {
        List<Router> routers = new ArrayList<>(table1().getRouters());
        assertEquals(ROUTER_NAME1, routers.get(0).getName());
    }
}
