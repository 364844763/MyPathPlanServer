import bean.Buliding;
import pathmatch.Buildmatch;
import pathmatch.BulidReader;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by jiajie on 15/11/16.
 */
public class Test {
    public static void main(String[] args){
        Map<Set, Buliding> bulidings;
        bulidings= BulidReader.read("D:\\test.csv");
        String name="公园";
        Buildmatch buildmatch=new Buildmatch(bulidings);
        List<Buliding> result=buildmatch.fuzzyMatch(name);
        for (Buliding b:result){
            System.out.println(b);
        }
    }
}
