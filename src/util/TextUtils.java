package util;

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
}
