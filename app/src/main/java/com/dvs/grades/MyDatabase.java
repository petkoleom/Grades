package com.dvs.grades;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.*;


@Database(entities = {Subject.class}, version = 1)
public abstract class MyDatabase extends RoomDatabase {
    public abstract SubjectDAO subjectDAO();

    private static volatile MyDatabase Instance;

    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static MyDatabase getDatabase(final Context context) {
        if (Instance == null) {
            synchronized (MyDatabase.class) {
                if (Instance == null) {
                    Instance = Room.databaseBuilder(context.getApplicationContext(),
                                    MyDatabase.class, "word_database")
                            .build();
                }
            }
        }
        return Instance;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            // If you want to keep data through app restarts,
            // comment out the following block
            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.
                SubjectDAO dao = Instance.subjectDAO();
                dao.deleteAll();

                Subject subject = new Subject("Maths");
                dao.insert(subject);
                subject = new Subject("English");
                dao.insert(subject);
            });
        }
    };


}
