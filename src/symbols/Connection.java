package symbols;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Connection {
    List<PhysicalInterface> interfaces; //Interfaces know their routers.

    public Connection(PhysicalInterface... interfaces) {
        this.interfaces = Arrays.asList(interfaces);
    }
}
