package com.example.myapplication222;
//
//public class SampleData {
//    private int starImg;
//    private String word;
//
//    public SampleData(int star, String word) {
//        this.starImg = star;
//        this.word = word;
//    }
//
//    public int getStarImg()
//    {
//        return this.starImg;
//    }
//
//    public String getWord()
//    {
//        return this.word;
//    }
//}

//TODO : Sample Data -> 영단어, 뜻으로 구성하도
public class SampleData {
    private int starImg;
    private String Eng;
    private String Kor;

    public SampleData (int star, String Eng, String Kor) {
        this.starImg = star;
        this.Eng = Eng;
        this.Kor = Kor;
    }

    public int getStarImg() {
        return this.starImg;
    }

    public String getEng() {
        return this.Eng;
    }

    public String getKor() {
        return this.Kor;
    }
}