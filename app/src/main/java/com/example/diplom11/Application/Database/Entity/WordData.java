package com.example.diplom11.Application.Database.Entity;



public class WordData {

    private long _id;

    private String english;

    private String russian;

    private String transcription;

    private int part_speech;

    private int complexity;

    private int word_knowledge;







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
