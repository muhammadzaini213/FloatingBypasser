package com.mzfreeapp.floatingbrowser.dashboard.setup;

public class DashboardData {
    private final String dashboardTitle;
    private final String dashboardDesc;
    private final String stringEditText;
    private final boolean isChecked;
    private final int viewType;

    /*
    This is core part for recyclerview, don't mess up.
     */

    public DashboardData(String dashboardTitle, String dashboardDesc, String stringEditText, boolean isChecked, int viewType) {
        this.dashboardTitle = dashboardTitle;
        this.dashboardDesc = dashboardDesc;
        this.stringEditText = stringEditText;
        this.isChecked = isChecked;
        this.viewType = viewType;

    }

    public String getDashboardTitle() {
        return dashboardTitle;
    }

    public String getDashboardDesc() {
        return dashboardDesc;
    }

    public String getStringEditText() {
        return stringEditText;
    }

    public boolean switchStatus() {
        return isChecked;
    }

    public int getViewType() {
        return viewType;
    }

}
