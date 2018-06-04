package com.example.desetad20.ahanonymous;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import org.greenrobot.eventbus.EventBus;

public class StartChatDialogFragment extends DialogFragment {
    private String replierText = "";
    private String questionText = "";

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Start a chat with this replier? They said:\n\n" + replierText);
        builder.setPositiveButton("Yep", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                EventBus bus = EventBus.getDefault();
                bus.post(new ChatStartedEvent(questionText, replierText));
            }
        });
        builder.setNegativeButton("Nope", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //nothing happens
            }
        });
        return builder.create();
    }
    public void setReplierText(String inReplierText){
        replierText = inReplierText;
    }
    public void setQuestionText(String inQuestionText){
        questionText = inQuestionText;
    }
}
