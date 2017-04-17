package com.orbismobile.betasearch.ui.SearchScreen;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.orbismobile.betasearch.R;
import com.orbismobile.betasearch.model.Job;
import com.orbismobile.betasearch.model.response.JobsResponse;

import java.util.List;

public class SearchActivity extends AppCompatActivity implements SearchView, View.OnClickListener, TextView.OnEditorActionListener {

    private EditText search_bar,location_bar;
    private SearchPresenter searchPresenter;
    private AppCompatImageView dialogImgClose,dialogImgBack;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        injectPresenter();
        initUI();
    }


    private void injectPresenter() {
        searchPresenter = new SearchPresenter();
        searchPresenter.attachedView(this);
    }

    private void initUI() {
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        dialogImgBack = (AppCompatImageView) findViewById(R.id.dialogImgBack);
        dialogImgBack.setOnClickListener(this);

        dialogImgClose = (AppCompatImageView) findViewById(R.id.dialogImgClose);
        dialogImgClose.setOnClickListener(this);

        location_bar = (EditText) findViewById(R.id.location_bar);
        location_bar.setOnEditorActionListener(this);

        search_bar = (EditText) findViewById(R.id.search_bar);
        search_bar.setOnEditorActionListener(this);
    }

    @Override
    public void listJobsDone(List<JobsResponse.DataBean> jobs) {
        for (int i = 0; i < jobs.size(); i++){
            Log.d("thr",jobs.get(i).getTitle());
        }
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.dialogImgClose:
                search_bar.setText("");
                break;
            case R.id.dialogImgBack:
                finish();
                break;
        }
    }

    public void hideSoftKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(search_bar.getWindowToken(), 0);
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

        if (v.getId() == R.id.search_bar && actionId == EditorInfo.IME_ACTION_SEARCH){
            location_bar.requestFocus();
            return true;
        }

        if (v.getId() == R.id.location_bar && actionId == EditorInfo.IME_ACTION_SEARCH) {
            searchPresenter.getJobsSearch(search_bar.getText().toString());
            return true;
        }

        return false;
    }
}
