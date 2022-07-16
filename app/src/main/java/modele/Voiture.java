package modele;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.Benginio.tglocation.Database;

import java.util.ArrayList;
import java.util.List;

public class Voiture {
    private  long id;
    private  String marque;
    private String annee;
    private String modele;
    private String couleur;
    private String num_immatri;
    private String im;

    public Voiture(long id, String marque, String annee, String modele, String couleur, String num_immatri){
        this.id=id;
        this.marque=marque;
        this.annee=annee;
        this.modele=modele;
        this.couleur=couleur;
        this.num_immatri=num_immatri;
    }

    public Voiture(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getAnnee() {
        return annee;
    }

    public void setAnnee(String annee) {
        this.annee = annee;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public String getNum_immatri() {
        return num_immatri;
    }

    public void setNum_immatri(String num_immatri) {
        this.num_immatri = num_immatri;
    }

    //Methode qui contient le script pour la creation de la table voiture
    public static String getScript() {
        String script ="CREATE TABLE Voiture(id INTEGER PRIMARY KEY AUTOINCREMENT,";
        script +="marque TEXT,";
        script+="annee TEXT,";
        script+="modele TEXT,";
        script+="couleur TEXT,";
        script+="num_immatri TEXT);";
        return script;
    }

    public static long Insert(Context context,Voiture obj){
        Database db=new Database(context);
        db.open();
        ContentValues valeur=new ContentValues();
        valeur.put("marque",obj.getMarque());
        valeur.put("annee",obj.getAnnee());
        valeur.put("modele",obj.getModele());
        valeur.put("couleur",obj.getCouleur());
        valeur.put("num_immatri",obj.getNum_immatri());
        db.getDB().insert("Voiture",null,valeur);
        long l=0;

        Cursor cursor=db.getDB().rawQuery("SELECT MAX(id) FROM Voiture",null);
        if (cursor.moveToNext()){
            l=cursor.getLong(0);
        }
        cursor.close();
        db.close();
        return l;


    }

    //Methode pour faire une mise a jour sur la table Personne
    public static void update(Context context,Voiture obj){

        Database database = new Database(context);
        database.open();
        ContentValues values=new ContentValues();

        values.put("marque",obj.getMarque());
        values.put("annee",obj.getAnnee());
        values.put("modele",obj.getModele());
        values.put("couleur",obj.getCouleur());
        values.put("num_immatri",obj.getNum_immatri());
        database.getDB().update("Voiture",values,"id=?",new String []{String.valueOf(obj.getId())});
        database.close();

    }

    //Methode pour supprimer un donnee precis dans la table Personne quant on clique sur vider dans le menu
    public static void delete(Context context, long id){
        Database db= new Database(context);
        db.open();
        db.getDB().delete("Voiture","id=?",new String []{String.valueOf(id)});
    }

    //Methode pour supprimer les donnees de la table Personne
    public static void deleteAll(Context context, long id){
        Database db= new Database(context);
        db.open();
        db.getDB().delete("Voiture",null,null);
    }

    //Methode pour mettre dans une liste tout les donnees de la table Personne
    public static List<Voiture> selectall(Context context){
        Database db= new Database(context);
        db.open();
        String query="select ";
        query +="id, marque, annee, modele, couleur, num_immatri";
        query +=" from Voiture";
        Cursor cursor=db.getDB().rawQuery(query,null);

        List<Voiture>list=new ArrayList<>();

        while (cursor.moveToNext()){
            Voiture obj =new Voiture();
            obj.setId(cursor.getLong(0));
            obj.setMarque(cursor.getString(1));
            obj.setAnnee(cursor.getString(2));
            obj.setModele(cursor.getString(3));
            obj.setCouleur(cursor.getString(4));
            obj.setNum_immatri(cursor.getString(5));
            list.add(obj);

        }
        cursor.close();
        db.close();

        return list;
    }

    //ToString c'est une Methode qui va renvoye les valeur qui lui est donnee sur forme d'object chaine
    public String toString(){//la il est utilise en tant que fonction de classe
        return this.id+" |   "+this.marque +" "+this.annee+"  "+this.modele+"  "+this.couleur+"  "+this.num_immatri;
    }
}
