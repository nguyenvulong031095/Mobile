package com.example.nguyenvulong.androiddemo;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by nguyenvulong on 2/6/18.
 */

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {
    private List<Content> contentList;
    private Activity activity;
    /**Contructor*/
    public ChatAdapter(Activity activity,List<Content> contentList) {
        this.activity = activity;
        this.contentList = contentList;
    }
    /** Create ViewHolder*/
    public class ChatViewHolder extends  RecyclerView.ViewHolder {
        private TextView inputMessage;
        private TextView outPutMessage;


        public ChatViewHolder(View itemView) {
            super(itemView);
            outPutMessage = (TextView) itemView.findViewById(R.id.tvOutPutMessenge);
            inputMessage = (TextView) itemView.findViewById(R.id.tvInputMessenge);

        }
    }
    @Override
    public ChatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        /** Get layout */
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_context,parent,false);
        return new ChatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ChatViewHolder holder, int position) {
        /** Set Value*/
        Content content = contentList.get(position);
        holder.inputMessage.setText(content.getInputMessageText());
        holder.outPutMessage.setText(content.getOutputMessageText());

       /*Sự kiện click vào item*/
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("aaaaaaaaaaabbbbbbbbbbbcccccc");
            }
        });
    }

    @Override
    public int getItemCount() {
        return contentList.size();
    }
}