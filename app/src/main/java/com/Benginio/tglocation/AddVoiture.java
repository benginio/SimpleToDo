package com.Benginio.tglocation;

import androidx.appcompat.app.AppCompatActivity;
import modele.Session;
import modele.Voiture;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddVoiture extends AppCompatActivity {

    private EditText marque, annee, modele, couleur, num_immatri;
    private Button btnvalider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_voiture);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        marque=findViewById(R.id.editMarque);
        annee=findViewById(R.id.editAnnee);
        modele=findViewById(R.id.editModele);
        couleur=findViewById(R.id.editCouleur);
       num_immatri=findViewById(R.id.editNum_immatri);
        btnvalider=findViewById(R.id.btnvalider);
        LoadValues();
        btnvalider.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Voiture obj=new Voiture();
                if(Session.getCurrentVoiture()!=null){
                    obj=Session.getCurrentVoiture();
                }
                obj.setMarque(marque.getText().toString().toUpperCase());
                obj.setAnnee(annee.getText().toString());
                obj.setModele(modele.getText().toString());
                obj.setCouleur(couleur.getText().toString());
                obj.setNum_immatri(num_immatri.getText().toString());
                if(obj.getId()>0){
                   Voiture.update(AddVoiture.this,obj);
                    //Session.getListPersonne().set((int) obj.getId()-1,obj);
                    Toast.makeText(AddVoiture.this,"Mise ajout effectue avec succes",Toast.LENGTH_LONG).show();
                } else {
                    //Session.getListPersonne().add(obj);

                        Long id= Voiture.Insert(AddVoiture.this,obj);
                        if (id>0){
                            Toast.makeText(AddVoiture.this,"Insertion Reussie",Toast.LENGTH_LONG).show();

                        }
                    }


                startActivity(new Intent(AddVoiture.this,ListVoiture.class));
                finish();
                Session.setCurrentVoiture(null);
            }
        });

    }

    void LoadValues(){
        Voiture obj=new Voiture();
        if (Session.getCurrentVoiture()!=null){
            obj=Session.getCurrentVoiture();
           marque.setText(obj.getMarque());
            annee.setText(obj.getAnnee());
           modele.setText(obj.getModele());
            couleur.setText(obj.getCouleur());
            num_immatri.setText(obj.getNum_immatri());

            btnvalider.setText("Modifier");
        }
    }

}