package com.example.cugclassschedule;

import android.icu.util.Calendar;

public class Note implements Comparable<Note> {
    private String title;
    private String content;
    private Calendar date;

    public Note(String title, String content, Calendar date) {
        this.title = title;
        this.content = content;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    @Override
    public int compareTo(Note other) {
        return this.date.compareTo(other.date);
    }


    public static Note fromString(String noteStr) {
        String[] parts = noteStr.split("\\|");
        String title = parts[0];
        String content = parts[1];
        Calendar date = Calendar.getInstance();
      //  date.setTimeInMillis(Long.parseLong(parts[2]));
        return new Note(title, content, date);
    }

    @Override
    public String toString() {
        return title + "|" + content + "|" + date.getTimeInMillis();
    }
}

