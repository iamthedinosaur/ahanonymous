package com.example.desetad20.ahanonymous;

public class Reply {
    private String replyText;
    private String timeStamp;
    private String posterInfo;

    public Reply(String inReplyText, String inTimeStamp, String inPosterInfo){
        replyText = inReplyText;
        timeStamp = inTimeStamp;
        posterInfo = inPosterInfo;
    }

    public String getReplyText(){ return replyText; }
    public void setReplyText(String inReplyText){ replyText = inReplyText; }
    public String getTimeStamp(){ return timeStamp; }
    public void setTimeStamp(String inTimeStamp){ timeStamp = inTimeStamp; }
    public String getPosterInfo(){return posterInfo;}
    public void setPosterInfo(String inPosterInfo){posterInfo = inPosterInfo;}
}
