package com.example.pixabaydemo;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class MainActivity extends AppCompatActivity {
    ImageView iv;
    EditText etSearch;
    ListView list;
    String API_URL = "https://pixabay.com";
    String q = "bad dog";
    String key = "16115131-f2ac4e59ef4204b7d06f11215";
    String image_type = "photo";

    interface PixabayAPI {
        @GET("/api") // путь к API
        // q=1213&key=fefefe&image_type=ewfwefwe
        Call<Response> search( @Query("q") String q, @Query("key") String key, @Query("image_type") String image_type);

        // Тип ответа, действие, тип запроса
    }
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

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL) // адрес API сервера
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PixabayAPI api = retrofit.create(PixabayAPI.class);

        Call<Response> call = api.search("flowers", key, image_type);

        Callback<Response> callback = new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                Response r = response.body(); // получили ответ в виде объекта
                Log.d("mytag", "hits:" + r.hits.length);
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                // обрабатываем ошибку, если она возникла
                Log.d("mytag", "Error: " + t.getLocalizedMessage());
            }
        };
        call.enqueue(callback); // ставим запрос в очередь


        // запускаем поиск по запросу в search_text
/*
        Request req = new Request(search_text);
        PixabayAPITask task = new PixabayAPITask(this);
        task.execute(req);


 */


        // TODO: пример использования GetBitmapFromURLTask




    }
}
