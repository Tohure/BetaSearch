package com.orbismobile.betasearch.ui.applies;

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
import com.orbismobile.betasearch.model.db.ApplyJob;
import com.orbismobile.betasearch.ui.detalleJob.DetailJobActivity;

import java.util.ArrayList;
import java.util.List;

public class AppliesFragment extends Fragment implements AppliesView {

    private AppliesPresenter appliesPresenter;
    private ProgressBar progressBar;
    private View rootView;
    private AppliesAdapter appliesAdapter;
    private List<ApplyJob> applyJobList = new ArrayList<>();

    public AppliesFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_applies, container, false);
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
        appliesPresenter.getApplies();
    }

    private void injectPresenter() {
        appliesPresenter = new AppliesPresenter();
        appliesPresenter.attachedView(this,getContext());
    }

    private void initUI() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL);
        RecyclerView appliesRecycler = (RecyclerView) rootView.findViewById(R.id.myAppliesRecycler);
        appliesRecycler.setLayoutManager(layoutManager);
        appliesRecycler.setHasFixedSize(true);
        appliesRecycler.addItemDecoration(itemDecoration);
        progressBar = (ProgressBar) rootView.findViewById(R.id.progressBar);

        appliesAdapter = new AppliesAdapter(getContext());
        appliesAdapter.setOnItemClickListener(new AppliesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                Intent searchIntent = new Intent(getContext(), DetailJobActivity.class);
                searchIntent.putExtra("idJob", itemView.getTag().toString());

                startActivity(searchIntent);
                getActivity().overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
            }
        });

        appliesRecycler.setAdapter(appliesAdapter);
    }

    @Override
    public void listAppliesDone(List<ApplyJob> applyJobList) {
        this.applyJobList = applyJobList;
        setupAdapter();
    }

    private void setupAdapter() {
        appliesAdapter.addItemList(applyJobList);
        appliesAdapter.notifyDataSetChanged();
    }

    @Override
    public void listAppliesFail(String message) {

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
        appliesPresenter.detachView();
    }
}
