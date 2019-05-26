package symbols;

import com.dat405.nldl.node.*;

import java.util.Objects;

public class IpAddress {
    public final int seg1, seg2, seg3, seg4;

    public IpAddress(int seg1, int seg2, int seg3, int seg4) {
        this.seg1 = seg1;
        this.seg2 = seg2;
        this.seg3 = seg3;
        this.seg4 = seg4;
    }

    public IpAddress inversed(){
        return new IpAddress(255-seg1, 255-seg2, 255-seg3, 255-seg4);
    }

    public IpAddress bitAnd(IpAddress mask){
        return new IpAddress(seg1 & mask.seg1, seg2 & mask.seg2, seg3 & mask.seg3, seg4 & mask.seg4);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IpAddress ipAddress = (IpAddress) o;
        return seg1 == ipAddress.seg1 &&
                seg2 == ipAddress.seg2 &&
                seg3 == ipAddress.seg3 &&
                seg4 == ipAddress.seg4;
    }

    @Override
    public int hashCode() {
        return Objects.hash(seg1, seg2, seg3, seg4);
    }

    @Override
    public String toString() {
        return Integer.toString(seg1) + "." + Integer.toString(seg2) + "." + Integer.toString(seg3) + "." + Integer.toString(seg4);
    }
}
