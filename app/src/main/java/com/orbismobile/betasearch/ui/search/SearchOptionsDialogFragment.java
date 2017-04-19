package com.orbismobile.betasearch.ui.search;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.orbismobile.betasearch.R;
import com.orbismobile.betasearch.ui.detalleJob.DetailJobActivity;

/**
 * Created by tohure on 19/04/17.
 */

public class SearchOptionsDialogFragment extends DialogFragment implements View.OnClickListener {

    private View rootView;
    private TextView verAnuncio, reportAnuncio, shareAnuncio;
    private String idJob;

    public SearchOptionsDialogFragment() { }

    @SuppressWarnings("SameParameterValue")
    public static SearchOptionsDialogFragment newInstance(String s) {
        SearchOptionsDialogFragment sodf = new SearchOptionsDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString("idjob", s);
        sodf.setArguments(bundle);

        return sodf;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        idJob = getArguments().getString("idjob");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search_dialog_options, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        rootView = view;
        initUI();
    }

    private void initUI() {
        verAnuncio = (TextView) rootView.findViewById(R.id.verAnuncio);
        verAnuncio.setOnClickListener(this);
        reportAnuncio = (TextView) rootView.findViewById(R.id.reportAnuncio);
        reportAnuncio.setOnClickListener(this);
        shareAnuncio = (TextView) rootView.findViewById(R.id.shareAnuncio);
        shareAnuncio.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.verAnuncio:
                Intent searchIntent = new Intent(getContext(), DetailJobActivity.class);
                searchIntent.putExtra("idJob", idJob);

                startActivity(searchIntent);
                getActivity().overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                break;
            case R.id.reportAnuncio:
                Toast.makeText(getContext(), "Se ha mandado el aviso para su revisión, gracias.", Toast.LENGTH_SHORT).show();
                break;
            case R.id.shareAnuncio:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Ey revisa este anuncio de empleo que tengo aqui.");
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
                break;
        }
        dismiss();
    }
}
