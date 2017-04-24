package com.orbismobile.betasearch.ui.search;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.orbismobile.betasearch.R;
import com.orbismobile.betasearch.model.db.Filter;
import com.orbismobile.betasearch.model.response.JobSearchResponse;
import com.orbismobile.betasearch.ui.detalleJob.DetailJobActivity;
import com.orbismobile.betasearch.ui.filters.FilterActivity;

import java.util.List;

public class SearchListActivity extends AppCompatActivity implements SearchView, View.OnClickListener {

    private SearchPresenter searchPresenter;
    private String query = "Cargo", location = "Lima";
    private Boolean filter = false;
    private int idLastSearch = 0;
    private ProgressBar progressBar;
    private RecyclerView list_recycler;
    private FloatingActionMenu fabMenu;
    private CoordinatorLayout container;
    private FloatingActionButton fabAlarm, fabFilter;
    static final int FILTER_REQUEST = 777;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_list);

        Bundle b = getIntent().getExtras();

        if (b != null) {
            query = b.getString("query");
            location = b.getString("location");
            idLastSearch = b.getInt("idLastSearch");
            filter = b.getBoolean("filter");
        }

        setupToolbar();
        injectPresenter();
        initUI();
    }

    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(query);
        toolbar.setSubtitle(location);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initUI() {
        container = (CoordinatorLayout) findViewById(R.id.container);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, LinearLayoutManager.VERTICAL);
        list_recycler = (RecyclerView) findViewById(R.id.list_recycler);
        list_recycler.setLayoutManager(layoutManager);
        list_recycler.setHasFixedSize(true);
        list_recycler.addItemDecoration(itemDecoration);

        fabMenu = (FloatingActionMenu) findViewById(R.id.menu_actions);
        fabAlarm = (FloatingActionButton) findViewById(R.id.float_menu_alarm);
        fabFilter = (FloatingActionButton) findViewById(R.id.float_menu_filter);
        searchPresenter.getJobsSearch(query, location);

        if (idLastSearch > 0) {
            updateFabButtons();
        }
    }

    private void injectPresenter() {
        searchPresenter = new SearchPresenter();
        searchPresenter.attachedView(this, getApplicationContext());
    }

    @Override
    public void listJobsDone(List<JobSearchResponse.DataBean> jobs) {
        setupAdapter(jobs);
        searchPresenter.saveQueryinDB(query, location);
    }

    private void updateFabButtons() {
        if (searchPresenter.getStatusAlarm(idLastSearch)) {
            fabAlarm.setImageResource(R.drawable.ic_bell_active);
        } else {
            fabAlarm.setImageResource(R.drawable.ic_bell_deactive);
        }
        fabAlarm.setOnClickListener(this);
        fabFilter.setOnClickListener(this);
        fabMenu.setVisibility(View.VISIBLE);
    }

    private void setupAdapter(List<JobSearchResponse.DataBean> jobs) {
        JobsAdapter jobOfferAdapter = new JobsAdapter(this, jobs);
        jobOfferAdapter.setOnItemClickListener(new JobsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                Intent searchIntent = new Intent(getApplicationContext(), DetailJobActivity.class);
                searchIntent.putExtra("idJob", itemView.getTag().toString());

                startActivity(searchIntent);
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            }
        });

        jobOfferAdapter.setOnItemLongClickListener(new JobsAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(View itemView, int position) {
                callDialog(itemView.getTag().toString());
            }
        });
        list_recycler.setAdapter(jobOfferAdapter);
    }

    private void callDialog(String s) {
        FragmentManager fm = this.getSupportFragmentManager();
        SearchOptionsDialogFragment searchOptionsDialogFragment = SearchOptionsDialogFragment.newInstance(s);
        searchOptionsDialogFragment.show(fm, "layout_search_bar");
    }

    @Override
    public void listJobsFail(String message) {

    }

    @Override
    public void listJobsError(String message) {

    }

    @Override
    public void saveQueryDone(int id) {
        idLastSearch = id;
        updateFabButtons();
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
    protected void onDestroy() {
        super.onDestroy();
        searchPresenter.detachView();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.float_menu_alarm:
                if (searchPresenter.getStatusAlarm(idLastSearch)) {
                    searchPresenter.setAlarm(idLastSearch, false);
                    Toast.makeText(this, "Notificaciones Desactivadas", Toast.LENGTH_SHORT).show();
                    fabAlarm.setImageResource(R.drawable.ic_bell_deactive);
                } else {
                    searchPresenter.setAlarm(idLastSearch, true);
                    Toast.makeText(this, "Notificaciones Activadas para esta búsqueda", Toast.LENGTH_SHORT).show();
                    fabAlarm.setImageResource(R.drawable.ic_bell_active);
                }
                fabMenu.close(true);
                break;
            case R.id.float_menu_filter:
                Intent searchIntent = new Intent(getApplicationContext(), FilterActivity.class);
                if (filter){ searchIntent.putExtra("filter","2500"); }
                startActivityForResult(searchIntent, FILTER_REQUEST);
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                fabMenu.close(true);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == FILTER_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                Filter filter = (Filter) data.getSerializableExtra("filter");
                searchPresenter.setFilter(idLastSearch, true);
                Snackbar.make(container, "Filtro Aplicado", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }

            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }

    }
}