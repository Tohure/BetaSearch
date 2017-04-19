package com.orbismobile.betasearch.ui.detalleJob;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.orbismobile.betasearch.R;
import com.orbismobile.betasearch.model.response.JobSearchResponse;


public class DetailJobActivity extends AppCompatActivity implements DetailView {

    private String idJob = "";
    private DetailPresenter detailPresenter;
    private ProgressBar progressBar;
    private TextView descriptionJob,companyJob,dateJob,placeJob,salaryJob;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_job);

        Bundle b = getIntent().getExtras();

        if (b != null) { idJob = b.getString("idJob"); }

        initUI();
        injectPresenter();
        setupToolbar();

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
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        descriptionJob = (TextView) findViewById(R.id.descriptionJob);
        companyJob = (TextView) findViewById(R.id.companyJob);
        dateJob = (TextView) findViewById(R.id.dateJob);
        placeJob = (TextView) findViewById(R.id.placeJob);
        salaryJob = (TextView) findViewById(R.id.salaryJob);
    }

    private void injectPresenter() {
        detailPresenter = new DetailPresenter();
        detailPresenter.attachedView(this);
        detailPresenter.getJobDetail(idJob);
    }

    private void setupToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null){
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

    @Override
    public void detailJobsDone(JobSearchResponse.DataBean job) {
        toolbar.setTitle(job.getTitle());

        descriptionJob.setText(job.getDescription());
        companyJob.setText(job.getCompany());
        dateJob.setText(job.getDate());
        placeJob.setText(job.getPlace());
        salaryJob.setText("S/. "+job.getSalary());

    }

    @Override
    public void detailJobsFail(String message) {

    }

    @Override
    public void detailJobsError(String message) {

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
