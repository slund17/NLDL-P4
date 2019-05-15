package recognizers;

import settings.Setting;

import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.function.Predicate;

public class SettingRecognizer<T extends Setting> {
    public ClassCreator<T> classCreator;
    public List<Predicate<Object>> predicates;

    public SettingRecognizer(ClassCreator<T> creator, Predicate<Object>... predicates){
        this.classCreator = creator;
        this.predicates = Arrays.asList(predicates);
    }

    public boolean isValid(List<Object> objects){
        if(objects.size() != predicates.size()) return false;

        ListIterator<Object> psIterator = objects.listIterator();
        ListIterator<Predicate<Object>> predIterator = predicates.listIterator();

        while (psIterator.hasNext()){
            if(!predIterator.next().test(psIterator.next())) return false;
        }

        return true;
    }

    public T create(List<Object> objects){
        return classCreator.create(objects);
    }
}
