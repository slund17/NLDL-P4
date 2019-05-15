package recognizers;

import com.dat405.nldl.node.PS;
import symbols.Setting;

import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

public class SettingRecognizer<T extends Setting> {
    public ClassCreator<T> classCreator;
    public List<TokenPredicate> predicates;

    public SettingRecognizer(ClassCreator<T> creator, TokenPredicate... predicates){
        this.classCreator = creator;
        this.predicates = Arrays.asList(predicates);
    }

    public boolean isValid(List<PS> psList){
        if(psList.size() != predicates.size()) return false;

        ListIterator<PS> psIterator = psList.listIterator();
        ListIterator<TokenPredicate> predIterator = predicates.listIterator();

        while (psIterator.hasNext()){
            if(!predIterator.next().test(psIterator.next())) return false;
        }

        return true;
    }

    public T create(List<PS> psList){
        return classCreator.create(psList);
    }
}
