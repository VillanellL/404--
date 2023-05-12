package com.example.cugclassschedule;

import android.content.Context;
import android.content.SharedPreferences;
import android.icu.text.DateFormat;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hhuclassschedule.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NoteAdapter extends ArrayAdapter<Note> {
    private int resourceId;
    private List<Note> originalNotes;
    private List<Note> filteredNotes;
    private SharedPreferences prefs;

    public NoteAdapter(@NonNull Context context, int textViewResourceId, @NonNull List<Note> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
        originalNotes = new ArrayList<>(objects);
        filteredNotes = new ArrayList<>(objects);
        prefs = context.getSharedPreferences("notes", Context.MODE_PRIVATE);
        loadNotes();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Note note = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
        TextView title = view.findViewById(R.id.note_title);
        TextView content = view.findViewById(R.id.note_content);
        TextView date = view.findViewById(R.id.note_date);
        title.setText(note.getTitle());
        content.setText(note.getContent());
        date.setText(DateFormat.getDateInstance(DateFormat.MEDIUM).format(note.getDate().getTime()));
        return view;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                filteredNotes.clear();
                if (constraint == null || constraint.length() == 0) {
                    filteredNotes.addAll(originalNotes);
                } else {
                    String filterPattern = constraint.toString().toLowerCase().trim();
                    for (Note note : originalNotes) {
                        if (note.getTitle().toLowerCase().contains(filterPattern) ||
                                note.getContent().toLowerCase().contains(filterPattern)) {
                            filteredNotes.add(note);
                        }
                    }
                }
                FilterResults results = new FilterResults();
                results.values = filteredNotes;
                return results;
            }

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                clear();
                addAll((List) results.values);
                notifyDataSetChanged();
            }
        };
    }

    public void addNote(Note note) {
        add(note);
        originalNotes.add(note);
        saveNotes();
    }

    public void removeNote(int position) {
        Note note = getItem(position);
        remove(note);
        originalNotes.remove(note);
        saveNotes();
    }
    public void removeNote_1(int position) {

        originalNotes.remove(position);
        saveNotes();
    }

    private void saveNotes() {
        SharedPreferences.Editor editor = prefs.edit();
        Set<String> set = new HashSet<>();
        for (Note note : originalNotes) {
            set.add(note.toString());
        }
        editor.putStringSet("notes", set);
        editor.apply();
    }

    private void loadNotes() {
        Set<String> set = prefs.getStringSet("notes", new HashSet<>());
        for (String s : set) {
            Note note = Note.fromString(s);
            add(note);
            originalNotes.add(note);
        }
    }
}

