package dev.japango.samplesqlite.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DataAccess extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_EN_VI = "EN_VI";
    private static final String COLUMN_EN_VI_ID = "Id";
    private static final String COLUMN_EN_VI_WORD = "Word";
    private static final String COLUMN_EN_VI_MEAN = "Mean";
    private static final String COLUMN_EN_VI_DESCRIPTIONS = "Descriptions";
    private static final String COLUMN_EN_VI_IMAGE = "Image";

    private static String DATABASE_NAME = "SQLite.db";
    private static String DATABASE_PATH;

    private static String CREATE_EN_VI_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_EN_VI + "(" +
            COLUMN_EN_VI_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_EN_VI_WORD + " TEXT NOT NULL," +
            COLUMN_EN_VI_MEAN + " TEXT," +
            COLUMN_EN_VI_DESCRIPTIONS + " TEXT," +
            COLUMN_EN_VI_IMAGE + " BLOB);";

    private static DataAccess mInstance;
    private SQLiteDatabase mDb;
    private Context mContext;

    private DataAccess(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.mContext = context;
        mDb = getWritableDatabase();
    }

    // Tao the hien cho DB Context
    public static DataAccess getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new DataAccess(context.getApplicationContext());
        }
        return mInstance;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        createTables(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EN_VI);
        createTables(db);
    }

    private void createTables(SQLiteDatabase db) {
        db.execSQL(CREATE_EN_VI_TABLE);
    }

    private void cloneDBFromAsset(SQLiteDatabase db) throws IOException {
        String path = this.mContext.getFilesDir().getPath() + "/" + DATABASE_NAME;

        InputStream input = mContext.getAssets().open(DATABASE_NAME);
        OutputStream output = new FileOutputStream(path);

        byte[] buffer = new byte[1024];
        int length;
        while ((length = input.read(buffer)) > 0) {
            output.write(buffer, 0, length);
        }
        output.flush();
        output.close();
        input.close();
    }
}
