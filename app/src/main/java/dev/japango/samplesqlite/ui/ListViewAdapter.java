package dev.japango.samplesqlite.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import dev.japango.samplesqlite.R;
import dev.japango.samplesqlite.models.Word;

public class ListViewAdapter extends ArrayAdapter<Word> {

    private ArrayList<Word> mData = new ArrayList<Word>();
    private LayoutInflater mInflater;

    public ListViewAdapter(Context context, int resource, ArrayList<Word> dataset) {
        super(context, resource, dataset);
        mInflater = LayoutInflater.from(getContext());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            view = layoutInflater.inflate(R.layout.item_rc, null);
        }

        Word word = getItem(position);

        if (word != null) {
            TextView tv_word = view.findViewById(R.id.word);

            if (tv_word != null) {
                tv_word.setText(word.getWord());
            }
        }
        return view;
    }

}

class ViewHolder {
    public TextView textView;
}
