package com.example.mvvmarchietecture;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

// @Database(entities = {Note.class}) if there are more than one database entity we can use an array with {} and add with commas


@Database(entities = Note.class, version = 1)
public abstract class NoteDatabase extends RoomDatabase {
    private static NoteDatabase instance;

    public abstract RoomDAO noteDAO();

    public static synchronized NoteDatabase getInstance(Context context) {

        if (instance == null) {

            instance = Room.databaseBuilder(context.getApplicationContext(),
                    NoteDatabase.class, "note_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallBack)
                    .build();
        }

        return instance;

    }

    public static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
//            new PopulateDbAsynTask(instance).execute();
        }
    };

    // This below is checked to use if we data is adding to our entity

//    private static class PopulateDbAsynTask extends AsyncTask<Void, Void, Void> {
//
//        private RoomDAO noteDao;
//        private PopulateDbAsynTask(NoteDatabase db) {
//            noteDao = db.noteDAO();
//        }
//
//        @Override
//        protected Void doInBackground(Void... voids) {
//            noteDao.insert(new Note("Title 1", "Description 1", 1));
//            noteDao.insert(new Note("Title 2", "Description 2", 2));
//            noteDao.insert(new Note("Title 3", "Description 3", 3));
//            return null;
//        }
//    }

}
