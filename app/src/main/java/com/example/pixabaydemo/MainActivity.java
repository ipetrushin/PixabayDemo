package com.example.pixabaydemo;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ImageView iv;
    EditText etSearch;
    ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv = findViewById(R.id.image);
        list = findViewById(R.id.list);
        etSearch = findViewById(R.id.search_text);

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




    }
}
