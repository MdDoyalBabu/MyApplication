package com.example.application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartActivity extends AppCompatActivity implements View.OnClickListener {


    private Button createButton,createUserCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        createButton=findViewById(R.id.create_Account_button_Id);
        createUserCode=findViewById(R.id.crate_UseCode_button_Id);

        createButton.setOnClickListener(this);
        createUserCode.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if (v.getId()==R.id.create_Account_button_Id){

            Intent intent=new Intent(StartActivity.this,CreateAccountActivity.class);
            startActivity(intent);
        }

    }
}
