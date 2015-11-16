package pathmatch;

import bean.Buliding;
import util.TextUtils;

import java.util.*;

/**
 * Created by jiajie on 15/11/10.
 */
public class Buildmatch {
    private List<Buliding> fuzzyBulidings;
    private Map<Set,Buliding> bulidingMap;

    public Buildmatch(Map<Set, Buliding> bulidingMap) {
        this.bulidingMap = bulidingMap;
    }

    public List<Buliding>  fuzzyMatch(String key){
        fuzzyBulidings=new ArrayList<>();
        Iterator iterator=bulidingMap.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry entry = (Map.Entry) iterator.next();
            Set<String> keySet= (Set<String>) entry.getKey();
            System.out.println();
            int i=keySet.size();
            System.out.println(i);
            keySet.addAll(TextUtils.string2set(key));
            if (i==keySet.size()){
                fuzzyBulidings.add((Buliding) entry.getValue());
            }
        }
        return fuzzyBulidings;
    }
}
