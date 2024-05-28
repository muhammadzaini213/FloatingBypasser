package com.ibnucoding.iceloating.terminal.setup;


public class TerminalData {
    String shellText;
    int color;
    final int shellType;

    public TerminalData(String shellText, int color, int shellType) {
        this.shellText = shellText;
        this.color = color;
        this.shellType = shellType;
    }

    public String getShellText() {
        return shellText;
    }

    public int getColor() {
        return color;
    }
    public int getShellType() {
        return shellType;
    }


}


