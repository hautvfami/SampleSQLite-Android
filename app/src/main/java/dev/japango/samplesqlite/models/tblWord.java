package dev.japango.samplesqlite.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.util.Log;

import java.util.ArrayList;

public class tblWord extends SQLiteDBContext {

    public tblWord(Context context) {
        super(context);
    }

    public ArrayList<Word> getAllWord() {
        ArrayList<Word> listWord = new ArrayList<>();
        try {
            openDataBase();
            Cursor cs = database.rawQuery("select * from EN_VI", null);
            Word word;
            while (cs.moveToNext()) {
                word = new Word(cs.getInt(0), cs.getString(1), cs.getString(2), cs.getString(3), cs.getBlob(4));
                Log.i("info", word.getMean());
                listWord.add(word);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }

        return listWord;
    }

    public Word getWordById(int id) {
        Word word = new Word();
        openDataBase();
        Cursor cs = database.rawQuery("select * from EN_VI where id=" + id, null);
        cs.moveToFirst();
        word.setId(cs.getInt(0));
        word.setWord(cs.getString(1));
        word.setMean(cs.getString(2));
        word.setDescriptions(cs.getString(3));
        word.setImage(cs.getBlob(4));
        return word;
    }

    public boolean insertWord(Word word) {
        boolean result = false;
        try {
            openDataBase();
            ContentValues values = new ContentValues();
            values.put("Id", "");
            values.put("Word", word.getWord());
            values.put("Mean", word.getMean());
            values.put("Descriptions", word.getDescriptions());
            values.put("Image", word.getImage());
            long rs = database.insert("EN_VI", null, values);
            if (rs > 0) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return result;
    }

    public boolean updateWord(Word word) {
        boolean result = false;
        try {
            openDataBase();
            ContentValues values = new ContentValues();
            values.put("Id", word.getId());
            values.put("Word", word.getWord());
            values.put("Mean", word.getMean());
            values.put("Descriptions", word.getDescriptions());
            values.put("Image", word.getImage());
            int rs = database.update("EN_VI", values, "id=" + word.getId(), null);
            if (rs > 0) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return result;
    }

    public boolean deleteWord(int id) {
        boolean result = false;
        try {
            openDataBase();
            int rs = database.delete("EN_VI", "id=" + id, null);
            if (rs > 0) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return result;
    }
}
