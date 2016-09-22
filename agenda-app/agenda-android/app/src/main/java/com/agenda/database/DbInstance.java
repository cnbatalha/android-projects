package com.agenda.database;

import android.content.Context;

/**
 * Created by Carlos on 01/09/2014.
 */
public class DbInstance {

    static private DatabaseHelper dbHelper;

    private DbInstance() {

    }

    static public void InicializaDb(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    static public DatabaseHelper getDbInstance() {
        return dbHelper;
    }

}
