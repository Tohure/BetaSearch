package com.orbismobile.betasearch.ui.search;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.AppCompatImageView;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.orbismobile.betasearch.R;

public class SearchBarFragment extends DialogFragment implements View.OnClickListener, TextView.OnEditorActionListener {

    private EditText search_bar, location_bar;
    private AppCompatImageView dialogImgClose, dialogImgBack;
    private View rootView;
    private Dialog dialog;
    public SearchBarFragment() { }

    @SuppressWarnings("SameParameterValue")
    public static SearchBarFragment newInstance(){
        return new SearchBarFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        dialog = getDialog();

        if (dialog != null && dialog.getWindow() != null){
            WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
            params.gravity = Gravity.CENTER_HORIZONTAL | Gravity.TOP;
            params.width = ViewGroup.LayoutParams.MATCH_PARENT;
            params.windowAnimations = R.style.DialogAnimation;
            dialog.getWindow().setAttributes(params);
        }

        return inflater.inflate(R.layout.fragment_search_bar, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        if (dialog != null && dialog.getWindow() != null){
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
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
                dismiss();
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
            Intent searchIntent = new Intent(getContext(), SearchListActivity.class);
            searchIntent.putExtra("query", search_bar.getText().toString());
            searchIntent.putExtra("location", location_bar.getText().toString());

            startActivity(searchIntent);
            getActivity().overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
            dismiss();
            return true;
        }

        return false;
    }
}
