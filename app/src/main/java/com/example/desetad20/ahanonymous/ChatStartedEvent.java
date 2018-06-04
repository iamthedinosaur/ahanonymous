package com.example.desetad20.ahanonymous;

public class ChatStartedEvent {
    public String question;
    public String reply;
    public ChatStartedEvent(String inQuestion, String inReply){
        question = inQuestion;
        reply = inReply;
    }
}
