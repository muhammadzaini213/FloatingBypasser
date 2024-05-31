package com.ibnucoding.iceloating.dashboard;

public class DashboardUtils {
    /*
    This Static Class act as an connector for DashboardFragments and services,
    Anything that needed to transferred to services from DashboardFragments
    would placed here first.
     */
    static boolean bUNFOCUS_ACTIVE;

    public static boolean getUnfocusBoolean() {
        return bUNFOCUS_ACTIVE;
    }

    public static void setUnfocusBoolean(boolean UNFOCUS_ACTIVE) {
        bUNFOCUS_ACTIVE = UNFOCUS_ACTIVE;
    }


    static boolean bIsPremium;

    public static void itIsPremium(boolean isPremium) {
        bIsPremium = isPremium;
    }

    public static boolean isPremium() {
        return bIsPremium;
    }

    static boolean bHIDDEN_MODE;

    public static boolean getHiddenMode() {
        return bHIDDEN_MODE;
    }

    public static void setHiddenMode(boolean HIDDEN_MODE) {
        bHIDDEN_MODE = HIDDEN_MODE;
    }


    static String sHome_URL;

    public static String getHome_URL() {
        return sHome_URL;
    }

    public static void setHome_URL(String HOME_URL) {
        sHome_URL = HOME_URL;
    }


    static boolean bANTI_OBSCURE;

    public static void setAntiObscure(boolean ANTI_OBSCURE) {
        bANTI_OBSCURE = ANTI_OBSCURE;
    }

    public static boolean getANTI_OBSCURE() {
        return bANTI_OBSCURE;
    }

    static boolean bUSE_VIBRATION;

    public static void setUSE_VIBRATION(boolean USE_VIBRATION) {
        bUSE_VIBRATION = USE_VIBRATION;
    }

    public static boolean getUSE_VIBRATION() {
        return bUSE_VIBRATION;
    }


    static boolean bDOUBLE_SAFETY;

    public static void setDOUBLE_SAFETY(boolean DOUBLE_SAFETY) {
        bDOUBLE_SAFETY = DOUBLE_SAFETY;
    }

    public static boolean getDOUBLE_SAFETY(){
        return bDOUBLE_SAFETY;
    }
}
