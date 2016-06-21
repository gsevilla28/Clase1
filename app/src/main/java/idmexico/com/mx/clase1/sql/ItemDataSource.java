package idmexico.com.mx.clase1.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.method.HideReturnsTransformationMethod;
import android.view.Display;

import java.util.ArrayList;
import java.util.List;

import Model.ModelItem;
import Model.ModelUSer;

/**
 * Created by Alumno on 17/06/2016.
 */
public class ItemDataSource {

    private  final SQLiteDatabase db;
    private ContentValues cv =new ContentValues();

    public ItemDataSource(Context context) {
        MySqliteHelper helper= new MySqliteHelper(context);
        db= helper.getWritableDatabase();

    }


    /*metodo para guardar datos en BD local*/
    public void saveItem(ModelItem modelItem){

        cv.put(MySqliteHelper.COLUMN_ITEM_NAME, modelItem.item);
        cv.put(MySqliteHelper.COLUMN_ITEM_DESC, modelItem.description);
        cv.put(MySqliteHelper.COLUMN_ITEM_RESOURCE,modelItem.resourceID);

        db.insert(MySqliteHelper.TABLE_NAME,null,cv); /*aqui se realiza el insert*/
        cv.clear();
    }

    /**METODO PARA INSERTAR USUARIOS EN BD LOCAL*/
    public void SaveUser(ModelUSer user){
        cv.put(MySqliteHelper.COLUMN_USER,user.userName);
        cv.put(MySqliteHelper.COLUMN_PWD,user.password);
        cv.put(MySqliteHelper.COLUMN_USER_NAME,user.Nombre);

        db.insert(MySqliteHelper.TABLE_NAME_USERS,null,cv);
        cv.clear();
    }


    public void deleteItem (ModelItem modelItem){

        int elimanados =db.delete(MySqliteHelper.TABLE_NAME,MySqliteHelper.COLUMN_ID + "=?",
                new String[]{String.valueOf(modelItem.id)});

    }



    public List<ModelItem> getAllItems(){

        List<ModelItem> modelItemLis = new ArrayList<>();
        Cursor cur = db.query(MySqliteHelper.TABLE_NAME,null,null,null,null,null,null,null);

        while (cur.moveToNext()){

            int id = cur.getInt(cur.getColumnIndexOrThrow(MySqliteHelper.COLUMN_ID));
            String ItemName = cur.getString(cur.getColumnIndexOrThrow(MySqliteHelper.COLUMN_ITEM_NAME));
            String description = cur.getString(cur.getColumnIndexOrThrow(MySqliteHelper.COLUMN_ITEM_DESC));
            int resourced = cur.getInt(cur.getColumnIndexOrThrow(MySqliteHelper.COLUMN_ITEM_RESOURCE));

            ModelItem modelItem = new ModelItem();
            modelItem.id=id;
            modelItem.description=description;
            modelItem.item=ItemName;
            modelItem.resourceID=resourced;

            modelItemLis.add(modelItem);

        }
        return modelItemLis;
    }

    /*obtener el usuario de acuerdo al id que se almacenó en shared preferences*/
    public ModelUSer getUser(int id){

        //String[] args = new String[]{MySqliteHelper.COLUMN_ID_USER,MySqliteHelper.COLUMN_USER,MySqliteHelper.COLUMN_PWD,MySqliteHelper.COLUMN_USER_NAME};

        Cursor cur = db.query(MySqliteHelper.TABLE_NAME_USERS,null,MySqliteHelper.COLUMN_ID_USER + " =?",null,null,null,null,null);

        if (cur.moveToNext()){
            return new ModelUSer(cur.getString(cur.getColumnIndexOrThrow(MySqliteHelper.COLUMN_USER)),cur.getString(cur.getColumnIndexOrThrow(MySqliteHelper.COLUMN_PWD)),cur.getString(cur.getColumnIndexOrThrow(MySqliteHelper.COLUMN_USER_NAME)));
        }
        else{
            return new ModelUSer("","","");
        }
    }

}
