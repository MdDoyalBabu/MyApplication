package com.example.application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateAccountActivity extends AppCompatActivity {


    private EditText firstName,lastName,email,password;
    private Button createNowbutton;
    private FirebaseAuth mAuth;

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        mAuth = FirebaseAuth.getInstance();
        databaseReference= FirebaseDatabase.getInstance().getReference("Abidar+shorab");


        firstName=findViewById(R.id.firstName_Edittext_Id);
        lastName=findViewById(R.id.lastName_Edittext_Id);
        email=findViewById(R.id.emailAddress_Edittext_Id);
        password=findViewById(R.id.password_Edittext_Id);
        createNowbutton=findViewById(R.id.createNow_button_Id);

        createNowbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String cfirstName=firstName.getText().toString();
                final String cLastName=lastName.getText().toString();
                final String cEmailAddress=email.getText().toString();
                final String cPassword=password.getText().toString();

                mAuth.createUserWithEmailAndPassword(cEmailAddress, cPassword)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()) {
                                  LoginDataHandler loginDataHandler=new LoginDataHandler(cfirstName,cLastName,cEmailAddress,cPassword);

                                  databaseReference.child("UserValue").child(databaseReference.push().getKey()).setValue(loginDataHandler).addOnCompleteListener(new OnCompleteListener<Void>() {
                                      @Override
                                      public void onComplete(@NonNull Task<Void> task) {

                                          if (task.isSuccessful()){
                                              Toast.makeText(CreateAccountActivity.this, "Successful", Toast.LENGTH_SHORT).show();
                                              Intent intent=new Intent(CreateAccountActivity.this,MainActivity.class);
                                              startActivity(intent);
                                          }

                                      }
                                  });

                                }

                            }
                        });


            }
        });

    }
}
