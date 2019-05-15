package symbols;

import com.dat405.nldl.node.AOneIf;
import com.dat405.nldl.node.AOneIp;
import com.dat405.nldl.node.ATwoIf;
import com.dat405.nldl.node.PIf;

import java.util.Objects;

public class InterfaceIndex {
    private InterfaceType type;
    private int index1;
    private int index2;


    public InterfaceIndex(PIf inf) {
        if (inf instanceof AOneIf) {
            AOneIf oneIf = (AOneIf) inf;
            this.index1 = Integer.valueOf(oneIf.getFirst().getText());
            this.index2 = -1;

            setInterfaceType(oneIf.getInterfaceType().getText());
        } else {
            ATwoIf twoIf = (ATwoIf) inf;
            this.index1 = Integer.valueOf(twoIf.getFirst().getText());
            this.index2 = Integer.valueOf(twoIf.getSecond().getText());

            setInterfaceType(twoIf.getInterfaceType().getText());
        }
    }

    private void setInterfaceType(String ifString){
        switch (ifString.toUpperCase()){
            case "F":
            case "FE": type = InterfaceType.FAST_ETHERNET; break;
            case "G":
            case "GB": type = InterfaceType.GIGABIT; break;
            case "E": type = InterfaceType.ETHERNET; break;
            default: throw new RuntimeException("Unsupported interface type");
        }
    }


    public int getIndex1() {
        return this.index1;
    }

    public int getIndex2() {
        return this.index2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InterfaceIndex that = (InterfaceIndex) o;
        return index1 == that.index1 &&
                index2 == that.index2;
    }

    @Override
    public int hashCode() {
        return Objects.hash(index1, index2);
    }

    public InterfaceType getInterfaceType() {
        return type;
    }
}
