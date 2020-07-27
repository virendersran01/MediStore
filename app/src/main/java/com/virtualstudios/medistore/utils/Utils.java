package com.virtualstudios.medistore.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.appcompat.app.AlertDialog;

import com.virtualstudios.medistore.R;

public class Utils {

    public static String getShortName(String name) {
        String[] strings = name.split(" ");//no i18n
        String shortName;
        if (strings.length == 1) {
            shortName = strings[0].substring(0, 2);
        } else {
            shortName = strings[0].substring(0, 1) + strings[1].substring(0, 1);
        }
        return shortName.toUpperCase();
    }

    public static float dpToPixel(int i, Resources resources) {
        final float scale = resources.getDisplayMetrics().density;
        return (int) (i * scale + 0.5f);
    }

    public static AlertDialog createProgressDialog(Context context) {
        AlertDialog dialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.AlertDialogTheme);
        View view = LayoutInflater.from(context).inflate(
                R.layout.layout_progress, null);
        builder.setView(view);
        dialog = builder.create();
        dialog.setCancelable(false);

        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        return dialog;
    }

    public static void hideKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
