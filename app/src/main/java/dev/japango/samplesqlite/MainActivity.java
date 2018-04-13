package dev.japango.samplesqlite;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import dev.japango.samplesqlite.Controllers.AdapterRecycleView;
import dev.japango.samplesqlite.Controllers.ListViewAdapter;
import dev.japango.samplesqlite.Models.SQLiteDBContext;
import dev.japango.samplesqlite.Models.Word;
import dev.japango.samplesqlite.Models.tblWord;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class MainActivity extends AppCompatActivity {
    //    private RecyclerView rc_view;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Word> dataset;
    private Intent intentWord;
    private SQLiteDBContext sql;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        createDB();
        getAllWord();
        _registerListView(this);
//        _registerRCView(this);
    }

    private void createDB() {
        sql = new SQLiteDBContext(this);
        try {
            sql.isCreateDatabase();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getAllWord() {
        tblWord w = new tblWord(getApplicationContext());
        dataset = new ArrayList<Word>();
        dataset = w.getAllWord();
    }

    public void _registerListView(final Context context) {
        ListView lv = findViewById(R.id.lv);
        ArrayAdapter<Word> arrayAdapter = new ListViewAdapter(context, R.layout.item_rc, dataset);
        lv.setAdapter(arrayAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intentWord = new Intent(MainActivity.this, WordActivity.class);
                intentWord.putExtra("id", dataset.get(position).getId());
                startActivity(intentWord);
            }
        });
    }

    public void handleUpgrade(View v) {
        sql.onUpgrade(null, 1, 2);
        getAllWord();
    }

//    public void _registerRCView(Context con) {
//        rc_view = findViewById(R.id.rc_view);
//        rc_view.setHasFixedSize(true);
//        layoutManager = new LinearLayoutManager(con);
//        rc_view.setLayoutManager(layoutManager);
//        adapter = new AdapterRecycleView(dataset);
//        rc_view.setAdapter(adapter);
//    }
}