package com.example.pixabaydemo;

import java.net.URLEncoder;

public class Request {
    String search_text;

    // key=16115131-f2ac4e59ef4204b7d06f11215&q=bad=dog&image_type=photo

    public Request(String text) {
        this.search_text = text;
    }

    // TODO: указать свой ключ https://pixabay.com/api/docs/
    String key = "16115131-f2ac4e59ef4204b7d06f11215";

    public String formDataToString() {
        // TODO: закодировать search_text с помощью URLEncode
        return "q="+ URLEncoder.encode(search_text) +"&key="+key+"&image_type=photo";
    }

}
