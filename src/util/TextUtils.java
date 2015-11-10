package util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * 字符串处理工具类
 * Created by jiajie on 15/10/22.
 */


public class TextUtils {
    private TextUtils(){

    }

    /**
     * 字符串判空函数
     * @param str 字符串
     * @return 是否为空
     */
    public static boolean isEmpty(CharSequence str) {
        return str == null || str.length() == 0;
    }
    //汉字返回拼音，字母原样返回，都转换为小写(默认取得的拼音全大写)
    public static String getPinYin(String input) {
        ArrayList<HanziToPinyin.Token> tokens = HanziToPinyin.getInstance().get(input);
        StringBuilder sb = new StringBuilder();
        if (tokens != null && tokens.size() > 0) {
            for (HanziToPinyin.Token token : tokens) {
                if (HanziToPinyin.Token.PINYIN == token.type) {
                    sb.append(token.target);
                } else {
                    sb.append(token.source);
                }
            }
        }
        return sb.toString().toLowerCase();
    }

    public static Set<String> string2set(String key){
        Set<String> keySet=new HashSet<>();
        char[] temps=key.toCharArray();
        for (char c:temps){
            keySet.add(c+"");
        }

        return keySet;
    }


}
