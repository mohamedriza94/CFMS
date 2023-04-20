package com.example.cfms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AddCupcakeActivity extends AppCompatActivity {
    EditText name,price,description,photoURL;
    Button btnAdd,btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cupcake);

        name = (EditText) findViewById(R.id.txtName);
        price = (EditText) findViewById(R.id.txtPrice);
        description = (EditText) findViewById(R.id.txtDescription);
        photoURL = (EditText) findViewById(R.id.txtPhotoURL);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnBack = (Button) findViewById(R.id.btnBack);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertData();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void insertData()
    {
        Map<String,Object> map = new HashMap<>();
        map.put("name",name.getText().toString());
        map.put("price",price.getText().toString());
        map.put("description",description.getText().toString());
        map.put("photo",photoURL.getText().toString());

        FirebaseDatabase.getInstance().getReference().child("cupcakes").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(AddCupcakeActivity.this,"Cupcake Added",Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddCupcakeActivity.this,"Failure. Try Again",Toast.LENGTH_SHORT).show();
                    }
                });
    }
}