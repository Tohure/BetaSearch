package com.orbismobile.betasearch.ui.search;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.orbismobile.betasearch.R;
import com.orbismobile.betasearch.data.JobsAdapter;
import com.orbismobile.betasearch.model.response.JobsResponse;

import java.util.List;

import static java.security.AccessController.getContext;

public class SearchListActivity extends AppCompatActivity implements SearchView {

    private SearchPresenter searchPresenter;
    private String query,location;
    private ProgressBar progressBar;
    private RecyclerView list_recycler;
    private LinearLayoutManager layoutManager;
    private JobsAdapter jobOfferAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_list);

        Bundle b = getIntent().getExtras();

        if(b != null){
            query = b.getString("query");
            location = b.getString("location");
        }

        injectPresenter();
        initUI();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void initUI() {
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, LinearLayoutManager.VERTICAL);
        list_recycler = (RecyclerView) findViewById(R.id.list_recycler);
        list_recycler.setLayoutManager(layoutManager);
        list_recycler.setHasFixedSize(true);
        list_recycler.addItemDecoration(itemDecoration);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        searchPresenter.getJobsSearch(query,location);
    }

    private void injectPresenter() {
        searchPresenter = new SearchPresenter();
        searchPresenter.attachedView(this);
    }

    @Override
    public void listJobsDone(List<JobsResponse.DataBean> jobs) {

        setupAdapter(jobs);
        list_recycler.setAdapter(jobOfferAdapter);
    }

    private void setupAdapter(List<JobsResponse.DataBean> jobs) {
        jobOfferAdapter = new JobsAdapter(this, jobs);
        jobOfferAdapter.setOnItemClickListener(new JobsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                Log.d("thr click",itemView.getTag().toString());
            }
        });

        jobOfferAdapter.setOnItemLongClickListener(new JobsAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(View itemView, int position) {
                Log.d("thr long-click",itemView.getTag().toString());
            }
        });
    }

    @Override
    public void listJobsFail(String message) {

    }

    @Override
    public void listJobsError(String message) {

    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }
}
