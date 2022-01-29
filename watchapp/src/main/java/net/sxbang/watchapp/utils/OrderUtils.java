package net.sxbang.watchapp.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class OrderUtils {

    public static String getOrderNumber() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        StringBuilder dateString = new StringBuilder(formatter.format(date));
        Random random = new Random();
        Integer number = random.nextInt(900) + Integer.valueOf(100);
        dateString.append(number);
        return dateString.toString();
    }

    public static String generatorCode() {
        return getCharacterAndNumber(16);
    }

    /**
     * 产生16位随机数字+大小写字母
     *
     * @param length
     * @return
     */
    public static String getCharacterAndNumber(int length) {
        StringBuffer val = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num"; // 输出字母还是数字
            if ("char".equalsIgnoreCase(charOrNum)) { // 字符串
                int choice = random.nextInt(2) % 2 == 0 ? 65 : 97; //取得大写字母还是小写字母
                val.append((choice + random.nextInt(26)));
            } else if ("num".equalsIgnoreCase(charOrNum)) { // 数字
                val.append(random.nextInt(10));
            }
        }
        return val.toString();
    }
}
