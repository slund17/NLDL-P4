package symbols;

import com.dat405.nldl.node.*;
import recognizers.Predicates;
import recognizers.SettingRecognizer;
import settings.AreaSetting;
import settings.DeadIntervalSetting;
import settings.HelloIntervalSetting;
import settings.StubAreaSetting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

public class Setting {

    public static List<SettingRecognizer<? extends Setting>> OSPF_settings = new ArrayList<>();
    public static List<SettingRecognizer<? extends Setting>> ROUTER_settings;

    static{
        OSPF_settings.addAll(Arrays.asList(
                //OSPF Area %num%
                new SettingRecognizer<AreaSetting>(l->{
                    int num = Integer.valueOf(((ANumS)l.get(1)).getConst().getText());
                    return new AreaSetting(num);
                }, Predicates.isIdentifier("Area"), Predicates.isPosNum()),
                //OSPF Area %num% Stub
                new SettingRecognizer<StubAreaSetting>(l->{
                    int num = Integer.valueOf(((ANumS)l.get(1)).getConst().getText());
                    return new StubAreaSetting(num);
                }, Predicates.isIdentifier("Area"), Predicates.isPosNum(), Predicates.isIdentifier("Stub")),
                //OSPF hello-interval %num%
                new SettingRecognizer<HelloIntervalSetting>(l->{
            int num = Integer.valueOf(((ANumS)l.get(1)).getConst().getText());
            return new HelloIntervalSetting(num);
        }, Predicates.isIdentifier("hello-interval"), Predicates.isPosNum()),
                //OSPF dead-interval %num%
                new SettingRecognizer<DeadIntervalSetting>(l->{
                    int num = Integer.valueOf(((ANumS)l.get(1)).getConst().getText());
                    return new DeadIntervalSetting(num);
                }, Predicates.isIdentifier("dead-interval"), Predicates.isPosNum())
        ) );

        ROUTER_settings.addAll(Arrays.asList(

        ) );
    }

    public static Setting getSetting(ASettingBlock settingBlock){
        switch (settingBlock.getProtocol().getText().toUpperCase()){
            case "OSPF": return findMatch(OSPF_settings, settingBlock);
            case "ROUTER": return findMatch(ROUTER_settings, settingBlock);
            default: throw new RuntimeException("Unknown Protocol.");
        }
    }

    private static Setting findMatch(List<SettingRecognizer<? extends Setting>> recognizers, ASettingBlock settingBlock){
        for (SettingRecognizer<? extends Setting> recognizer : recognizers){
            if(recognizer.isValid(settingBlock.getS())){
                return recognizer.create(settingBlock.getS());
            }
        }
        throw new RuntimeException("Unknown setting");
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
