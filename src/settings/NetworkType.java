package settings;

public enum NetworkType {
    POINT_TO_POINT("Point-to-Point"),
    POINT_TO_MULTIPOINT("Point-to-Multipoint"),
    POINT_TO_MULTIPOINT_NON_BROADCAST("Point-to-Multipoint Non-Broadcast"),
    BROADCAST("Broadcast"),
    NON_BROADCAST("Non-Broadcast")
    ;

    private final String name;

    private NetworkType(String name){
        this.name = name;
    }

    public String toString(){
        return name;
    }
}
