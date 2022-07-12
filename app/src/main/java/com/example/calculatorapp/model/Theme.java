package com.example.calculatorapp.model;

import androidx.annotation.StringRes;
import androidx.annotation.StyleRes;

import com.example.calculatorapp.R;

public enum Theme {
    MAIN(R.style.Theme_CalculatorApp, R.string.main_theme, "thememain" ),
    NIGHT(R.style.Theme_CalculatorApp_Night, R.string.night_theme, "themenight" ),
    PURPLE(R.style.Theme_CalculatorApp_Purple, R.string.purple_theme, "themepurple" );

    Theme(int themeRes, int title, String key) {
        this.themeRes = themeRes;
        this.title = title;
        this.key = key;
    }

    @StyleRes
    private int themeRes;
    @StringRes
    private int title;

    private String key;

    public int getThemeRes() {
        return themeRes;
    }

    public int getTitle() {
        return title;
    }

    public String getKey() {
        return key;
    }
}
