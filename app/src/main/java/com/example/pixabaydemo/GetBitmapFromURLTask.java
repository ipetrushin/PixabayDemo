package com.example.pixabaydemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetBitmapFromURLTask extends AsyncTask<String, Void, Bitmap> {
    final MainActivity activity;

    // передаём ссылку на активность, чтобы отобразить результат перевода
    public GetBitmapFromURLTask(MainActivity activity) {
        this.activity = activity;
    }

    public Bitmap requestToServer(String url) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            InputStream stream = connection.getInputStream();
            return BitmapFactory.decodeStream(stream);

        } catch (IOException e) {
            Log.d("mytag", e.getLocalizedMessage());
            return null; }
    }

    @Override
    protected Bitmap doInBackground(String... requests) {
        String r = requests[0];
        return requestToServer(r);
    }

    @Override
    protected void onPostExecute(Bitmap bmp) {
        // задать полученное изображение на ImageView
        activity.displayImage(bmp);
        Log.d("mytag", "Bitmap size: " + bmp.getByteCount());
    }
}