package com.example.nguyenvulong.androiddemo.entity;

import java.io.Serializable;

/**
 * Created by nguyenvulong on 3/16/18.
 */

public class ViewDetailFragmentContent  implements Serializable{
    private String proba;
    private String predict;
    private String word;
    private String image;
    private String input;
    private String predictionValue;
    private String allLabelWord;

    public String getClassificationReport() {
        return classificationReport;
    }

    public void setClassificationReport(String classificationReport) {
        this.classificationReport = classificationReport;
    }

    public String getConfusionMatrix() {
        return confusionMatrix;
    }

    public void setConfusionMatrix(String confusionMatrix) {
        this.confusionMatrix = confusionMatrix;
    }

    private String classificationReport;
    private String confusionMatrix;



    public ViewDetailFragmentContent(String word,String predictionValue,String allLabelWord, String proba, String predict, String input,String classificationReport) {
        this.word = word;
        this.proba = proba;
        this.predict = predict;
        this.input = input;
        this.predictionValue = predictionValue;
        this.allLabelWord = allLabelWord;
        this.classificationReport = classificationReport;
    }

    public ViewDetailFragmentContent() {

    }
    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }


    public String getProba() {
        return proba;
    }

    public void setProba(String proba) {
        this.proba = proba;
    }

    public String getPredict() {
        return predict;
    }

    public void setPredict(String predict) {
        this.predict = predict;
    }
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    public String getPredictionValue() {
        return predictionValue;
    }

    public void setPredictionValue(String predictionValue) {
        this.predictionValue = predictionValue;
    }

    public String getAllLabelWord() {
        return allLabelWord;
    }

    public void setAllLabelWord(String allLabelWord) {
        this.allLabelWord = allLabelWord;
    }







}
