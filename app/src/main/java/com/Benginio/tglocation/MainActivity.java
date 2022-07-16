package com.Benginio.tglocation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import modele.Voiture;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    LinearLayout linearLayout=( LinearLayout)findViewById(R.id.addcar);
    linearLayout.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(MainActivity.this,AddVoiture.class));
        }
    });

        LinearLayout linearLayout1=( LinearLayout)findViewById(R.id.listcar);
        linearLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ListVoiture.class));
            }
        });

    }

    // la partie Menu( option menu)

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater =getMenuInflater();//MenuInflate est une classe utiliser pour instancier les fichier xml de menu en object menu
        inflater.inflate(R.menu.optionmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.add:

                startActivity(new Intent(MainActivity.this,AddVoiture.class));
                return true;
            case R.id.vider:
                //lvpatient.setAdapter(null);
                Voiture obj= new Voiture();
                Voiture.deleteAll(MainActivity.this,obj.getId());
                startActivity(new Intent(MainActivity.this,ListVoiture.class));
                finish();
                return true;
            case R.id.loggout:
                //pour le button log out
                startActivity(new Intent(MainActivity.this,Login.class));
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}