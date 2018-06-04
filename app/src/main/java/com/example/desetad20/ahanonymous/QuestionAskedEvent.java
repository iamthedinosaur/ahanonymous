package com.example.desetad20.ahanonymous;

public class QuestionAskedEvent {
    public String question;
    public boolean postAnonymously;
    public QuestionAskedEvent(String inQuestion, boolean inPostAnonymously){
        question = inQuestion;
        postAnonymously = inPostAnonymously;
    }
}
