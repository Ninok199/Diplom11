package com.example.diplom11.Application.Database.Entity;


public class StatisticsData {


        private long _id_Statistics;

        private long _idWord;

        private int correctAnswer;

    private String dateAnswer;






    public StatisticsData(long _id_Statistics, long _idWord, int correctAnswer,
            String dateAnswer) {
        this._id_Statistics = _id_Statistics;
        this._idWord = _idWord;
        this.correctAnswer = correctAnswer;
        this.dateAnswer = dateAnswer;
    }


    public StatisticsData() {
    }


    public StatisticsData( long _idWord, int correctAnswer,
                          String dateAnswer) {
        this._idWord = _idWord;
        this.correctAnswer = correctAnswer;
        this.dateAnswer = dateAnswer;
    }

    

    public long get_id_Statistics() {
        return _id_Statistics;
    }

    public void set_id_Statistics(long _id_Statistics) {
        this._id_Statistics = _id_Statistics;
    }

    public long get_idWord() {
        return _idWord;
    }

    public void set_idWord(long _idWord) {
        this._idWord = _idWord;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(int  correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getDateAnswer() {
        return dateAnswer;
    }

    public void setDateAnswer(String dateAnswer) {
        this.dateAnswer = dateAnswer;
    }
}
