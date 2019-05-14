package symbols;

import com.dat405.nldl.node.AOneIf;
import com.dat405.nldl.node.ATwoIf;
import com.dat405.nldl.node.PIf;

import java.util.Objects;

public class InterfaceIndex {
    private int index1;
    private int index2;


    public InterfaceIndex(PIf inf) {
        if (inf instanceof AOneIf) {
            this.index1 = Integer.valueOf(((AOneIf) inf).getFirst().getText());
            this.index2 = -1;

        } else {
            this.index1 = Integer.valueOf(((ATwoIf) inf).getFirst().getText());
            this.index2 = Integer.valueOf(((ATwoIf) inf).getSecond().getText());
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
}
