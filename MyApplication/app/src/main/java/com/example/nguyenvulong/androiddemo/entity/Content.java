package com.example.nguyenvulong.androiddemo.entity;

import android.text.BoringLayout;

/**
 * Created by nguyenvulong on 2/6/18.
 */

public class Content {
    private String outputMessageText;
    private String inputMessageText;
    private String output2MessageText;
    private String output3MessageText;

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    private Boolean flag = false;
    public String getOutput2MessageText() {
        return output2MessageText;
    }

    public void setOutput2MessageText(String output2MessageText) {
        this.output2MessageText = output2MessageText;
    }

    public String getOutput3MessageText() {
        return output3MessageText;
    }

    public void setOutput3MessageText(String output3MessageText) {
        this.output3MessageText = output3MessageText;
    }

   public Content(String inputMessageText,String outputMessageText, Boolean flag){
        this.inputMessageText = inputMessageText;
        this.outputMessageText = outputMessageText;
        this.flag = flag;
   }

    public Content(String inputMessageText, String outputMessageText, String output2MessageText, String output3MessageText) {
        this.outputMessageText = outputMessageText;
        this.inputMessageText = inputMessageText;
        this.output2MessageText = output2MessageText;
        this.output3MessageText = output3MessageText;

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
