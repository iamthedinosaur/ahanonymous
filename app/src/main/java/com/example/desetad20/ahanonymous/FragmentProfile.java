package com.example.desetad20.ahanonymous;



import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentProfile extends Fragment {
    private QuestionAdapter questionAdapter;
    private TextView addQuestion;
    private RecyclerView recycleQuestions;
    private EventBus bus = EventBus.getDefault();
    private List<Question> questionList = new ArrayList<>();

    public FragmentProfile() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        ImageView profView = rootView.findViewById(R.id.profPic);
        Bitmap profBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.dom);
        RoundedBitmapDrawable circularBitmapDrawable =
                RoundedBitmapDrawableFactory.create(getResources(), profBitmap);
        circularBitmapDrawable.setCircular(true);
        profView.setImageDrawable(circularBitmapDrawable);

        recycleQuestions = (RecyclerView)rootView.findViewById(R.id.recycleQuestions);
        recycleQuestions.setLayoutManager(new LinearLayoutManager(getContext()));

        addQuestion = (TextView)rootView.findViewById(R.id.addQuestion);
        addQuestion.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                AskQuestionDialogFragment dialogFragment = new AskQuestionDialogFragment();
                dialogFragment.show(((FragmentActivity)getContext()).getFragmentManager(), "start");
            }
        });

        return rootView;
    }

    private void updateQuestionUI(Question question){
        if(question != null){
            questionList.add(question);
        }
        questionAdapter = new QuestionAdapter(questionList, getContext(), getResources(),false);
        recycleQuestions.setAdapter(questionAdapter);
    }

    @Override
    public void onStart(){
        super.onStart();
        bus.register(this);
        updateQuestionUI(null);
    }

    @Override
    public void onStop(){
        super.onStop();
        bus.unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(QuestionAskedEvent event){
        Question question = null;
        String timeStamp = new SimpleDateFormat("M/d h:ma").format(Calendar.getInstance().getTime());
        if(event.postAnonymously){
            question = new Question(event.question, timeStamp, R.drawable.ic_help_gray_24dp,
                    "♂ 10", "Dominick DeSeta", "@iamthedinosaur");
        }else{
            question = new Question(event.question, timeStamp, R.drawable.dom,
                    "♂ 10", "Dominick DeSeta", "@iamthedinosaur");
        }
        updateQuestionUI(question);
    }
}
