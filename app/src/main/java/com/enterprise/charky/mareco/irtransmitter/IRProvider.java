package com.enterprise.charky.mareco.irtransmitter;

/**
 * Created by charky on 11.10.15.
 */
public class IRProvider {

    public String irpTitle;
    public String irpSubTitle;
    public int irpIcon;
    public IRCodes irpCodes;

    public IRProvider(String sTitle,  String sSubTitle, int iIcon, IRCodes irCodes) {
        irpTitle = sTitle;
        irpSubTitle = sSubTitle;
        irpIcon = iIcon;
        irpCodes = irCodes;
    }
}
