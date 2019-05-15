package recognizers;

import settings.Setting;

import java.util.List;

public interface ClassCreator<T extends Setting> {
    T create(List<Object> list);
}
