package com.orbismobile.betasearch.data;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.orbismobile.betasearch.R;
import com.orbismobile.betasearch.model.response.JobSearchResponse;

import java.util.List;

/**
 * Created by tohure on 18/04/17.
 */

public class JobsAdapter extends RecyclerView.Adapter<JobsAdapter.ItemSearchViewHolder> {

    private List<JobSearchResponse.DataBean> jobList;
    private Context mContext;
    private static OnItemClickListener listener;
    private static OnItemLongClickListener longListener;

    public interface OnItemClickListener { void onItemClick(View itemView, int position); }
    public interface OnItemLongClickListener { void onItemLongClick(View itemView, int position); }

    public void setOnItemClickListener(OnItemClickListener listener) { JobsAdapter.listener = listener; }

    public void setOnItemLongClickListener(OnItemLongClickListener listener) { JobsAdapter.longListener = listener; }

    public JobsAdapter(Context context, List<JobSearchResponse.DataBean> items) {
        this.jobList = items;
        this.mContext = context;
    }

    public static class ItemSearchViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout rowContainer;
        private TextView titleJob, companyName, jobDate;

        public ItemSearchViewHolder(View itemView) {
            super(itemView);

            this.rowContainer = (LinearLayout) itemView.findViewById(R.id.rowContainer);
            this.titleJob = (TextView) itemView.findViewById(R.id.titleJob);
            this.companyName = (TextView) itemView.findViewById(R.id.companyName);
            this.jobDate = (TextView) itemView.findViewById(R.id.jobDate);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null){
                        listener.onItemClick(v,getLayoutPosition());
                    }
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (longListener != null){
                        longListener.onItemLongClick(v,getLayoutPosition());
                        return true;
                    }
                    return false;
                }
            });
        }

        void clearAnimation() {
            itemView.clearAnimation();
        }
    }

    @Override
    public ItemSearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_element, parent, false);
        return new ItemSearchViewHolder(v);

    }

    @Override
    public void onBindViewHolder(ItemSearchViewHolder holder, int position) {
        holder.rowContainer.setTag(jobList.get(position).getId());
        setAnimation(holder.rowContainer);
        holder.titleJob.setText(jobList.get(position).getTitle());
        holder.companyName.setText(jobList.get(position).getCompany());
        holder.jobDate.setText(jobList.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        if (jobList.isEmpty()){
            return 0;
        }else {
            return jobList.size();
        }
    }

    @Override
    public void onViewDetachedFromWindow(ItemSearchViewHolder holder) {
        holder.clearAnimation();
    }

    private void setAnimation(LinearLayout container) {
        Animation animation = AnimationUtils.loadAnimation(mContext, android.R.anim.slide_in_left);
        container.startAnimation(animation);
    }


}
