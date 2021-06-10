package com.sjh.test;

/**
 * ClassName: SimFeatureUtil <br/>
 * Description: <br/>
 * date: 2020/6/19 17:46<br/>
 *
 * @author ex-sujh<br/>
 * @since JDK 12
 */
public class SimFeatureUtil {
    private static int min(int one, int two, int three) {
        int min = one;
        if (two < min) {
            min = two;
        }
        if (three < min) {
            min = three;
        }
        return min;
    }

    public static int ld(String str1, String str2) {
        int d[][];
        int n = str1.length();
        int m = str2.length();
        int i;
        int j;
        char ch1;
        char ch2;
        int temp;
        if (n == 0) {
            return m;
        }

        if (m == 0) {
           return n;
        }
        d = new int[n + 1][m + 1];
        for (i = 0; i <=n; i++) {
            d[i][0] = i;
        }
        for (j = 0; j <= m; j++) {
            d[0][j] = j;
        }
        for (i = 1; i<=n; i++) {
            ch1 = str1.charAt(i - 1);
            // 匹str2
            for (j = 1; j <=m; j++) {
                ch2 = str2.charAt(j - 1);
                if (ch1 == ch2) {
                    temp = 0;
                } else {
                    temp = 1;
                }
                d[i][j] = min(d[i - 1][j] + 1, d[i][j - 1] + 1, d[i - 1][j - 1] + temp);
            }
        }
        return d[n][m];
    }

    public static double sim(String str1, String str2) {
        try {
            long start = System.currentTimeMillis();
            System.out.println("start: " + start);
            double ld = (double) ld(str1, str2);
            double r = 1 - ld / (double) Math.max(str1.length(), str2.length());
            long end = System.currentTimeMillis();
            System.out.println("end: " + end);
            System.out.println(end - start);
            return r;
        } catch (Exception e){
            return 0.1;
        }
    }
}
