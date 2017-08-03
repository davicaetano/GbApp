package com.davicaetano.gbapp.view;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.davicaetano.gbapp.GbApp;
import com.davicaetano.gbapp.R;
import com.davicaetano.gbapp.gbModel.GbEvent;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private MainPresenter mainPresenter;

    private TextView loadingTextView;
    private RecyclerView recyclerView;
    private MainAdapter mainAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainPresenter = ((GbApp)getApplication()).getMainPresenter(this);

        findViewById(R.id.main_api_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                connectApi();
            }
        });

        loadingTextView = findViewById(R.id.main_loading_text);

        recyclerView = findViewById(R.id.main_recycler_view);
        mainAdapter = new MainAdapter(mainPresenter.getGbEventList());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(false);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(mainAdapter);

        swipeRefreshLayout = findViewById(R.id.main_swipe_refresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                connectApi();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        connectApi();
    }

    private void connectApi() {
        loadingTextView.setText(R.string.loading);
        swipeRefreshLayout.setRefreshing(true);
        mainPresenter.apiOnClick();
    }

    public void refreshAdapter(List<GbEvent> gbEventList) {
        loadingTextView.setText("");
        swipeRefreshLayout.setRefreshing(false);
        mainAdapter.setGbEventList(gbEventList);
        mainAdapter.notifyDataSetChanged();
    }

    public void dataError() {
        swipeRefreshLayout.setRefreshing(false);
        loadingTextView.setText(R.string.loading_error);
    }
}
