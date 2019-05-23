package settings;

import recognizers.Predicates;
import recognizers.SettingRecognizer;
import symbols.InterfaceIndex;
import symbols.IpAddress;

import javax.sql.rowset.Predicate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@SuppressWarnings({"unchecked"})
public abstract class Setting {

    private final SettingType settingType;

    protected Setting(SettingType settingType) {
        this.settingType = settingType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Setting)) return false;
        Setting setting = (Setting) o;
        return settingType == setting.settingType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(settingType);
    }

    private static List<SettingRecognizer<? extends Setting>> OSPF_settings = new ArrayList<>();
    private static List<SettingRecognizer<? extends Setting>> OTHER_settings = new ArrayList<>();

    static {
        OSPF_settings.addAll(Arrays.asList(
                //OSPF Area %num%
                new SettingRecognizer<AreaSetting>(l -> {
                    return new AreaSetting((Integer) l.get(1));
                }, Predicates.isIdentifier("Area"), Predicates.isPosNum()),
                //OSPF Area %num% Stub
                new SettingRecognizer<StubAreaSetting>(l -> {
                    return new StubAreaSetting((Integer) l.get(1));
                }, Predicates.isIdentifier("Area"), Predicates.isPosNum(), Predicates.isIdentifier("Stub")),
                //OSPF Area %num% totally-stub
                new SettingRecognizer<TotallyStubAreaSetting>(l -> {
                    return new TotallyStubAreaSetting((Integer) l.get(1));
                }, Predicates.isIdentifier("Area"), Predicates.isPosNum(), Predicates.isIdentifier("totally-stub")),
                //OSPF Area %num% nssa
                new SettingRecognizer<NssaAreaSetting>(l -> {
                    return new NssaAreaSetting((Integer) l.get(1));
                }, Predicates.isIdentifier("Area"), Predicates.isPosNum(), Predicates.isIdentifier("nssa")),
                new SettingRecognizer<TotallyNssaAreaSetting>(l -> {
                    return new TotallyNssaAreaSetting((Integer) l.get(1));
                }, Predicates.isIdentifier("Area"), Predicates.isPosNum(), Predicates.isIdentifier("totally-nssa")),
                //OSPF Network %type%
                new SettingRecognizer<NetworkTypeSetting>(l -> {
                    return new NetworkTypeSetting((String) l.get(1));
                }, Predicates.isIdentifier("Network"), Predicates.isIdentifierAnyOf("point-to-point", "broadcast", "non-broadcast", "point-to-multipoint")),
                //OSPF Network point-to-multipoint non-broadcast
                new SettingRecognizer<NetworkTypeSetting>(l -> {
                    return new NetworkTypeSetting((String) l.get(1), (String) l.get(2));
                }, Predicates.isIdentifier("Network"), Predicates.isIdentifier("point-to-multipoint"), Predicates.isIdentifier("non-broadcast")),
                //OSPF hello-interval %num%
                new SettingRecognizer<HelloIntervalSetting>(l -> {
                    return new HelloIntervalSetting((Integer) l.get(1));
                }, Predicates.isIdentifier("hello-interval"), Predicates.isPosNum()),
                //OSPF dead-interval %num%
                new SettingRecognizer<DeadIntervalSetting>(l -> {
                    return new DeadIntervalSetting((Integer) l.get(1));
                }, Predicates.isIdentifier("dead-interval"), Predicates.isPosNum()),
                //OSPF priority %num%
                new SettingRecognizer<PrioritySetting>(l -> {
                    return new PrioritySetting((Integer) l.get(1));
                }, Predicates.isIdentifier("priority"), Predicates.isPosNum()),

                new SettingRecognizer<DefaultMetricSetting>(l -> {
                    return new DefaultMetricSetting((Integer) l.get(1));
                }, Predicates.isIdentifier("Default-Metric"), Predicates.isPosNum())
        ));

        OTHER_settings.addAll(Arrays.asList(
                new SettingRecognizer<DNSServerSetting>(l -> {
                    IpAddress ip = (IpAddress) l.get(2);
                    return new DNSServerSetting(ip);
                }, Predicates.isIdentifier("DNS"), Predicates.isIdentifier("server"), Predicates.isIp()),
                new SettingRecognizer<RouterIDSetting>(l -> {
                    IpAddress ip = (IpAddress) l.get(2);
                    return new RouterIDSetting(ip);
                }, Predicates.isIdentifier("Router"), Predicates.isIdentifier("ID"), Predicates.isIp())
        ));
    }

    public static Setting getSetting(List<Object> objects) {

        if (!(objects.get(0) instanceof String)) return findMatch(OTHER_settings, objects);

        switch (((String) objects.get(0)).toUpperCase()) {
            //If it starts with OSPF remove the first element and find match in ospf settings.
            case "OSPF":
                return findMatch(OSPF_settings, objects.subList(1, objects.size()));
            default:
                return findMatch(OTHER_settings, objects);
        }
    }

    private static Setting findMatch(List<SettingRecognizer<? extends Setting>> recognizers, List<Object> objects){
        for (SettingRecognizer<? extends Setting> recognizer : recognizers){
            if(recognizer.isValid(objects)){
                return recognizer.create(objects);
            }
        }

        throw new RuntimeException("Unknown setting: " + objects);
    }



    //[OSPF, Area, num, Stub]
    //(x->x.text()=="OSPF", x->x.text()=="Area", x->x instanceof TConst, x->x.text()=="Stub)
    //(l-> new HelloIntervalSetting(l.get(2)), x->x.text()

