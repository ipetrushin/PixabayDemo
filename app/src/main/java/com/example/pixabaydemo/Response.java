package com.example.pixabaydemo;


public class Response {
    int total, totalHits;
    Hit[] hits;

    @Override
    public String toString() {
        // TODO: вывести текст без []
        return "totalHits = " + totalHits;
    }
}
