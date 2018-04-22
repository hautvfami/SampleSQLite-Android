package dev.japango.samplesqlite.models;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class SQLiteDBContext extends SQLiteOpenHelper {
    // path store SQLite DB
    private static String DB_PATH;
    private static String DB_NAME = "SQLite.db";

    public SQLiteDatabase database;
    private Context mContext = null;

    public SQLiteDBContext(Context context) {
        super(context, DB_NAME, null, 1);
        DB_PATH = context.getFilesDir().getPath();
        DB_PATH = String.format(DB_PATH, context.getPackageName());
        this.mContext = context;
    }

    // Nếu DB chưa tồn tại thì copy DB vào
    public boolean isCreateDatabase() {
        boolean result = true;
        if (!checkExistDatabase()) {
            this.getReadableDatabase();
            try {
                copyDatabase();
                result = false;
            } catch (Exception e) {
                throw new Error(e);
            }
        }
        return result;
    }


    // Kiểm tra DB đã tồn tại chưa
    private boolean checkExistDatabase() {
        try {
            String path = DB_PATH + DB_NAME;
            File fileDB = new File(path);
            return fileDB.exists();
        } catch (Exception e) {
            throw new Error("Can't checkExistDatabase " + e);
        }
    }

    private void copyDatabase() throws IOException {
        InputStream input = mContext.getAssets().open(DB_NAME);
        OutputStream output = new FileOutputStream(DB_PATH + DB_NAME);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = input.read(buffer)) > 0) {
            output.write(buffer, 0, length);
        }
        output.flush();
        output.close();
        input.close();
    }

    /**
     * delete database file
     *
     * @return
     */
    public boolean deleteDatabase() {
        File file = new File(DB_PATH + DB_NAME);
        if (file != null && file.exists()) {
            return file.delete();
        }
        return false;
    }

    /**
     * open database
     *
     * @throws SQLException
     */
    public void openDataBase() throws SQLException {
        database = SQLiteDatabase.openDatabase(DB_PATH + DB_NAME, null,
                SQLiteDatabase.OPEN_READWRITE);
    }

    @Override
    public synchronized void close() {
        if (database != null)
            database.close();
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        deleteDatabase();
        try {
            copyDatabase();
        } catch (Exception error) {
            throw new Error("Error in upgrade DB");
        }
    }

    public int deleteDataFromTable(String tbName) {

        int result = 0;
        try {
            openDataBase();
            database.beginTransaction();
            result = database.delete(tbName, null, null);
            if (result >= 0) {
                database.setTransactionSuccessful();
            }
        } catch (Exception e) {
            database.endTransaction();
            close();
        } finally {
            database.endTransaction();
            close();
        }

        return result;
    }
}
