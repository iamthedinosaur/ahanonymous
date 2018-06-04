package com.example.desetad20.ahanonymous;



import android.app.DialogFragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentQuestions extends Fragment {
    private QuestionAdapter questionAdapter;
    private RecyclerView recycleFriendQuestions;

    public FragmentQuestions() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_questions, container, false);

        recycleFriendQuestions = (RecyclerView)rootView.findViewById(R.id.recycleFriendQuestions);
        recycleFriendQuestions.setLayoutManager(new LinearLayoutManager(getContext()));
        updateQuestionUI();

        return rootView;
    }

    private void updateQuestionUI(){
        List<Question> questionList = new ArrayList<>();
        questionList.add(new Question("Which is the better course? Android App Development or AP Comp Sci?", "5/22/18 2:42", R.drawable.dom, "♂ 10", "Dominick DeSeta", "@iamthedinosaur"));
        questionList.add(new Question("Who is the best student of the Android App Development class?", "5/23/18 3:42", R.drawable.dom2, "♂ 10", "Mark DeSeta", "@markymark1235"));
        questionList.add(new Question("Who would win in a fight? Danny Devito or Thanos from the MCU? (With the infinity gauntlet)", "5/24/18 4:42", R.drawable.danny, "♂ 12", "Danny Devito", "@trashman"));
        questionList.add(new Question("What is my middle name? I will legally change it to what's said by whoever replies first.", "5/25/18 5:42", R.drawable.danny2, "♂ 11", "Daniel Devoto", "@coolkid"));
        questionList.add(new Question("Is Arjun even a real human being? Or is he a manifestation of the spirit of a potato? The world may never know.", "5/26/18 6:42", R.drawable.danny3, "♀ 10", "Danielle Devito", "@prettywoman"));
        questionAdapter = new QuestionAdapter(questionList, getContext(), getResources(), true);
        recycleFriendQuestions.setAdapter(questionAdapter);
    }
}
