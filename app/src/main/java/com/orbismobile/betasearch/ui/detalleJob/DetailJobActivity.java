package com.orbismobile.betasearch.ui.detalleJob;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.orbismobile.betasearch.R;
import com.orbismobile.betasearch.data.RecomendsAdapter;
import com.orbismobile.betasearch.model.response.JobSearchResponse;
import com.orbismobile.betasearch.ui.activities.TabActivity;

import java.util.List;


public class DetailJobActivity extends AppCompatActivity implements DetailView {

    private String idJob = "";
    private DetailPresenter detailPresenter;
    private ProgressBar progressBar;
    private TextView descriptionJob, companyJob, dateJob, placeJob, salaryJob;
    private Toolbar toolbar;
    private RecyclerView rycRecomendados;
    private RecomendsAdapter recomendsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_job);

        Bundle b = getIntent().getExtras();
        if (b != null) {
            idJob = b.getString("idJob");
        }

        initUI();
        injectPresenter();
        setupToolbar();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Postulación Correcta", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.miShare: {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Ey revisa este anuncio de empleo que tengo aqui.");
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
                break;
            }
            case R.id.miHome: {
                Intent searchIntent = new Intent(this, TabActivity.class);
                searchIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(searchIntent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
                break;
            }
        }
        return false;
    }

    private void initUI() {
        rycRecomendados = (RecyclerView) findViewById(R.id.rycRecomendados);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, LinearLayoutManager.HORIZONTAL);
        rycRecomendados.setLayoutManager(layoutManager);
        rycRecomendados.setHasFixedSize(true);
        rycRecomendados.addItemDecoration(itemDecoration);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        descriptionJob = (TextView) findViewById(R.id.descriptionJob);
        companyJob = (TextView) findViewById(R.id.companyJob);
        dateJob = (TextView) findViewById(R.id.dateJob);
        placeJob = (TextView) findViewById(R.id.placeJob);
        salaryJob = (TextView) findViewById(R.id.salaryJob);
    }

    private void injectPresenter() {
        detailPresenter = new DetailPresenter();
        detailPresenter.attachedView(this,getApplicationContext());
        detailPresenter.getJobDetail(idJob);
    }

    private void setupToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
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

    @Override
    public void detailJobsDone(JobSearchResponse.DataBean job) {
        toolbar.setTitle(job.getTitle());
        descriptionJob.setText(job.getDescription());
        companyJob.setText(job.getCompany());
        dateJob.setText(job.getDate());
        placeJob.setText(job.getPlace());
        salaryJob.setText("S/. " + job.getSalary());

        detailPresenter.getJobsRecomend(job.getKeywords(), String.valueOf(job.getId()));
    }

    @Override
    public void recoJobsDone(List<JobSearchResponse.DataBean> jobs) {
        setupAdapterRecomends(jobs);
    }

    private void setupAdapterRecomends(List<JobSearchResponse.DataBean> jobs) {
        recomendsAdapter = new RecomendsAdapter(jobs);
        recomendsAdapter.setOnItemClickListener(new RecomendsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                Intent searchIntent = new Intent(getApplicationContext(), DetailJobActivity.class);
                searchIntent.putExtra("idJob", itemView.getTag().toString());

                startActivity(searchIntent);
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            }
        });
        rycRecomendados.setAdapter(recomendsAdapter);
    }

    //<editor-fold desc="ERROR HANDLERS">
    @Override
    public void detailJobsFail(String message) {

    }

    @Override
    public void detailJobsError(String message) {

    }

    @Override
    public void recoJobsFail(String message) {

    }

    @Override
    public void recoJobsError(String message) {

    }
    //</editor-fold>

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
        detailPresenter.detachView();
    }
}
