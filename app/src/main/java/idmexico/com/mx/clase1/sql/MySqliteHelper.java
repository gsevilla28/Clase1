package idmexico.com.mx.clase1.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.provider.SyncStateContract;

/**
 * Created by Alumno on 17/06/2016.
 */
public class MySqliteHelper  extends SQLiteOpenHelper{

    private final static String DATABASE_NAME="unamsqlite3";
    private final static int DATABASE_VERSION=1;

    public static final String TABLE_NAME="item_table";
    public static final String COLUMN_ID= BaseColumns._ID;
    public static final String COLUMN_ITEM_NAME="name";
    public static final String COLUMN_ITEM_DESC="description";
    public static  final String COLUMN_ITEM_RESOURCE="resouce_id";


    private static final String CREATE_TABLE=" create table " + TABLE_NAME +
            " ( " + COLUMN_ID + " integer primary key autoincrement, " +
            COLUMN_ITEM_NAME + " text not null, " +
            COLUMN_ITEM_DESC + " text not null, "+
            COLUMN_ITEM_RESOURCE + " integer not null)";


    /*CREANDO TABLA DE USUARIOS*/
    public static final String TABLE_NAME_USERS="USERS_TABLE";
    public static final String COLUMN_ID_USER=BaseColumns._ID;
    public static final String COLUMN_USER="USER";
    public static final String COLUMN_PWD="PWD";
    public static final String COLUMN_USER_NAME="USER_NAME";

    private static final String CREATE_TABLE_USERS=" CREATE TABLE " + TABLE_NAME_USERS +
            " ( " + COLUMN_ID_USER + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_USER + " TEXT NOT NULL, " +
            COLUMN_PWD + " TEXT NOT NULL, " +
            COLUMN_USER_NAME + " TEXT NOT NULL)";

    public MySqliteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        db.execSQL(CREATE_TABLE_USERS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
