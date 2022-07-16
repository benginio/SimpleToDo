package com.Benginio.tglocation;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import modele.Session;
import modele.Voiture;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class
ListVoiture extends AppCompatActivity {

    private ListView lvoiture;
    ArrayAdapter<Voiture> arrayAdapter; //Renvoie une vue pour chaque object dans la collection fourni


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_voiture);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Init();
        Load();
        SetEvent();
    }

    //inflate c'est la methode pour initialiser les valeur
    void Init() {
        lvoiture = findViewById(R.id.lvvoiture);
    }

    public void onClick(View view){
        Intent intent=new Intent(ListVoiture.this, AddVoiture.class);
        startActivity(intent);
        finish();
    }
    //Methode d'execution des action poser par les utilisateur sur la List
    void SetEvent(){
        //action de modifier un patient
        lvoiture.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Voiture obj=new Voiture();
                obj=arrayAdapter.getItem(i);
                Session.setCurrentVoiture(obj);
                //Log.v("obj",new Gson().toJson(obj));
                startActivity(new Intent(ListVoiture.this, AddVoiture.class));
                Toast.makeText(ListVoiture.this," cliquer sur un Item",Toast.LENGTH_SHORT).show();//Toast fourni des commentaire Simple sur un operation dans une ptit fenetre
            }
        });

        //Action de supprimer un patient avec un log clique
        lvoiture.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int i, long id) {
                int pos=i;
                Toast.makeText(ListVoiture.this,"Long Clique sur un Item",Toast.LENGTH_SHORT).show();
                new AlertDialog.Builder(ListVoiture.this)
                        .setTitle("Comfirmation")
                        .setMessage("Voulez-vous vraiment supprimer")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Voiture obj=new Voiture();
                                obj=arrayAdapter.getItem(pos);
                                Voiture.delete(ListVoiture.this,obj.getId());
                                startActivity(new Intent(ListVoiture.this, ListVoiture.class));
                                finish();
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setCancelable(false)//setCancelable: utiliser pour empecher l'utilisateur de clicquer en dehors du dialogue
                        .show();
                return true;
            }
        });
    }

    //Methode d'affichage des Donnees
    void Load(){
        List<Voiture> Liste=new ArrayList<>();
        Liste=Voiture.selectall(ListVoiture.this); //ListVoiture.this est un context utiliser pour pour load dans la fenetre actuel

        arrayAdapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1 , Liste);
        //Log.v("listepersonne",new Gson().toJson(Liste));
        lvoiture.setAdapter(arrayAdapter);
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

                startActivity(new Intent(ListVoiture.this,AddVoiture.class));
                return true;
            case R.id.vider:
                //lvpatient.setAdapter(null);
                Voiture obj= new Voiture();
                Voiture.deleteAll(ListVoiture.this,obj.getId());
                startActivity(new Intent(ListVoiture.this,ListVoiture.class));
                finish();
                return true;
            case R.id.loggout:
                //pour le button log out
                startActivity(new Intent(ListVoiture.this,Login.class));
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}