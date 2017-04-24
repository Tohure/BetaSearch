package com.orbismobile.betasearch.data;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.orbismobile.betasearch.R;
import com.orbismobile.betasearch.model.db.ApplyJob;

import java.util.List;

/**
 * Created by crhto on 23/04/2017.
 */

public class AppliesAdapter extends RecyclerView.Adapter<AppliesAdapter.ApplyItemViewHolder> {

    private List<ApplyJob> applyJobList;
    private Context mContext;
    private static OnItemClickListener listener;

    public interface OnItemClickListener { void onItemClick(View itemView, int position); }

    public void setOnItemClickListener(OnItemClickListener listener) { AppliesAdapter.listener = listener; }

    public AppliesAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void addItemList(List<ApplyJob> applyJobList){
        this.applyJobList = applyJobList;
    }

    public class ApplyItemViewHolder extends RecyclerView.ViewHolder {

        private RelativeLayout rowContainer;
        private TextView titleJob, dateApply;

        public ApplyItemViewHolder(View itemView) {
            super(itemView);

            this.rowContainer = (RelativeLayout) itemView.findViewById(R.id.rowContainer);
            this.titleJob = (TextView) itemView.findViewById(R.id.titleJob);
            this.dateApply = (TextView) itemView.findViewById(R.id.dateApply);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null){
                        listener.onItemClick(v,getLayoutPosition());
                    }
                }
            });
        }
    }

    @Override
    public ApplyItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_apply_elements, parent, false);
        return new ApplyItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ApplyItemViewHolder holder, int position) {
        holder.rowContainer.setTag(applyJobList.get(position).getId());
        holder.titleJob.setText(applyJobList.get(position).getTitleJob());
        holder.dateApply.setText(applyJobList.get(position).getDateApply());
    }

    @Override
    public int getItemCount() {
        if (applyJobList.isEmpty()){
            return 0;
        }else {
            return applyJobList.size();
        }
    }

}
