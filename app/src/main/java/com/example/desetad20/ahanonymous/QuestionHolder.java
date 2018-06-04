package com.example.desetad20.ahanonymous;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.provider.ContactsContract;
import android.support.constraint.solver.widgets.ConstraintWidgetContainer;
import android.support.v4.app.FragmentActivity;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class QuestionHolder extends RecyclerView.ViewHolder{
    public TextView questionTextView;
    public TextView timeStampTextView;
    public Button viewRepliesButton;
    public RecyclerView recycleReplies;
    public ImageView profPic;
    public TextView status;
    public TextView realName;
    public TextView username;
    public Button sendReply;
    public EditText enterReply;

    public Question question;
    public Context context;
    public Resources resources;
    public boolean displayProfile;
    private ReplyAdapter replyAdapter;

    public QuestionHolder(View itemView, Context inContext, Resources inResources, boolean inDisplayProfile){
        super(itemView);
        context = inContext;
        resources = inResources;
        displayProfile = inDisplayProfile;
        questionTextView = (TextView) itemView.findViewById(R.id.question);
        timeStampTextView = (TextView) itemView.findViewById(R.id.questionTime);
        viewRepliesButton = (Button) itemView.findViewById(R.id.viewReplies);
        profPic = (ImageView) itemView.findViewById(R.id.profPic);
        status = (TextView) itemView.findViewById(R.id.status);
        realName = (TextView) itemView.findViewById(R.id.realName);
        username = (TextView) itemView.findViewById(R.id.username);
        sendReply = (Button) itemView.findViewById(R.id.sendReply);
        enterReply = (EditText) itemView.findViewById(R.id.enterReply);

        recycleReplies = (RecyclerView) itemView.findViewById(R.id.recycleReplies);
        recycleReplies.setLayoutManager(new LinearLayoutManager(context));

        if(displayProfile){
            enterReply.setVisibility(View.VISIBLE);
            sendReply.setVisibility(View.VISIBLE);
            realName.setVisibility(View.VISIBLE);
            status.setVisibility(View.VISIBLE);
            profPic.setVisibility(View.VISIBLE);
            username.setVisibility(View.VISIBLE);
        }

        updateReplyUI();

        viewRepliesButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(recycleReplies.getVisibility() == View.VISIBLE){
                    viewRepliesButton.setText("↓ View Replies");
                    recycleReplies.setVisibility(View.GONE);
                }
                else{
                    viewRepliesButton.setText("↑ Hide Replies");
                    recycleReplies.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    public void bindQuestion(Question inQuestion){
        question = inQuestion;
        questionTextView.setText(question.getQuestionText());
        timeStampTextView.setText(question.getTimeStamp());
        status.setText(question.getPosterStatus());
        realName.setText(question.getPosterRealName());
        username.setText(question.getPosterUsername());
        if(displayProfile){
            Picasso.get()
                    .load(question.getPosterPicId())
                    .resize(50, 50)
                    .into(profPic, new Callback() {
                @Override
                public void onSuccess() {
                    Bitmap imageBitmap = ((BitmapDrawable) profPic.getDrawable()).getBitmap();
                    RoundedBitmapDrawable imageDrawable = RoundedBitmapDrawableFactory.create(resources, imageBitmap);
                    imageDrawable.setCircular(true);
                    imageDrawable.setCornerRadius(Math.max(imageBitmap.getWidth(), imageBitmap.getHeight()) / 2.0f);
                    profPic.setImageDrawable(imageDrawable);
                }
                @Override
                public void onError(Exception e) {
                    profPic.setImageResource(R.drawable.dom);
                }
            });
        }
    }

    private class ReplyHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView replyTextView;
        public TextView timeStampTextView;
        public TextView posterInfoTextView;
        public Reply reply;

        public ReplyHolder(View itemView){
            super(itemView);
            itemView.setOnClickListener(this);
            replyTextView = (TextView) itemView.findViewById(R.id.reply);
            timeStampTextView = (TextView) itemView.findViewById(R.id.replyTime);
            posterInfoTextView = (TextView) itemView.findViewById(R.id.posterInfo);
        }

        public void bindReply(Reply inReply){
            reply = inReply;
            replyTextView.setText(reply.getReplyText());
            timeStampTextView.setText(reply.getTimeStamp());
            posterInfoTextView.setText(reply.getPosterInfo());
        }

        public void onClick(View v){
            StartChatDialogFragment dialogFragment = new StartChatDialogFragment();
            dialogFragment.setReplierText(replyTextView.getText() + "");
            dialogFragment.setQuestionText(questionTextView.getText() + "");
            dialogFragment.show(((FragmentActivity)context).getFragmentManager(), "start");
        }
    }

    private class ReplyAdapter extends RecyclerView.Adapter<ReplyHolder>{
        private List<Reply> replyList;

        public ReplyAdapter(List<Reply> inReplies){
            replyList = inReplies;
        }

        public ReplyHolder onCreateViewHolder(ViewGroup parent, int viewType){
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            View view = layoutInflater
                    .inflate(R.layout.list_item_reply, parent, false);
            return new ReplyHolder(view);
        }

        public void onBindViewHolder(ReplyHolder holder, int position){
            Reply reply = replyList.get(position);
            holder.bindReply(reply);
        }

        public int getItemCount(){
            return replyList.size();
        }
    }

    private void updateReplyUI(){
        List<Reply> replyList = new ArrayList<>();
        replyList.add(new Reply("Is this even debatable? The answer is certainly 'yes'! Stop asking such silly questions, you fool.", "5/22/18 2:42", "♂ 10"));
        replyList.add(new Reply("This question sure is interesting! I refuse to answer it, though. Goodbye!", "5/23/18 3:42", "♀ 10"));
        replyList.add(new Reply("This question makes me feel sad inside. Are you okay, dude? I'm here to help whenever!", "5/24/18 4:42", "♂ 11"));
        replyList.add(new Reply("Please delete your account! I'm tired of seeing your boring questions!", "5/25/18 5:42", "♀ 11"));
        replyList.add(new Reply("Three words: 'I hate this question'", "5/26/18 6:42", "♂ 12"));
        replyAdapter = new ReplyAdapter(replyList);
        recycleReplies.setAdapter(replyAdapter);
    }
}
