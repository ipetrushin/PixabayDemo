package com.example.pixabaydemo;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class PicturesAdapter extends BaseAdapter {
    Context ctx;

    public PicturesAdapter(Context ctx, Hit[] hits) {
        this.ctx = ctx;
        this.hits = hits;
    }

    Hit[] hits;

    @Override
    public int getCount() {
        return hits.length;
    }

    @Override
    public Object getItem(int position) {
        return hits[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView= LayoutInflater.from(ctx).inflate(R.layout.item, parent, false);
        ImageView iv = convertView.findViewById(R.id.picture);
        TextView tvid = convertView.findViewById(R.id.id);
        Hit h = hits[position];
        tvid.setText("id: "+h.id);

        iv.setImageResource(R.drawable.no_image);
        GetBitmapFromURLTask bmptask = new GetBitmapFromURLTask(iv);
        bmptask.execute(h.previewURL);
        Log.d("mytag", "loading id: "+h.id);

        return convertView;
    }
}
