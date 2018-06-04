package com.example.desetad20.ahanonymous;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

public class AskQuestionDialogFragment extends DialogFragment {
    private CheckBox checkBox;
    private EditText questionInput;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View rootView = inflater.inflate(R.layout.dialog_ask_question, null);
        checkBox = rootView.findViewById(R.id.checkbox);
        questionInput = rootView.findViewById(R.id.questionInput);
        builder.setView(rootView);
        builder.setPositiveButton("Send!", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                String questionText = questionInput.getText() + "";
                boolean postAnonymously = checkBox.isChecked();
                EventBus bus = EventBus.getDefault();
                bus.post(new QuestionAskedEvent(questionText, postAnonymously));
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
            }
        });
        return builder.create();
    }

}