/*

    public static Setting getType(ASettingBlock settingBlock){
        ListIterator<PS> iterator = settingBlock.getS().listIterator();
        PS current = getNext(iterator);

        switch (settingBlock.getProtocol().getText()){
            case "OSPF":
                return getOspfType(iterator);
            case "Router":
                return getRouterType(iterator);
            default: throw new RuntimeException("Unknown protocol");
        }
    }

    private static Setting getRouterType(ListIterator<PS> iterator) {
        PS current = getNext(iterator);
        if(current instanceof AIdentifierS) {
            switch (((AIdentifierS) current).getIdentifier().getText()) {
                case "DNS":
                    return getDNSType(iterator);
                case "router-id":
                    return getRIDType(iterator);
                default:
                    throw new RuntimeException("Unknown Router setting");
            }
        }

        throw new RuntimeException("Expected token");
    }

    private static Setting getOspfType(ListIterator<PS> iterator){
        PS current = getNext(iterator);
        if(current instanceof AIdentifierS) {
            switch (((AIdentifierS) current).getIdentifier().getText()) {
                case "Area":
                    return getAreaType(iterator);
                case "hello-interval":
                    return getHelloType(iterator);
                case "dead-interval":
                    return getDeadType(iterator);
                case "Network":
                    return getNetworkType(iterator);
                default: throw new RuntimeException("Unknown OSPF setting");
            }
        }
        throw new RuntimeException("Unknown token");
    }

    private static Setting getRIDType(ListIterator<PS> iterator) {
        PS current = getNext(iterator);
        if(current instanceof ANumS) {

            int value = Integer.valueOf(((ANumS) current).getConst().getText());
            if(!iterator.hasNext()){
                return new RouterIDSetting(value);
            } else {
                throw new RuntimeException("Unknown token after number");
            }
        }
        throw new RuntimeException("Expected number");
    }

    private static Setting getDNSType(ListIterator<PS> iterator) {
        PS current = getNext(iterator);
        if(current instanceof AIpS) {
            IPAdresss ip = IPAddress.from((AIpS)current);
            if(!iterator.hasNext()){
                return new DNSSetting(ip);
            } else {
                throw new RuntimeException("Unknown token after number");
            }
        }
        throw new RuntimeException("Expected number");
    }



    private static Setting getNetworkType(ListIterator<PS> iterator) {
        PS current = getNext(iterator);
        if(current instanceof AIdentifierS) {
            if(!iterator.hasNext()){
                switch (((AIdentifierS)current).getIdentifier().getText()){
                    case "broadcast":
                        return new BroadcastNetworkSetting();
                    case "point-to-point":
                        return new PointToPointNetworkSetting();
                }
            } else {
                throw new RuntimeException("Unknown token after number");
            }
        }
        throw new RuntimeException("Expected number");
    }

    private static Setting getDeadType(ListIterator<PS> iterator) {
        PS current = getNext(iterator);
        if(current instanceof ANumS) {

            int value = Integer.valueOf(((ANumS) current).getConst().getText());
            if(!iterator.hasNext()){
                return new DeadIntervalSetting(value);
            } else {
                throw new RuntimeException("Unknown token after number");
            }
        }
        throw new RuntimeException("Expected number");
    }

    private static Setting getHelloType(ListIterator<PS> iterator) {
        PS current = getNext(iterator);
        if(current instanceof ANumS) {

            int value = Integer.valueOf(((ANumS) current).getConst().getText());
            if(!iterator.hasNext()){
                return new HelloIntervalSetting(value);
            } else {
                throw new RuntimeException("Unknown token after number");
            }
        }
        throw new RuntimeException("Expected number");
    }

    private static Setting getAreaType(ListIterator<PS> iterator) {
        PS current = getNext(iterator);
        if(current instanceof ANumS){

            int value = Integer.valueOf(((ANumS)current).getConst().getText());

            current = getNext(iterator);
            if (current == null){
                return new AreaSetting(value);}
            if(current instanceof AIdentifierS){
                if(!iterator.hasNext()){
                switch (((AIdentifierS)current).getIdentifier().getText()){
                    case "Stub": return new StubAreaSetting(value);
                    case "Totally-Stub": return new TotallyStubAreaSetting(value);
                }} else {
                    throw new RuntimeException("Unknown token following");
                }
            }
            throw new RuntimeException("Expected identifier");
        }
        throw new RuntimeException("Expected number");
    }

    private static PS getNext(ListIterator<PS> iterator){
        if (!iterator.hasNext())
            return null;
        return iterator.next();
    }
*/
}
