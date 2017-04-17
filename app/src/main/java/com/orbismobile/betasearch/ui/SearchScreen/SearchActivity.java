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
import android.widget.TextView;

import com.orbismobile.betasearch.R;
import com.orbismobile.betasearch.model.Job;

import java.util.List;

public class SearchActivity extends AppCompatActivity implements SearchView, View.OnClickListener {

    private EditText search_bar;
    private SearchPresenter searchPresenter;
    private AppCompatImageView dialogImgClose;
    private int totalChars = 0;
    private String querySearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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


    private void injectPresenter() {
        searchPresenter = new SearchPresenter();
        searchPresenter.attachedView(this);
    }

    private void initUI() {

        dialogImgClose = (AppCompatImageView) findViewById(R.id.dialogImgClose);
        dialogImgClose.setOnClickListener(this);

        search_bar = (EditText) findViewById(R.id.search_bar);
        hideSoftKeyboard();
        search_bar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                totalChars = s.length();
                querySearch = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        search_bar.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if (totalChars >= 6){
                        searchPresenter.getJobsSearch(querySearch);
                    }
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public void getJobsDone(List<Job> jobs) {

    }

    @Override
    public void getJobsFail(String message) {

    }

    @Override
    public void getJobsError(String message) {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.dialogImgClose:
                search_bar.setText("");
                break;

        }
    }

    public void hideSoftKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(
                Context.INPUT_METHOD_SERVICE
        );
        imm.hideSoftInputFromWindow(search_bar.getWindowToken(), 0);
    }
}
