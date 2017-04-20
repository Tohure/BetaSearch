package com.orbismobile.betasearch.ui.lastSearchs;

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

import java.util.List;

public class LastSearchsFragment extends Fragment implements LastSearchView {

    private LastSearchPresenter lastSearchPresenter;
    private ProgressBar progressBar;
    private RecyclerView lastSearchRecycler;
    private LinearLayoutManager layoutManager;
    private View rootView;



    public LastSearchsFragment() {
        // Required empty public constructor
    }


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
