package com.example.calculatorapp.model;

import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;
import androidx.annotation.StyleRes;

import com.example.calculatorapp.R;

public enum Theme {
    MAIN(R.style.Theme_CalculatorApp, R.string.main_theme, "thememain" , R.drawable.clouds ),
    NIGHT(R.style.Theme_CalculatorApp_Night, R.string.night_theme, "themenight",  R.drawable.night_clouds),
    PURPLE(R.style.Theme_CalculatorApp_Purple, R.string.purple_theme, "themepurple", R.drawable.purple_clouds ),
    BLUE(R.style.Theme_CalculatorApp_Blue, R.string.blue_theme, "themeblue", R.drawable.blue_clouds ),
    ORANGE(R.style.Theme_CalculatorApp_Orange, R.string.orange_theme, "themeorange", R.drawable.orange_clouds );

    Theme(int themeRes, int title, String key, int picture) {
        this.themeRes = themeRes;
        this.title = title;
        this.key = key;
        this.picture = picture;
    }

    @StyleRes
    private int themeRes;
    @StringRes
    private int title;
    @DrawableRes
    private int picture;

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

    public int getPicture() {
        return picture;
    }
}
