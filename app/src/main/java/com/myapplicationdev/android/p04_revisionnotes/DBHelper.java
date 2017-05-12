package com.myapplicationdev.android.p04_revisionnotes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

	//TODO Define the Database properties
	private static final String DATABASE_NAME = "note";
	private static final int DATABASE_VERSION = 1;
	private static final String TABLE_NAME = "note";
	private static final String COLUMN_ID = "_id";
	private static final String COLUMN_NOTE_CONTENT = "note_content";
	private static final String COLUMN_STAR = "stars";


	public DBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		
        String createTableSql = "CREATE TABLE " + TABLE_NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NOTE_CONTENT + " TEXT,"
                + COLUMN_STAR + "INTEGER )";
        db.execSQL(createTableSql);
        Log.i("info", "created tables");

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		onCreate(db);
	}

	public void insertNote(String noteContent, int stars) {
		//TODO insert the data into the database
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOTE_CONTENT, noteContent);
        values.put(COLUMN_STAR, stars);
        db.insert(TABLE_NAME, null, values);
        db.close();
	}

	public ArrayList<Note> getAllNotes() {
		//TODO return records in Java objects
        ArrayList<Note> notes = new ArrayList<Note>();
        String selectQuery = "SELECT " +  COLUMN_ID + " ,"
                + COLUMN_NOTE_CONTENT + ","
                + COLUMN_STAR
                + " FROM " + TABLE_NAME;

        // Get the instance of database to read
        SQLiteDatabase db = this.getReadableDatabase();
        // Run the SQL query and get back the Cursor object
        Cursor cursor = db.rawQuery(selectQuery, null);

        // moveToFirst() moves to first row
        if (cursor.moveToFirst()) {
            // Loop while moveToNext() points to next row
            //  and returns true; moveToNext() returns false
            //  when no more next row to move to
            do {
                int id = cursor.getInt(0);
                String noteContent = cursor.getString(1);
                int star = cursor.getInt(2);
                Note obj = new Note(id, noteContent, star);
                notes.add(obj);
            } while (cursor.moveToNext());
        }
        // Close connection
        cursor.close();
        db.close();
        return notes;
	}

    public ArrayList<String> getNoteContent() {
        //TODO return records in Strings

		// Create an ArrayList that holds String objects
        ArrayList<String> notes = new ArrayList<String>();
        // Select all the notes' content
        String selectQuery = "SELECT " + COLUMN_NOTE_CONTENT + " FROM " + TABLE_NAME;

        // Get the instance of database to read
        SQLiteDatabase db = this.getReadableDatabase();
        // Run the SQL query and get back the Cursor object
        Cursor cursor = db.rawQuery(selectQuery, null);
        // moveToFirst() moves to first row
        if (cursor.moveToFirst()) {
            // Loop while moveToNext() points to next row and returns true;
            // moveToNext() returns false when no more next row to move to
            do {


            } while (cursor.moveToNext());
        }
        // Close connection
        cursor.close();
        db.close();

        return notes;
    }
}
