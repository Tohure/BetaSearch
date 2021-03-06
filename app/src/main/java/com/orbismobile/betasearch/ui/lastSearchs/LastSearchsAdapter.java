package com.orbismobile.betasearch.ui.lastSearchs;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.orbismobile.betasearch.R;
import com.orbismobile.betasearch.model.db.LastSearch;

import java.util.List;

/**
 * Created by tohure on 20/04/17.
 */

public class LastSearchsAdapter extends RecyclerView.Adapter<LastSearchsAdapter.LastItemViewHolder>  {

    private List<LastSearch> lastSearches;
    private static EventsListeners.OnItemClickListener listener;
    private static EventsListeners.OnItemLongClickListener longListener;

    public void setOnItemClickListener(EventsListeners.OnItemClickListener listener) { LastSearchsAdapter.listener = listener; }
    public void setOnItemLongClickListener(EventsListeners.OnItemLongClickListener listener) { LastSearchsAdapter.longListener = listener; }

    public LastSearchsAdapter() { }

    public void addItemList(List<LastSearch> lastSearches){
        this.lastSearches = lastSearches;
    }

    public class LastItemViewHolder extends RecyclerView.ViewHolder {

        private RelativeLayout rowContainer;
        private TextView titleSearch, locationSearch;
        private ImageView alertRing,filterSearch;

        public LastItemViewHolder(View itemView) {
            super(itemView);

            this.rowContainer = (RelativeLayout) itemView.findViewById(R.id.rowContainer);
            this.titleSearch = (TextView) itemView.findViewById(R.id.titleSearch);
            this.locationSearch = (TextView) itemView.findViewById(R.id.locationSearch);
            this.alertRing = (ImageView) itemView.findViewById(R.id.alertRing);
            this.filterSearch = (ImageView) itemView.findViewById(R.id.applyFilter);

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
    }

    @Override
    public LastItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_last_searchs_elements, parent, false);
        return new LastItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(LastItemViewHolder holder, int position) {
        holder.rowContainer.setTag(R.id.idLastSearch,lastSearches.get(position).getId());
        holder.rowContainer.setTag(R.id.querySearch,lastSearches.get(position).getQuery());
        holder.rowContainer.setTag(R.id.locationSearch,lastSearches.get(position).getPlace());
        holder.rowContainer.setTag(R.id.statusFilter,lastSearches.get(position).getFiltros());
        holder.titleSearch.setText(lastSearches.get(position).getQuery());
        holder.locationSearch.setText(" | "+lastSearches.get(position).getPlace());

        if (lastSearches.get(position).getAlerta()){
            //holder.alertRing.setImageDrawable(ResourcesCompat.getDrawable(mContext.getResources(), R.drawable.ic_alert_ring_green, null));
            holder.alertRing.setImageResource(R.drawable.ic_alert_ring_green);
        }else {
            holder.alertRing.setImageResource(R.drawable.ic_alert_ring_griss);
        }

        if (lastSearches.get(position).getFiltros()){
            //holder.alertRing.setImageDrawable(ResourcesCompat.getDrawable(mContext.getResources(), R.drawable.ic_alert_ring_green, null));
            holder.filterSearch.setImageResource(R.drawable.ic_filters_green);
        }else {
            holder.filterSearch.setImageResource(R.drawable.ic_filters_griss);
        }
    }

    @Override
    public int getItemCount() {
        if (lastSearches.isEmpty()){
            return 0;
        }else {
            return lastSearches.size();
        }
    }


}
