package pathmatch;

import bean.Buliding;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 建筑物读取
 * Created by jiajie on 15/11/10.
 */
public class BulidReader {
    public static Map<Set, Buliding>read(String fileName){
        Map<Set,Buliding> map=new HashMap<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line = br.readLine();

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
