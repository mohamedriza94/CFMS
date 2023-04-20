package com.example.cfms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.ktx.Firebase;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    CupcakeAdapter cupcakeAdapter;
    FloatingActionButton floatingActionButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView)findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<CupcakeModel> options =
                new FirebaseRecyclerOptions.Builder<CupcakeModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("cupcakes"), CupcakeModel.class)
                        .build();

        cupcakeAdapter = new CupcakeAdapter(options);
        recyclerView.setAdapter(cupcakeAdapter);
        floatingActionButton = (FloatingActionButton)findViewById(R.id.floatingActionButton);

        floatingActionButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),AddCupcakeActivity.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        cupcakeAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        cupcakeAdapter.stopListening();
    }
}