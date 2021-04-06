package com.dogannn.getsocial;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private EditText inputCity;
    private EventApiClient service;
    private Button submit;
    private EventsAdapter eventsAdapter;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerView recyclerView;
    private TextView welcomeText;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputCity = findViewById(R.id.editText);
        submit = findViewById(R.id.button);
        recyclerView =findViewById(R.id.recyclerView);
        welcomeText=findViewById(R.id.welcomeText);
        progressBar=findViewById(R.id.progressBar);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        APIConfig();


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                welcomeText.setVisibility(View.INVISIBLE);
                progressBar.setVisibility(View.VISIBLE);
                Call<Events> eventsCall = service.getEvents(inputCity.getText().toString(),"eventDateLocal asc",20);
                eventsCall.enqueue(new Callback<Events>() {
                    @Override
                    public void onResponse(Call<Events> call, Response<Events> response) {
                        progressBar.setVisibility(View.INVISIBLE);
                        List<Events.Event> event = response.body().getEvents();
                        eventsAdapter = new EventsAdapter(MainActivity.this,event);
                        recyclerView.setLayoutManager(linearLayoutManager);
                        recyclerView.setAdapter(eventsAdapter);
                    }

                    @Override
                    public void onFailure(Call<Events> call, Throwable t) {

                    }
                });
            }
        });
    }

    public void APIConfig(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.stubhub.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(EventApiClient.class);
    }


}
