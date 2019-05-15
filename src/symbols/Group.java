package symbols;

import settings.Setting;

import java.util.HashSet;
import java.util.Set;

public class Group {
    private Group parent;
    private Set<Setting> settings = new HashSet<>();
    private IpAddress ip = null;

    public Group(Group parent) {
        this.parent = parent;
    }


    public IpAddress getIp(){
        if(ip==null){
            if(parent == null) throw new RuntimeException("Ip shorthand used while ip was not defined");
            return parent.getIp();
        }
        else return ip;
    }

    public void setIp(IpAddress ip){
        this.ip = ip;
    }

    public void addSetting(Setting setting){
        this.settings.add(setting);
    }

    public Set<? extends Setting> getSettings(){
        Set<Setting> set = new HashSet<>(settings);
        if(parent !=null){
            set.addAll(parent.getSettings());
        }
        return set;
    }

    public Group getParent(){
        return this.parent;
    }
}
