package visitors;


import ast.ASTfactory;
import org.junit.jupiter.api.Test;
import symbols.IpAddress;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class GBlk_IP {

    private SemanticsVisitor semanticsVisitor = new SemanticsVisitor();
    final String GBlk =
            "Router R18, R19;" +
            "Group area3{" +
                "IP 10.3.2.0;" +
                "" +
                "Setting OSPF Area 3 totally-stub;" +
                "Setting OSPF Network point-to-point;" +
                "" +
                "R18(.1/30, f0/0)->R19(.2/30, f1/0);" +
            "}";

    final String GBlk_2 =
            "Router R18, R19;" +
            "Group area3{" +
                "IP 10.3.2.0;" +
                "R18(.1/30, f0/0)->R19(.2/30, f1/0);" +
            "}" +
            "Group ptp{" +
                "IP 11.3.2.0;" +
                "Setting OSPF Network point-to-point;" +
                "R18(.1/30, f0/0)->R19(.2/30, f1/0);" +
            "}";

    @Test
    void IP() {
        // Assert that all the shorthand IP evaluates correctly
        ASTfactory.createFromString(GBlk).apply(semanticsVisitor);
        List<IpAddress> ipAdds = new ArrayList<>();
        semanticsVisitor.envR.getRouters().forEach(r -> r.getInterfaces().forEach(ix -> ipAdds.add(ix.getNetworkAddress())));
        IpAddress validationIp = new IpAddress(10,3,2,0);
        for(IpAddress ipAddress : ipAdds) {
            assertEquals(validationIp, ipAddress);
        }
    }

    @Test
    void IP_2() {
        // Assert that the IP addresses on the router's interfaces are all correctly derived from the group
        ASTfactory.createFromString(GBlk_2).apply(semanticsVisitor);
        List<IpAddress> ipAdds = new ArrayList<>();
        semanticsVisitor.envR.getRouters().forEach(r -> r.getInterfaces().forEach(ix -> ipAdds.add(ix.getNetworkAddress())));
        IpAddress validationIp = new IpAddress(11,3,2,0);
        for(IpAddress ipAddress : ipAdds) {
            assertEquals(validationIp, ipAddress);
        }
    }
}
