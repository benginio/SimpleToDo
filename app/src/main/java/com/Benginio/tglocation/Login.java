package com.Benginio.tglocation;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends Activity {

    private EditText tUser,tPass;
    private Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tUser=(EditText)findViewById(R.id.tUser);
        tPass=(EditText)findViewById(R.id.tPass);
        btnLogin=(Button)findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tUser.getText().toString().equalsIgnoreCase("tgl") && tPass.getText().toString().equalsIgnoreCase("pass") ){
                    Intent intent = new Intent(Login.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                    tPass.setText(null);

                }else if(tUser.getText().toString().equalsIgnoreCase("") || tPass.getText().toString().equalsIgnoreCase("")){
                    Toast.makeText(getApplicationContext(),"vous avez laisse des champs vide",Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Invalide UserName or password",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}