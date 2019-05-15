package symbols;

import com.dat405.nldl.node.*;

public class IpAddress {
    public final int seg1, seg2, seg3, seg4;

    public IpAddress(PIp ipProduction, Group group) {
        if(ipProduction instanceof AFourIp){
            AFourIp ip = (AFourIp) ipProduction;
            this.seg1 = Integer.valueOf(ip.getSeg1().getText());
            this.seg2 = Integer.valueOf(ip.getSeg2().getText());
            this.seg3 = Integer.valueOf(ip.getSeg3().getText());
            this.seg4 = Integer.valueOf(ip.getSeg4().getText());
        } else if(ipProduction instanceof AThreeIp){
            AThreeIp ip = (AThreeIp) ipProduction;
            this.seg1 = group.getIp().seg1;
            this.seg2 = Integer.valueOf(ip.getSeg2().getText());
            this.seg3 = Integer.valueOf(ip.getSeg3().getText());
            this.seg4 = Integer.valueOf(ip.getSeg4().getText());
        } else if(ipProduction instanceof ATwoIp){
            ATwoIp ip = (ATwoIp) ipProduction;
            this.seg1 = group.getIp().seg1;
            this.seg2 = group.getIp().seg2;
            this.seg3 = Integer.valueOf(ip.getSeg3().getText());
            this.seg4 = Integer.valueOf(ip.getSeg4().getText());
        } else if (ipProduction instanceof AOneIp){
            AOneIp ip = (AOneIp) ipProduction;
            this.seg1 = group.getIp().seg1;
            this.seg2 = group.getIp().seg2;
            this.seg3 = group.getIp().seg3;
            this.seg4 = Integer.valueOf(ip.getSeg4().getText());
        } else {
            throw new RuntimeException("Wrong ip");
        }


    }
}
