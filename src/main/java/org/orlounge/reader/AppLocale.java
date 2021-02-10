package org.orlounge.reader;

/**
 * Created by Satyam Soni on 9/20/2015.
 */
public enum AppLocale {


    ENGLISH("ENGLISH");

    private String locale;

    private AppLocale(String locale) {
        this.locale = locale;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }
}
