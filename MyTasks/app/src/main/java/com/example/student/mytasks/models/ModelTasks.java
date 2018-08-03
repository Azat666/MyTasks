package com.example.student.mytasks.models;

public class ModelTasks {
    private final int imageId;
    private final String textTitle;
    private int textTime[] ;
    private int textData[] ;

    public ModelTasks(int imageId, String textTitle, int[] textTime, int[] textData) {
        this.imageId = imageId;
        this.textTitle = textTitle;

        this.textTime = textTime;
        this.textData = textData;
    }

    public int getImageId() {
        return imageId;
    }

    public String getTextTitle() {
        return textTitle;
    }

    public int[] getTextTime() {
        return textTime;
    }

    public int[] getTextData() {
        return textData;
    }
}

