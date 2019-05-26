package symbols;

public enum InterfaceType {

    FAST_ETHERNET("FastEthernet"),
    ETHERNET("Ethernet"),
    GIGABIT("GigaBit");

    private final String name;

    private InterfaceType(String name){
        this.name = name;
    }

    public String toString(){
        return name;
    }
}
