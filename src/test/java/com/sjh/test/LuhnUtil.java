package com.sjh.test;

import java.util.regex.Pattern;

/**
 * ClassName: Luhn <br/>
 * Description: <br/>
 * date: 2020/6/17 15:07<br/>
 *
 * @author ex-sujh<br/>
 * @since JDK 12
 */
public class LuhnUtil {

    int[] num;

    public LuhnUtil() {
        num = new int[21];
        for (int i = 0; i < num.length; i++) {
            num[i] = 0;
        }
    }

    public LuhnUtil(int n) {
        num = new int[n];
    }

    public LuhnUtil(String str) {
        num = new int[str.length()];
        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) {
                this.setElem(i, Integer.parseInt(str.substring(i, i + 1)));
            } else {
                this.setElem(i, str.charAt(i) - 64);
            }
        }
    }

    public void setElem(int index, int n) {
        num[index] = n;
    }

    public int plusOne() {
        int total = 0;
        boolean need = true;
        for (int i = num.length - 1; i >= 0; i--) {
            int number = num[i];
            if (need) {
                total += number * 2 / 10 + number * 2 % 10;
                need = false;
            } else {
                total += number;
                need = true;
            }
        }
        return total % 10;
    }
}
