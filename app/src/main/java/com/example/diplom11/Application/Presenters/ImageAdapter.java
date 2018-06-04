package com.example.diplom11.Application.Presenters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.diplom11.R;

public class ImageAdapter extends BaseAdapter {

    private Context mContext;

    // Keep all Images in array
    public Integer[] mThumbIds = { R.drawable.photo1,R.drawable.photo_2,R.drawable.photo_3,R.drawable.photo_4};

    // Constructor
    public ImageAdapter(Context c) {
        mContext = c;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return mThumbIds.length; // длина массива
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return mThumbIds[position];
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = new ImageView(mContext);
        imageView.setImageResource(mThumbIds[position]);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        return imageView;
    }
}
