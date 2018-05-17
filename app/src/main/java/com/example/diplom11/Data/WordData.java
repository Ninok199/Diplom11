package com.example.diplom11.Data;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity(nameInDb = "word",generateConstructors = true)
public class WordData {

    @Id
    private long _id;
    @Property(nameInDb = "english")
    private String english;
    @Property(nameInDb = "russian")
    private String russian;
    @Property(nameInDb = "transcription")
    private String transcription;
    @Property(nameInDb = "part_speech")
    private int part_speech;
    @Property(nameInDb = "complexity")
    private int complexity;
    @Property(nameInDb = "word_knowledge")
    private int word_knowledge;






    @Generated(hash = 645985973)
    public WordData(long _id, String english, String russian, String transcription,
            int part_speech, int complexity, int word_knowledge) {
        this._id = _id;
        this.english = english;
        this.russian = russian;
        this.transcription = transcription;
        this.part_speech = part_speech;
        this.complexity = complexity;
        this.word_knowledge = word_knowledge;
    }

    public WordData( String english, String russian, String transcription,
                    int part_speech, int complexity, int word_knowledge) {

        this.english = english;
        this.russian = russian;
        this.transcription = transcription;
        this.part_speech = part_speech;
        this.complexity = complexity;
        this.word_knowledge = word_knowledge;
    }

    @Generated(hash = 551690561)
    public WordData() {
    }




  

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getRussian() {
        return russian;
    }

    public void setRussian(String russian) {
        this.russian = russian;
    }

    public String getTranscription() {
        return transcription;
    }

    public void setTranscription(String transcription) {
        this.transcription = transcription;
    }

    public int getPart_speech() {
        return part_speech;
    }

    public void setPart_speech(int part_speech) {
        this.part_speech = part_speech;
    }

    public int getComplexity() {
        return complexity;
    }

    public void setComplexity(int complexity) {
        this.complexity = complexity;
    }

    public int getWord_knowledge() {
        return word_knowledge;
    }

    public void setWord_knowledge(int word_knowledge) {
        this.word_knowledge = word_knowledge;
    }
}
