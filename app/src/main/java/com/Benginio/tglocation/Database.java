package com.Benginio.tglocation;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class Database{

    private com.Benginio.tglocation.Databasehandler databasehandler;
    private SQLiteDatabase DB;

    public Database(Context context){
        databasehandler=new com.Benginio.tglocation.Databasehandler
                (context, "DB_Voiture", null, 1);

    }

    public void open(){
        DB=databasehandler.getWritableDatabase();
    }
    public void close(){
        DB.close();
    }

    public SQLiteDatabase getDB() {
        return DB;
    }

}
