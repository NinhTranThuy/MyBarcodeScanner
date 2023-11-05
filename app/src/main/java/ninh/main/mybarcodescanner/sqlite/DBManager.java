package ninh.main.mybarcodescanner.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class DBManager {

    private DatabaseHelper dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public DBManager(Context c) {

        context = c;
    }

    public DBManager open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {

        dbHelper.close();
    }

    public void insert( Long seri , String name, Integer quantity) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.Seri, seri);
        contentValue.put(DatabaseHelper.NameProduct, name);
        contentValue.put(DatabaseHelper.Quantity, quantity);
        database.insert(DatabaseHelper.TABLE_NAME, null, contentValue);
    }

    public Cursor fetch() {
        String[] columns = new String[] { DatabaseHelper.Seri, DatabaseHelper.NameProduct, DatabaseHelper.Quantity };
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int update(Long _seri, String name, Integer quantity) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.NameProduct, name);
        contentValues.put(DatabaseHelper.Quantity, quantity);
        int i = database.update(DatabaseHelper.TABLE_NAME, contentValues, DatabaseHelper.Seri + " = " + _seri, null);
        return i;
    }

    public void delete(Long _seri) {
        database.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper.Seri + "=" + _seri, null);
    }

    public boolean checkExisted(Long seri){
        Cursor cursor = fetch();
        long seriProduct = cursor.getInt(0);
        if (seriProduct == seri){
            return true;
        }
        return false;
    }

}