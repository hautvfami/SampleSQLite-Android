package dev.japango.samplesqlite;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import dev.japango.samplesqlite.models.SQLiteDBContext;
import dev.japango.samplesqlite.models.Word;
import dev.japango.samplesqlite.models.tblWord;

public class WordActivity extends AppCompatActivity {
    public TextView tv_word, tv_mean, tv_descriptions;
    public ImageView img;
    protected int id;
    private Intent intentWord;
    private tblWord w;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_word);

        createDB();

        this._registerTextView();
        this._getIdFromIntent();
        this._showData();
    }

    private void createDB() {
        SQLiteDBContext sql = new SQLiteDBContext(this);
        try {
            sql.isCreateDatabase();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Word getWordById(Context context, int id_) {
        w = new tblWord(context);
        Word word = w.getWordById(id);
        return word;
    }

    private void _registerTextView() {
        tv_word = findViewById(R.id.tv_word);
        tv_mean = findViewById(R.id.tv_mean);
        tv_descriptions = findViewById(R.id.tv_descriptions);
        img = findViewById(R.id.img);
    }

    private void _getIdFromIntent() {
        intentWord = getIntent();
        id = intentWord.getIntExtra("id", 0);
        Log.i("Intent get Id: ", String.valueOf(id));
    }

    private void _showData() {
        Word word = this.getWordById(this, id);
        Log.i("======================", word.getWord() + word.getMean() + word.getDescriptions());
        tv_word.setText(word.getWord());
        tv_mean.setText(word.getMean());
        tv_descriptions.setText(word.getDescriptions());
        byte[] image = word.getImage();
        try {
            Bitmap bmp = BitmapFactory.decodeByteArray(image, 0, image.length);
            img.setImageBitmap(bmp);
        } catch (Exception e) {
            Log.e("Image: ", e.getMessage());
        }
    }

    public void handleDelete(View v) {
        w.deleteWord(id);
        super.finish();
    }
}
