package com.orbismobile.betasearch.data;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.orbismobile.betasearch.R;
import com.orbismobile.betasearch.model.db.LastSearch;

import java.util.List;

/**
 * Created by tohure on 20/04/17.
 */

public class LastSearchsAdapter extends RecyclerView.Adapter<LastSearchsAdapter.LastItemViewHolder> {

    private List<LastSearch> lastSearches;
    private Context mContext;
    private static LastSearchsAdapter.OnItemClickListener listener;

    public interface OnItemClickListener { void onItemClick(View itemView, int position); }

    public void setOnItemClickListener(OnItemClickListener listener) { LastSearchsAdapter.listener = listener; }

    public LastSearchsAdapter(List<LastSearch> lastSearches, Context mContext) {
        this.lastSearches = lastSearches;
        this.mContext = mContext;
    }

    public class LastItemViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout rowContainer;
        private TextView titleSearch, locationSearch;

        public LastItemViewHolder(View itemView) {
            super(itemView);

            this.rowContainer = (LinearLayout) itemView.findViewById(R.id.rowContainer);
            this.titleSearch = (TextView) itemView.findViewById(R.id.titleSearch);
            this.locationSearch = (TextView) itemView.findViewById(R.id.locationSearch);
        }
    }

    @Override
    public LastItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_last_searchs_elements, parent, false);
        return new LastItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(LastItemViewHolder holder, int position) {
        holder.rowContainer.setTag(R.id.querySearch,lastSearches.get(position).getQuery());
        holder.rowContainer.setTag(R.id.locationSearch,lastSearches.get(position).getPlace());
        holder.titleSearch.setText(lastSearches.get(position).getQuery());
        holder.locationSearch.setText(" | "+lastSearches.get(position).getPlace());
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
