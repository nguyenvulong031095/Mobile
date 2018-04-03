package com.example.nguyenvulong.androiddemo.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nguyenvulong.androiddemo.R;
import com.example.nguyenvulong.androiddemo.entity.ViewCompareContent;

import java.util.List;

/**
 * Created by nguyenvulong on 3/26/18.
 */


public class ViewCompareAdapter extends RecyclerView.Adapter<ViewCompareAdapter.ViewCompareViewHolder> {
    private List<ViewCompareContent> viewCompareContentList;
    private Activity activity;

    /**
     * Contructor
     */
    public ViewCompareAdapter(Activity activity, List<ViewCompareContent> viewCompareContentList) {
        this.activity = activity;
        this.viewCompareContentList = viewCompareContentList;
    }

    /**
     * Create ViewHolder
     */
    public class ViewCompareViewHolder extends RecyclerView.ViewHolder {
        private TextView tvCompare;
        private ImageView imageView;


        public ViewCompareViewHolder(View itemView) {
            super(itemView);
            tvCompare = (TextView) itemView.findViewById(R.id.tvCompare);

            imageView = (ImageView) itemView.findViewById(R.id.imgViewCompare);


        }
    }

    @Override
    public ViewCompareViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        /** Get layout */
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.compare_coc_coc_items, parent, false);
        return new ViewCompareViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewCompareViewHolder holder, int position) {
        /** Set Value*/
        int img_icon = 0;
        final ViewCompareContent viewCompareContent = viewCompareContentList.get(position);
        if (viewCompareContent.getInputCompare().equals("toi di hoc")) {
            holder.tvCompare.setText(viewCompareContent.getOutputCompare1() +
                    "\n" + viewCompareContent.getOutputCompare2() +
                    "\n" + viewCompareContent.getOutputCompare3());
            img_icon = R.drawable.heart;

        }else if (viewCompareContent.getInputCompare().equals("hom nay toi di hoc")) {
            holder.tvCompare.setText(viewCompareContent.getOutputCompare1() +
                    "\n" + viewCompareContent.getOutputCompare2() +
                    "\n" + viewCompareContent.getOutputCompare3());
            img_icon = R.drawable.ic_send;

        }

        holder.imageView.setImageResource(img_icon);
//        if (content.getInputMessageText().equals("hom nay toi di hoc")) {
//            System.out.printf("text aaaaaaaaa"+content.getInputMessageText());

//        } else if (content.getInputMessageText().equals("hom nay la mot ngay")) {
//            img_icon = R.drawable.ic_send;
//        }


    }

    @Override
    public int getItemCount() {
        return viewCompareContentList.size();
    }
}
