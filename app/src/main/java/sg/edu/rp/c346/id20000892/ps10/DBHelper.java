package sg.edu.rp.c346.id20000892.ps10;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "song.db";
    private static final int DATABASE_VERSION = 2;
    private static final String table_song = "song";
    private static final String _id = "_id";
    private static final String title = "title";
    private static final String singers = "singers";
    private static final String year = "year";
    private static final String stars = "stars";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createNoteTableSql = "CREATE TABLE " + table_song + "("
                + _id + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + title + " TEXT,"
                + singers + " TEXT,"
                + year + " INTEGER,"
                + stars + " INTEGER )";

        db.execSQL(createNoteTableSql);
        Log.i("info", "created tables");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTE);
        db.execSQL("ALTER TABLE " + table_song + " ADD COLUMN  module_name TEXT ");
        //onCreate(db);
    }

    public void insertSong(String titles, String singerss, String years, String starss) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(title, titles);
        values.put(singers, singerss);
        values.put(year, years);
        values.put(stars, starss);
        db.insert(table_song, null, values);
        db.close();
    }

    public ArrayList<Song> getAllSongs() {
        ArrayList<Song> songs = new ArrayList<Song>();

        String selectQuery = "SELECT " + _id + "," + title + "," + singers + "," + year + ","
                + stars + " FROM " + table_song;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String singers = cursor.getString(2);
                int year = cursor.getInt(3);
                int stars = cursor.getInt(4);
                Song song = new Song(id, title, singers, year, stars);
                songs.add(song);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return songs;
    }

    public ArrayList<Song> getAllSongs5() {
        ArrayList<Song> songs = new ArrayList<Song>();

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {_id, title, singers, year, stars};
        String condition = stars + " Like ?";
        String[] args = {"%5%"};
        Cursor cursor = db.query(table_song, columns, condition, args,
                null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String singers = cursor.getString(2);
                int year = cursor.getInt(3);
                int stars = cursor.getInt(4);
                Song song = new Song(id, title, singers, year, stars);
                songs.add(song);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return songs;
    }

    public int updateSong(Song data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(title, data.getTitle());
        values.put(singers, data.getSingers());
        values.put(year, data.getYear());
        values.put(stars, data.getStar());
        String condition = _id + "= ?";
        String[] args = {String.valueOf(data.getId())};
        int result = db.update(table_song, values, condition, args);
        db.close();
        return result;
    }
}