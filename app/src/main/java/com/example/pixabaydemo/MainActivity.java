package com.example.pixabaydemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void displayResult(String text) {
        Log.d("mytag", text);
        ImageView iv = findViewById(R.id.image);
        // TODO: предусмотреть вывод первой картинки из списка
    }

    public void onClick(View v) {
        EditText et = findViewById(R.id.search_text);
        String txt = et.getText().toString();
        // TODO: **
        Request req = new Request(txt);
        PixabayAPITask task = new PixabayAPITask(this);
        task.execute(req);

    }
}
