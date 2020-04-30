package top.stuck.link.core.utils;

import java.util.Random;

/**
 * Created on 2020-04-27
 *
 * @author Octopus
 */
public class CodeUtil {

    private static final char[] BASE_62_CHARS = "abcdefghzjklmnopqrstuvwxyzABCDEFGHZJKLMNOPQRSTUVWXYZ0123456789".toCharArray();

    private static final int BASE_62_CHARS_LENGTH = BASE_62_CHARS.length;

    public static String getCode(){
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for(int i = 0; i < 6; i++) {
            int index = random.nextInt(BASE_62_CHARS_LENGTH);
            sb.append(BASE_62_CHARS[index]);
        }
        return sb.toString();
    }

}
