package com.example.diplom11;



public class WordData {

    private int _id;
    private String english;
    private String russian;
    private String transcription;
    private int part_speech;
    private int complexity;
    private int word_category;
    public WordData(){}
    public WordData (String english, String russian, String transcription, int part_speech,int complexity,int word_category){

        this.english=english;
        this.russian=russian;
        this.transcription=transcription;
        this.part_speech=part_speech;
        this.complexity=complexity;
        this.word_category=word_category;
    }
    public WordData (int id,String english, String russian, String transcription, int part_speech,int complexity,int word_category){
        this._id=id;
        this.english=english;
        this.russian=russian;
        this.transcription=transcription;
        this.part_speech=part_speech;
        this.complexity=complexity;
        this.word_category=word_category;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
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

    public int getWord_category() {
        return word_category;
    }

    public void setWord_category(int word_category) {
        this.word_category = word_category;
    }
}
