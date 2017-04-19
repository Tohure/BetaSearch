package com.orbismobile.betasearch.data;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.orbismobile.betasearch.R;
import com.orbismobile.betasearch.model.response.JobSearchResponse;

import java.util.List;

/**
 * Created by tohure on 19/04/17.
 */

public class RecomendsAdapter extends RecyclerView.Adapter<RecomendsAdapter.ItemRecomViewHolder> {

    private List<JobSearchResponse.DataBean> jobList;
    private static RecomendsAdapter.OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        RecomendsAdapter.listener = listener;
    }

    public RecomendsAdapter(List<JobSearchResponse.DataBean> jobList) {
        this.jobList = jobList;
    }

    public static class ItemRecomViewHolder extends RecyclerView.ViewHolder {

        private CardView cardContainer;
        private TextView titleJobReco, placeJobReco;

        public ItemRecomViewHolder(View itemView) {
            super(itemView);

            this.cardContainer = (CardView) itemView.findViewById(R.id.cardContainer);
            this.titleJobReco = (TextView) itemView.findViewById(R.id.titleJobReco);
            this.placeJobReco = (TextView) itemView.findViewById(R.id.placeJobReco);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onItemClick(v, getLayoutPosition());
                    }
                }
            });
        }

    }

    @Override
    public ItemRecomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recomend_element, parent, false);
        return new ItemRecomViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ItemRecomViewHolder holder, int position) {
        holder.cardContainer.setTag(jobList.get(position).getId());
        holder.titleJobReco.setText(jobList.get(position).getTitle());
        holder.placeJobReco.setText(jobList.get(position).getPlace());
    }

    @Override
    public int getItemCount() {
        return jobList.size();
    }
}
