package com.ibnucoding.iceloating.dashboard;

public class DashboardUtils {
    /*
    This Static Class act as an connector for DashboardFragments and services,
    Anything that needed to transferred to services from DashboardFragments
    would placed here first.
     */
    static boolean bUNFOCUS_ACTIVE;
    static boolean bIsPremium;
    static boolean bHIDDEN_MODE;
    static String sHome_URL;
    static boolean bANTI_OBSCURE;
    static boolean bANTI_OBSCURE_SAFETY;
    static boolean bUSE_VIBRATION;
    static boolean bDOUBLE_SAFETY;
    static boolean bBOTTLE_OPENER;

    public static boolean getUnfocusBoolean() {
        return bUNFOCUS_ACTIVE;
    }

    public static void setUnfocusBoolean(boolean UNFOCUS_ACTIVE) {
        bUNFOCUS_ACTIVE = UNFOCUS_ACTIVE;
    }

    public static void itIsPremium(boolean isPremium) {
        bIsPremium = isPremium;
    }

    public static boolean isPremium() {
        return bIsPremium;
    }

    public static boolean getHiddenMode() {
        return bHIDDEN_MODE;
    }

    public static void setHiddenMode(boolean HIDDEN_MODE) {
        bHIDDEN_MODE = HIDDEN_MODE;
    }

    public static String getHome_URL() {
        return sHome_URL;
    }

    public static void setHome_URL(String HOME_URL) {
        sHome_URL = HOME_URL;
    }

    public static void setAntiObscure(boolean ANTI_OBSCURE) {
        bANTI_OBSCURE = ANTI_OBSCURE;
    }

    public static boolean getANTI_OBSCURE() {
        return bANTI_OBSCURE;
    }

    public static void setAntiObscureSafety(boolean ANTI_OBSCURE_SAFETY) {
        bANTI_OBSCURE_SAFETY = ANTI_OBSCURE_SAFETY;
    }

    public static boolean getANTI_OBSCURE_SAFETY() {
        return bANTI_OBSCURE_SAFETY;
    }

    public static boolean getUSE_VIBRATION() {
        return bUSE_VIBRATION;
    }

    public static void setUSE_VIBRATION(boolean USE_VIBRATION) {
        bUSE_VIBRATION = USE_VIBRATION;
    }

    public static boolean getDOUBLE_SAFETY() {
        return bDOUBLE_SAFETY;
    }

    public static void setDOUBLE_SAFETY(boolean DOUBLE_SAFETY) {
        bDOUBLE_SAFETY = DOUBLE_SAFETY;
    }

    public static boolean getBOTTLE_OPENER() {
        return bBOTTLE_OPENER;
    }

    public static void setBOTTLE_OPENER(boolean BOTTLE_OPENER) {
        bBOTTLE_OPENER = BOTTLE_OPENER;
    }


}
