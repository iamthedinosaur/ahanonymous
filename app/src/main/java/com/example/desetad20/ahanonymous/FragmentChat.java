package com.example.desetad20.ahanonymous;



import android.content.Intent;
import android.opengl.Visibility;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentChat extends Fragment {
    private RecyclerView recycleChats;
    private ChatAdapter chatAdapter;
    private ImageView noChatsImage;
    private TextView noChatsText;
    EventBus bus = EventBus.getDefault();
    List<Chat> chatList = new ArrayList<>();

    public FragmentChat() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_chat, container, false);

        recycleChats = (RecyclerView) rootView.findViewById(R.id.recycleChats);
        noChatsImage = (ImageView) rootView.findViewById(R.id.noChatsImage);
        noChatsText = (TextView) rootView.findViewById(R.id.noChatsText);
        recycleChats.setLayoutManager(new LinearLayoutManager(getContext()));

        return rootView;
    }

    private class ChatHolder extends RecyclerView.ViewHolder{
        public TextView questionTextView;
        public TextView replyTextView;

        public ChatHolder(View itemView){
            super(itemView);
            questionTextView = (TextView)itemView.findViewById(R.id.questionTextChat);
            replyTextView = (TextView)itemView.findViewById(R.id.replyTextChat);
        }
    }

    private class ChatAdapter extends RecyclerView.Adapter<ChatHolder>{
        private List<Chat> chatList;

        public ChatAdapter(List<Chat> inChatList){
            chatList = inChatList;
        }

        public ChatHolder onCreateViewHolder(ViewGroup parent, int viewType){
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            View view = layoutInflater
                    .inflate(R.layout.list_item_chat, parent, false);
            return new ChatHolder(view);
        }

        public void onBindViewHolder(ChatHolder holder, int position){
            Chat chat = chatList.get(position);
            holder.questionTextView.setText(chat.getQuestionText());
            holder.replyTextView.setText(chat.getReplyText());
        }

        public int getItemCount(){
            return chatList.size();
        }
    }

    private void updateUI(Chat chat){
        if(chat != null){
            chatList.add(chat);
        }
        chatAdapter = new ChatAdapter(chatList);
        recycleChats.setAdapter(chatAdapter);
        if(chatAdapter.getItemCount() != 0){
            noChatsText.setVisibility(View.GONE);
            noChatsImage.setVisibility(View.GONE);
            recycleChats.setVisibility(View.VISIBLE);
        }else{
            noChatsText.setVisibility(View.VISIBLE);
            noChatsImage.setVisibility(View.VISIBLE);
            recycleChats.setVisibility(View.GONE);
        }
    }

    @Override
    public void onStart(){
        super.onStart();
        bus.register(this);
        updateUI(null);
    }

    @Override
    public void onStop(){
        super.onStop();
        bus.unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(ChatStartedEvent event){
        Chat chat = new Chat(event.question, event.reply);
        updateUI(chat);
    }
}
