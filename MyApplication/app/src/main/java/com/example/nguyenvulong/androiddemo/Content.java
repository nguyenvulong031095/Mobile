package com.example.nguyenvulong.androiddemo;

/**
 * Created by nguyenvulong on 2/6/18.
 */

public class Content {
    private String outputMessageText;
    private String inputMessageText;

    public Content() {
    }

    public Content(String inputMessageText, String outputMessageText) {
        this.outputMessageText = outputMessageText;
        this.inputMessageText = inputMessageText;

    }

    public String getOutputMessageText() {
        return outputMessageText;
    }

    public void setOutputMessageText(String outputMessageText) {
        this.outputMessageText = outputMessageText;
    }


    public String getInputMessageText() {
        return inputMessageText;
    }

    public void setInputMessageText(String inputMessageText) {
        this.inputMessageText = inputMessageText;
    }


}
