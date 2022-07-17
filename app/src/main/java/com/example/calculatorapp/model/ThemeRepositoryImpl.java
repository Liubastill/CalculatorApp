package com.example.calculatorapp.model;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Arrays;
import java.util.List;

public class ThemeRepositoryImpl implements ThemeRepository{


    private static final String KEY_THEME = "KEY_THEME ";
    private static ThemeRepository INSTANCE;
    private SharedPreferences preferences;



    public ThemeRepositoryImpl(Context context) {
        preferences = context.getSharedPreferences("themes.xml", Context.MODE_PRIVATE);
    }

    public static ThemeRepository getInstance(Context context) {
         if (INSTANCE == null){
             INSTANCE = new ThemeRepositoryImpl(context);
         }
        return INSTANCE;
    }


    @Override
    public Theme getSavedTheme() {

        String savedKey = preferences.getString(KEY_THEME, Theme.MAIN.getKey());

        for (Theme them: Theme.values()) {
            if (them.getKey().equals(savedKey)){
                 return them;
            }

        }
        return Theme.MAIN;
    }

    @Override
    public void saveTheme(Theme theme) {
        preferences.edit()
                .putString(KEY_THEME, theme.getKey())
                .apply();

    }

    @Override
    public List<Theme> getAllThemes() {

        return Arrays.asList(Theme.values());
    }
}
