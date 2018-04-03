package com.example.nguyenvulong.androiddemo.entity;

/**
 * Created by nguyenvulong on 3/26/18.
 */

public class ViewCompareContent {

    String inputCompare;
    String outputCompare1;
    String outputCompare2;
    String outputCompare3;
    String coccoc = "COC COC";

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    String example = "EXAMPLE";

    public ViewCompareContent(){};
    public ViewCompareContent(String inputCompare, String outputCompare1, String outputCompare2, String outputCompare3) {
        this.inputCompare = inputCompare;
        this.outputCompare1 = outputCompare1;
        this.outputCompare2 = outputCompare2;
        this.outputCompare3 = outputCompare3;
    }

    public String getInputCompare() {
        return inputCompare;
    }

    public void setInputCompare(String inputCompare) {
        this.inputCompare = inputCompare;
    }

    public String getOutputCompare1() {
        return outputCompare1;
    }

    public void setOutputCompare1(String outputCompare1) {
        this.outputCompare1 = outputCompare1;
    }

    public String getOutputCompare2() {
        return outputCompare2;
    }

    public void setOutputCompare2(String outputCompare2) {
        this.outputCompare2 = outputCompare2;
    }

    public String getOutputCompare3() {
        return outputCompare3;
    }

    public void setOutputCompare3(String outputCompare3) {
        this.outputCompare3 = outputCompare3;
    }

}
