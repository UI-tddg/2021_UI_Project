package com.example.myapplication222;

public class SampleData {
    private int starImg;
    private String word;

    public SampleData(int star, String word) {
        this.starImg = star;
        this.word = word;
    }

    public int getStarImg()
    {
        return this.starImg;
    }

    public String getWord()
    {
        return this.word;
    }
}