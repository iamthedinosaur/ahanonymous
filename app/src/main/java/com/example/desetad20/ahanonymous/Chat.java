package com.example.desetad20.ahanonymous;

public class Chat {
    private String questionText;
    private String replyText;

    public Chat(String questionText, String replyText) {
        this.questionText = questionText;
        this.replyText = replyText;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getReplyText() {
        return replyText;
    }

    public void setReplyText(String replyText) {
        this.replyText = replyText;
    }
}
