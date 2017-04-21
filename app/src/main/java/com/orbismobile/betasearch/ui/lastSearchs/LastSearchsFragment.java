package com.orbismobile.betasearch.ui.lastSearchs;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.orbismobile.betasearch.R;
import com.orbismobile.betasearch.data.LastSearchsAdapter;
import com.orbismobile.betasearch.model.db.LastSearch;
import com.orbismobile.betasearch.ui.search.SearchListActivity;

import java.util.List;

public class LastSearchsFragment extends Fragment implements LastSearchView {

    private LastSearchPresenter lastSearchPresenter;
    private ProgressBar progressBar;
    private RecyclerView lastSearchRecycler;
    private View rootView;

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

    private void injectPresenter() {
        lastSearchPresenter = new LastSearchPresenter();
        lastSearchPresenter.attachedView(this,getContext());
    }

    private void initUI() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL);
        lastSearchRecycler = (RecyclerView) rootView.findViewById(R.id.lastSearchRecycler);
        lastSearchRecycler.setLayoutManager(layoutManager);
        lastSearchRecycler.setHasFixedSize(true);
        lastSearchRecycler.addItemDecoration(itemDecoration);
        progressBar = (ProgressBar) rootView.findViewById(R.id.progressBar);
        lastSearchPresenter.getLastSearchs();
    }

    @Override
    public void listLastSearchsDone(List<LastSearch> listLastSearchs) {
        setupAdapter(listLastSearchs);
    }

    private void setupAdapter(List<LastSearch> listLastSearchs) {
        LastSearchsAdapter lastSearchsAdapter = new LastSearchsAdapter(listLastSearchs,getContext());
        lastSearchsAdapter.setOnItemClickListener(new LastSearchsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                Intent searchIntent = new Intent(getContext(), SearchListActivity.class);
                searchIntent.putExtra("query", itemView.getTag(R.id.querySearch).toString());
                searchIntent.putExtra("location", itemView.getTag(R.id.locationSearch).toString());

                startActivity(searchIntent);
                getActivity().overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
            }
        });
        lastSearchRecycler.setAdapter(lastSearchsAdapter);
    }

    @Override
    public void listLastSearchsFail(String message) {

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
    public void onDestroyView() {
        super.onDestroyView();
        lastSearchPresenter.detachView();
    }
}
