package com.orbismobile.betasearch.ui.filters;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.orbismobile.betasearch.R;
import com.orbismobile.betasearch.model.db.Filter;

public class FilterActivity extends AppCompatActivity {

    private EditText filterSalary;
    private Toolbar toolbar;
    private String salary = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        Bundle b = getIntent().getExtras();

        if (b != null) {
            salary = b.getString("filter");
        }

        initUI();
        //injectPresenter();
        setupToolbar();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Filter filter = new Filter(0,filterSalary.getText().toString());
                Intent returnIntent = new Intent();
                returnIntent.putExtra("filter",filter);
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
            }
        });
    }

    private void setupToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Aplicar Filtros");
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
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        filterSalary = (EditText) findViewById(R.id.filterSalary);

        if (!salary.equals("")){
            filterSalary.setText(salary);
        }
    }

}
