package com.example.diplom11.Presenters;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.example.diplom11.R;
import com.example.diplom11.View.ChangeViewActivity;

/**
 * Created by Инна on 04.05.2018.
 */

public class ChangeViewPresenterImpl implements BasePresenter {
    private ChangeViewActivity activity;

    public ChangeViewPresenterImpl(ChangeViewActivity activity){
        this.activity = activity;
    }
    @Override
    public void onBackClick() {
        activity.finish();
    }

    @Override
    public void onItemCLick(int position) {
        AlertDialog.Builder builder;
        AlertDialog dialog;
        switch (position){
            case 0:
                builder = new AlertDialog.Builder(activity);
                builder.setTitle(R.string.change_color);
                builder.setSingleChoiceItems(R.array.itemsOfColors, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i){
                            case 0:

                        }
                    }
                });
                builder.setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.setNegativeButton("Cancel",null);
                dialog = builder.create();
                dialog.show();
                break;
            case 1:
                builder = new AlertDialog.Builder(activity);
                builder.setTitle(R.string.change_font);
                builder.setSingleChoiceItems(R.array.itemsOfFonts, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i){
                            case 0:
                        }
                    }
                });
                builder.setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.setNegativeButton("Cancel",null);
                dialog = builder.create();
                dialog.show();
                break;

        }
    }
    }

