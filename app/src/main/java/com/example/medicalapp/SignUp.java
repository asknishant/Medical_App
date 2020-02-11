package com.example.medicalapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;

public class SignUp extends AppCompatActivity {

    EditText name,userName,email,password;
    Button login,signup;
    private FirebaseAuth mAuth;
    private String newEmail,newPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        name=findViewById(R.id.name);
        userName=findViewById(R.id.userName);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        signup=findViewById(R.id.signup);
        login=findViewById(R.id.login);



        mAuth = FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SignUp.this,Login.class);
                startActivity(intent);
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                newEmail=email.getText().toString();
                newPassword=password.getText().toString();

                if(newEmail.isEmpty())
                {
                    email.setError("Enter Name");
                    email.requestFocus();
                    return;
                }
                if(newPassword.isEmpty())
                {
                    password.setError("Enter Id");
                    password.requestFocus();
                    return;
                }


                mAuth.createUserWithEmailAndPassword(newEmail, newPassword)
                        .addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    Toast.makeText(SignUp.this,"Successful taken id",Toast.LENGTH_SHORT).show();

                                } else {
                                        if(task.getException() instanceof FirebaseAuthUserCollisionException)
                                            Toast.makeText(SignUp.this,"You are already registered successful",Toast.LENGTH_SHORT);
                                        else
                                            Toast.makeText(SignUp.this,"Error registering",Toast.LENGTH_SHORT).show();
                                }


                            }
                        });
            }
        });
    }
}
