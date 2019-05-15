package recognizers;

import com.dat405.nldl.node.PS;
import symbols.Setting;

import java.util.List;

public interface ClassCreator<T extends Setting> {
    T create(List<PS> list);
}
