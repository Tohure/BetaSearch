package com.orbismobile.betasearch.ui.lastSearchs;

import android.view.View;

/**
 * Created by tohure on 28/04/17.
 */

public interface EventsListeners {

    interface OnItemClickListener { void onItemClick(View itemView, int position); }
    interface OnItemLongClickListener { void onItemLongClick(View itemView, int position); }
}
