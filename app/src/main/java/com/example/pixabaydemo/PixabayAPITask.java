package com.example.pixabaydemo;

import android.os.AsyncTask;
import android.util.Log;

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
            Log.d("mytag", urlString);
            URL url = new URL(urlString);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            InputStream stream = urlConnection.getInputStream();
            Scanner sc = new Scanner(stream);
            String jsonString = sc.nextLine();
            Log.d("mytag", jsonString);
            Response response = gson.fromJson(jsonString, Response.class);
            return response;

        } catch (IOException e) { return null; }

    }

    @Override
    protected Response doInBackground(Request... requests) {
        Request r = requests[0];
        return requestToServer(r);
    }

    @Override
    protected void onPostExecute(Response response) {
        activity.displayResult(response.toString());
    }
}