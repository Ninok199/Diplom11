package com.example.diplom11.Data;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Инна on 11.05.2018.
 */
@Entity(nameInDb = "statistic",generateConstructors = true)
public class StatisticData {

        @Id
        private long _id_statistics;
        @Property(nameInDb = "_id_word")
        private int _idWord;
        @Property(nameInDb = "correct_answer")
        private int correctAnswer;
    @Property(nameInDb = "date_answer")
    private String dateAnswer;

    

    public StatisticData(){}

    @Generated(hash = 688486151)
    public StatisticData(long _id_statistics, int _idWord, int correctAnswer,
            String dateAnswer) {
        this._id_statistics = _id_statistics;
        this._idWord = _idWord;
        this.correctAnswer = correctAnswer;
        this.dateAnswer = dateAnswer;
    }

    public long get_id_statistics() {
        return _id_statistics;
    }

    public void set_id_statistics(long _id_statistics) {
        this._id_statistics = _id_statistics;
    }

    public int get_idWord() {
        return _idWord;
    }

    public void set_idWord(int _idWord) {
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
