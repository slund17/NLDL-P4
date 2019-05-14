package recognizers;

import com.dat405.nldl.node.PS;

import java.util.List;

public interface ClassCreator<T> {
    T create(List<PS> list);
}
