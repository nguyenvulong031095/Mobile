package com.example.nguyenvulong.androiddemo.adapter;

import android.app.Activity;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nguyenvulong.androiddemo.IListAction;
import com.example.nguyenvulong.androiddemo.entity.Content;
import com.example.nguyenvulong.androiddemo.R;

import java.util.List;

/**
 * Created by nguyenvulong on 2/6/18.
 */

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {
    private List<Content> contentList;
    private Activity activity;

    /**
     * Contructor
     */
    public ChatAdapter(Activity activity, List<Content> contentList) {
        this.activity = activity;
        this.contentList = contentList;
    }

    /**
     * Create ViewHolder
     */
    public class ChatViewHolder extends RecyclerView.ViewHolder {
        private TextView inputMessage, outPutMessage;
        private ImageView imageView;


        public ChatViewHolder(View itemView) {
            super(itemView);
            outPutMessage = (TextView) itemView.findViewById(R.id.tvOutPutMessenge);
            inputMessage = (TextView) itemView.findViewById(R.id.tvInputMessenge);
            imageView = (ImageView) itemView.findViewById(R.id.imgExample);


        }
    }

    @Override
    public ChatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        /** Get layout */
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_context, parent, false);
        return new ChatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ChatViewHolder holder, int position) {
        /** Set Value*/
        Content content = contentList.get(position);
        holder.inputMessage.setText(content.getInputMessageText());
        if(content.getFlag()){
            holder.outPutMessage.setText(content.getOutputMessageText());
        }else{
            holder.outPutMessage.setText("option 1: " + content.getOutputMessageText() + "\n"
                    + "option 2: " + content.getOutput2MessageText() + "\n"
                    + "option 3: " + content.getOutput3MessageText());
        }


//        int img_icon = 0;
//        if (content.getInputMessageText().equals("hom nay toi di hoc")) {
//            System.out.printf("text aaaaaaaaa"+content.getInputMessageText());
//            img_icon = R.drawable.heart;
//        } else if (content.getInputMessageText().equals("hom nay la mot ngay")) {
//            img_icon = R.drawable.ic_send;
//        }
//
//        holder.imageView.setImageResource(img_icon);

//        if(content.getKey().equals("1")){
//            holder.imageView.setImageResource(R.drawable.heart);
//        }

    }

    @Override
    public int getItemCount() {
        return contentList.size();
    }
}