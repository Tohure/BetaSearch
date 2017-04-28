package com.orbismobile.betasearch.ui.lastSearchs;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.orbismobile.betasearch.R;
import com.orbismobile.betasearch.model.db.LastSearch;
import com.orbismobile.betasearch.ui.search.SearchListActivity;

import java.util.ArrayList;
import java.util.List;

public class LastSearchsFragment extends Fragment implements LastSearchView {

    private LastSearchPresenter lastSearchPresenter;
    private ProgressBar progressBar;
    private View rootView;
    private LastSearchsAdapter lastSearchsAdapter;
    private List<LastSearch> lastSearchList = new ArrayList<>();

    public LastSearchsFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_searchs, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        rootView = view;
        injectPresenter();
        initUI();
    }

    @Override
    public void onResume() {
        super.onResume();
        lastSearchPresenter.getLastSearchs();
    }

    private void injectPresenter() {
        lastSearchPresenter = new LastSearchPresenter();
        lastSearchPresenter.attachedView(this,getContext());
    }

    private void initUI() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL);
        RecyclerView lastSearchRecycler = (RecyclerView) rootView.findViewById(R.id.lastSearchRecycler);
        lastSearchRecycler.setLayoutManager(layoutManager);
        lastSearchRecycler.setHasFixedSize(true);
        lastSearchRecycler.addItemDecoration(itemDecoration);
        progressBar = (ProgressBar) rootView.findViewById(R.id.progressBar);

        lastSearchsAdapter = new LastSearchsAdapter();
        lastSearchsAdapter.setOnItemClickListener(new EventsListeners.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                Intent searchIntent = new Intent(getContext(), SearchListActivity.class);
                searchIntent.putExtra("query", itemView.getTag(R.id.querySearch).toString());
                searchIntent.putExtra("location", itemView.getTag(R.id.locationSearch).toString());
                searchIntent.putExtra("idLastSearch", (Integer) itemView.getTag(R.id.idLastSearch));
                searchIntent.putExtra("filter", (Boolean) itemView.getTag(R.id.statusFilter));

                startActivity(searchIntent);
                getActivity().overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
            }
        });

        lastSearchsAdapter.setOnItemLongClickListener(new EventsListeners.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(final View itemView, int position) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("¿Eliminar el historial?")
                        .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                lastSearchPresenter.deleteLastSearch((Integer) itemView.getTag(R.id.idLastSearch));
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        });
                // Create the AlertDialog object and return it
                builder.create().show();
            }
        });

        lastSearchRecycler.setAdapter(lastSearchsAdapter);
    }

    @Override
    public void listLastSearchsDone(List<LastSearch> listLastSearchs) {
        lastSearchList = listLastSearchs;
        setupAdapter();
    }

    @Override
    public void deleteDoneSearch() {
        lastSearchPresenter.getLastSearchs();
    }

    private void setupAdapter() {
        lastSearchsAdapter.addItemList(lastSearchList);
        lastSearchsAdapter.notifyDataSetChanged();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void listLastSearchsFail(String message) { }

    @Override
    public void deleteFailSearch(String message) { }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        lastSearchPresenter.detachView();
    }
}
