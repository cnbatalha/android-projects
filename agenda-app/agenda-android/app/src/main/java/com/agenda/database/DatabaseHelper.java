package com.agenda.database;

/**
 * Created by Carlos on 27/08/2014.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.agenda.model.Contact;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database
    // Table Name
    public final static String TABLE_NAME = "contact";

    /* Contact Data */
    public final static String CONTACT_NAME = "name";
    public final static String _ID = "_id";
    public final static String[] columns = {_ID, CONTACT_NAME};

    final private static String CREATE_CMD =
            "CREATE TABLE " + Contact.TABLE_NAME + " (" + Contact.FLD_ID
                    + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + Contact.FLD_NAME + " TEXT NOT NULL,"
                    + Contact.FLD_PHONE + " TEXT ,"
                    + Contact.FLD_EMAIL + " TEXT ,"
                    + Contact.FLD_URL_PHOTO + " TEXT "
                    + ")";

    final private static String DB_NAME = "agenda_db";
    final private static Integer VERSION = 1;
    private Context mContext = null;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
        //context.deleteDatabase(DB_NAME);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_CMD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {

    }

    void deleteDatabase() {
        mContext.deleteDatabase(DB_NAME);
    }
}
