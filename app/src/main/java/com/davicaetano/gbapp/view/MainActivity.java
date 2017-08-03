package com.davicaetano.gbapp.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.davicaetano.gbapp.GbApp;
import com.davicaetano.gbapp.R;

public class MainActivity extends AppCompatActivity {
    private MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainPresenter = ((GbApp)getApplication()).getMainPresenter(this);

        findViewById(R.id.api_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                connectApi();
            }
        });
    }

    private void connectApi() {
        mainPresenter.apiOnClick();
    }
}
