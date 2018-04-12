package dev.japango.samplesqlite;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.WindowManager;

import java.io.IOException;
import java.util.ArrayList;

import dev.japango.samplesqlite.Controllers.AdapterRecycleView;
import dev.japango.samplesqlite.Models.SQLiteDBContext;
import dev.japango.samplesqlite.Models.Word;
import dev.japango.samplesqlite.Models.tblWord;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class MainActivity extends AppCompatActivity {
    private RecyclerView rc_view;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Word> dataset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        createDB();
        getAllWord();
        rc_view = findViewById(R.id.rc_view);
        rc_view.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        rc_view.setLayoutManager(layoutManager);

        adapter = new AdapterRecycleView(dataset);
        rc_view.setAdapter(adapter);
    }

    private void createDB() {
        SQLiteDBContext sql = new SQLiteDBContext(this);
        try {
            sql.isCreateDatabase();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getAllWord() {
        tblWord w = new tblWord(getApplicationContext());
        dataset = new ArrayList<Word>();
        dataset = w.getAllWord();
    }
}
