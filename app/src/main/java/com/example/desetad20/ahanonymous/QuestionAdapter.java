package com.example.desetad20.ahanonymous;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionHolder>{
    public Context context;
    public Resources resources;
    private List<Question> questionList;
    public boolean displayProfile;

    public QuestionAdapter(List<Question> inQuestionList, Context inContext, Resources inResources, boolean inDisplayProfile){
        questionList = inQuestionList;
        context = inContext;
        resources = inResources;
        displayProfile = inDisplayProfile;
    }

    public QuestionHolder onCreateViewHolder(ViewGroup parent, int viewType){
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater
                .inflate(R.layout.list_item_question, parent, false);
        return new QuestionHolder(view, context, resources, displayProfile);
    }

    public void onBindViewHolder(QuestionHolder holder, int position){
        Question question = questionList.get(position);
        holder.bindQuestion(question);
    }

    public int getItemCount(){
        return questionList.size();
    }
}
