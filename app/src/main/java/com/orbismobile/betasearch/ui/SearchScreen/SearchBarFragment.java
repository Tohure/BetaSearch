package com.orbismobile.betasearch.ui.SearchScreen;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.AppCompatImageView;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.orbismobile.betasearch.R;
import com.orbismobile.betasearch.model.response.JobsResponse;

import java.util.List;

public class SearchBarFragment extends DialogFragment implements View.OnClickListener, TextView.OnEditorActionListener {

    private EditText search_bar, location_bar;
    private AppCompatImageView dialogImgClose, dialogImgBack;
    private View rootView;

    public SearchBarFragment() { }

    @SuppressWarnings("SameParameterValue")
    public static SearchBarFragment newInstance(){
        return new SearchBarFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        WindowManager.LayoutParams params = getDialog().getWindow().getAttributes();
        params.gravity = Gravity.CENTER_HORIZONTAL | Gravity.TOP;
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        getDialog().getWindow().setAttributes(params);

        return inflater.inflate(R.layout.fragment_search_bar, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        rootView = view;
        initUI();
    }


    private void initUI() {
        dialogImgBack = (AppCompatImageView) rootView.findViewById(R.id.dialogImgBack);
        dialogImgBack.setOnClickListener(this);

        dialogImgClose = (AppCompatImageView) rootView.findViewById(R.id.dialogImgClose);
        dialogImgClose.setOnClickListener(this);

        location_bar = (EditText) rootView.findViewById(R.id.location_bar);
        location_bar.setOnEditorActionListener(this);

        search_bar = (EditText) rootView.findViewById(R.id.search_bar);
        search_bar.setOnEditorActionListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dialogImgClose:
                search_bar.setText("");
                break;
            case R.id.dialogImgBack:
                //close
                break;
        }
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

        if (v.getId() == R.id.search_bar && actionId == EditorInfo.IME_ACTION_SEARCH) {
            location_bar.requestFocus();
            return true;
        }

        if (v.getId() == R.id.location_bar && actionId == EditorInfo.IME_ACTION_SEARCH) {
            //searchPresenter.getJobsSearch(search_bar.getText().toString());
            return true;
        }

        return false;
    }
}
