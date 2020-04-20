package com.example.pixabaydemo;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv = findViewById(R.id.image);
    }

    public void displayResult(String text) {
        Log.d("mytag", text);
    }

    public void displayImage(Bitmap bmp) {
        iv.setImageBitmap(bmp);
    }

    public void onClick(View v) {
        EditText et = findViewById(R.id.search_text);
        String search_text = et.getText().toString();

        // запускаем поиск по запросу в search_text
        Request req = new Request(search_text);
        PixabayAPITask task = new PixabayAPITask(this);
        task.execute(req);

        // TODO: пример использования GetBitmapFromURLTask
        GetBitmapFromURLTask bmptask = new GetBitmapFromURLTask(this);
        bmptask.execute("https://cdn.pixabay.com/photo/2018/02/08/22/27/flower-3140492_150.jpg");



    }
}
