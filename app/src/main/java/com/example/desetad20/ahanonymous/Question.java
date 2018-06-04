package com.example.desetad20.ahanonymous;

public class Question {
    private String questionText;
    private String timeStamp;
    private int posterPicId;
    private String posterStatus;
    private String posterRealName;
    private String posterUsername;

    public Question(String inQuestionText, String inTimeStamp, int posterPicId, String posterStatus, String posterRealName, String posterUsername){
        questionText = inQuestionText;
        timeStamp = inTimeStamp;
        this.posterPicId = posterPicId;
        this.posterStatus = posterStatus;
        this.posterRealName = posterRealName;
        this.posterUsername = posterUsername;
    }

    public int getPosterPicId() {
        return posterPicId;
    }

    public void setPosterPicId(int posterPicId) {
        this.posterPicId = posterPicId;
    }

    public String getPosterStatus() {
        return posterStatus;
    }

    public void setPosterStatus(String posterStatus) {
        this.posterStatus = posterStatus;
    }

    public String getPosterRealName() {
        return posterRealName;
    }

    public void setPosterRealName(String posterRealName) {
        this.posterRealName = posterRealName;
    }

    public String getPosterUsername() {
        return posterUsername;
    }

    public void setPosterUsername(String posterUsername) {
        this.posterUsername = posterUsername;
    }

    public String getQuestionText(){ return questionText; }
    public void setQuestionText(String inQuestionText){ questionText = inQuestionText; }
    public String getTimeStamp(){ return timeStamp; }
    public void setTimeStamp(String inTimeStamp){ timeStamp = inTimeStamp; }
}
