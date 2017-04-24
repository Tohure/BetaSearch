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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initUI();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Filter filter = new Filter(0,filterSalary.getText().toString());
                Intent returnIntent = new Intent();
                returnIntent.putExtra("filter",filter);
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
                Snackbar.make(view, "Filtro Aplicado", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void initUI() {
        filterSalary = (EditText) findViewById(R.id.filterSalary);
    }

}
