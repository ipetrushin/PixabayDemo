package com.example.pixabaydemo;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class PixabayAPITask extends AsyncTask<Request, Void, Response> {
    final MainActivity activity;

    // передаём ссылку на активность, чтобы отобразить результат перевода
    public PixabayAPITask(MainActivity activity) {
        this.activity = activity;
    }

    public Response requestToServer(Request req) {
        Gson gson = new Gson();

        String API_URL = "https://pixabay.com/api";
        try {
            String urlString = API_URL + "?" + req.formDataToString();
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // если сервер PixaBay переадресует нас на другой URL, сделать повторный запрос
            while (connection.getResponseCode() == 301) {
                Log.d("mytag", "HTTP code: " + connection.getResponseCode());
                String location = connection.getHeaderField("Location");
                Log.d("mytag", "Redirection to: " + connection.getHeaderField("Location"));
                url = new URL(location); connection = (HttpURLConnection) url.openConnection();
            }

            InputStream stream = connection.getInputStream();
            Response response = gson.fromJson(new InputStreamReader(stream), Response.class);
            return response;

        } catch (IOException e) {
            Log.d("mytag", e.getLocalizedMessage());
            return null; }
    }

    @Override
    protected Response doInBackground(Request... requests) {
        Request r = requests[0];
        return requestToServer(r);
    }

    @Override
    protected void onPostExecute(Response response) {
        activity.displayResult(response.toString());
        // TODO: из результатов поиска извлечь адрес картинки, загрузить её и отобразить на ImageView
        // Hit hit = response.hits[0];
        // hit.previewURL
        PicturesAdapter adapter = new PicturesAdapter(activity, response.hits);
        activity.list.setAdapter(adapter);
        /*
        if (response.hits.length > 0) { // если есть результаты поиска
            GetBitmapFromURLTask bmptask = new GetBitmapFromURLTask(activity);
            bmptask.execute(response.hits[0].previewURL);
        }
        else {
            Toast.makeText(activity, "No hits!", Toast.LENGTH_SHORT).show();
        } */
    }
}