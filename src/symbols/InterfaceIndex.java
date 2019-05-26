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

    public InterfaceIndex(int first, int second, InterfaceType type) {
        this.index1 = first;
        this.index2 = second;
        this.type = type;
    }

    public InterfaceIndex(int first, InterfaceType type) {
        this.index1 = first;
        this.index2 = -1;
        this.type = type;
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

    @Override
    public String toString() {
        return type.name() + " "  + index1 + "/" + index2;
    }

    public InterfaceType getInterfaceType() {
        return type;
    }
}
