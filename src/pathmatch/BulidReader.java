package pathmatch;

import bean.Buliding;
import util.TextUtils;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 建筑物读取
 * Created by jiajie on 15/11/10.
 */
public class BulidReader {
    public static Map<Set, Buliding> read(String fileName){
        Map<Set,Buliding> map=new HashMap<>();
        try {
            File f = new File(fileName);
            InputStreamReader read = new InputStreamReader(new FileInputStream(f),"UTF-8");
            BufferedReader br = new BufferedReader(read);
            String line = br.readLine();
            while (line!=null){
                String[] temp=line.split(",");
                //System.out.println(line);
                Set<String> keySet= TextUtils.string2set(temp[0]);
                Buliding buliding=new Buliding();
                buliding.setName(temp[0]);
                buliding.setLongitude(Double.parseDouble(temp[1]));
                buliding.setLatitude(Double.parseDouble(temp[2]));
                map.put(keySet, buliding);
                line = br.readLine();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }
}
