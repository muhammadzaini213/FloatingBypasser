package com.ibnucoding.iceloating.window.floatingwindow.bottomnavbar;

public class SafetyCounter {
    static int counter = 0;

    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int count) {
        counter = count + 1;
    }
}
