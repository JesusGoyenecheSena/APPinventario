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
import com.google.firebase.ktx.Firebase;

public class registro extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private EditText correoRegistro;
    private EditText contrasenaRegistro;
    private EditText  contrasenaRegsitro2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        mAuth = FirebaseAuth.getInstance();
        correoRegistro = findViewById(R.id.correoRegistro);
        contrasenaRegistro = findViewById(R.id.contrasenaRegistro);
        contrasenaRegsitro2 = findViewById(R.id.contrasenaRegsitro2);
    }

    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    public void registrarUsuario (View view){

        if (contrasenaRegistro.getText().toString().equals(contrasenaRegsitro2.getText().toString())){

            mAuth.createUserWithEmailAndPassword(correoRegistro.getText().toString(),contrasenaRegistro.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(getApplicationContext(),"Usuario Creado",Toast.LENGTH_SHORT).show();
                                FirebaseUser user = mAuth.getCurrentUser();

                                Intent i = new Intent(getApplicationContext(), menuPrincipal.class);
                                startActivity(i);

                            }else {
                                Toast.makeText(getApplicationContext(),"Su Autenticacion Fallo",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });



        }else {
            Toast.makeText(this,"Las contraseñas no coinsiden",Toast.LENGTH_SHORT).show();
        }


    }

}