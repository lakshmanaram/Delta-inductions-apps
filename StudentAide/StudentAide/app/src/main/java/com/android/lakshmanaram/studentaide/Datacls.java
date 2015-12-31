package com.android.lakshmanaram.studentaide;

/**
 * Created by lakshmanaram on 21/7/15.
 */
public class Datacls {
    private static int present,absent;
    private static boolean close;
    public static void setvalues(int a, int b)
    {
        present = a;
        absent = b;
    }

    public static int getAbsent() {
        return absent;
    }

    public static int getPresent() {
        return present;
    }

    public static boolean isClose() {
        return close;
    }
    public static void setClose(boolean a)
    {
        close = a;
    }
}
