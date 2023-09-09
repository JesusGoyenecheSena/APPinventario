package com.inventario.appinventario;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {


    private FirebaseAuth mAuth;

    private EditText ingreseCorreo;
    private EditText ingreseContrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ingreseCorreo = findViewById(R.id.ingreseCorreo);
        ingreseContrasena = findViewById(R.id.ingreseContrasena);

        mAuth = FirebaseAuth.getInstance();
    }
    public void irRegistrarse(View view){
        Intent i = new Intent(this, registro.class);
        startActivity(i);
    }

    public void onStart(){
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    public void iniciarSesion (View view){


        mAuth.signInWithEmailAndPassword(ingreseCorreo.getText().toString(),ingreseContrasena.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                        FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(getApplicationContext(), "Autenticacion Correta", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(getApplicationContext(), menuPrincipal.class);
                            startActivity(i);
                        }else {
                            Toast.makeText(getApplicationContext(), "Autenticacion fallida", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}