package dev.japango.samplesqlite.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import dev.japango.samplesqlite.R;
import dev.japango.samplesqlite.models.Word;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {
    private ArrayList<Word> data;

    public RecyclerViewAdapter(ArrayList<Word> data_) {
        this.data = data_;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_rc, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        Word word = data.get(position);
        holder.word.setText(word.getWord());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView word;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            word = itemView.findViewById(R.id.word);
        }
    }
}
