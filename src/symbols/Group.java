package symbols;

import com.dat405.nldl.node.PIp;

import java.util.HashSet;
import java.util.Set;

public class Group {
    private Group parent;
    private Set<? extends Setting> settings = new HashSet<>();
    private IpAddress ip = null;

    public Group(Group parent) {
        this.parent = parent;
    }


    public IpAddress getIp(){
        if(ip==null){
            return parent.getIp();
        }
        else return ip;
    }

    public void setIp(PIp pip){
        this.ip = new IpAddress(pip, this);
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
