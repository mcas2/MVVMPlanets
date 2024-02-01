package com.mcas2.roomcodelab;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.mcas2.roomcodelab.daos.PictureDao;
import com.mcas2.roomcodelab.daos.WordDao;
import com.mcas2.roomcodelab.entities.Word;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Word.class}, version = 1, exportSchema = false)
public abstract class MyRoomDatabase extends RoomDatabase {
    public abstract WordDao wordDao();
    public abstract PictureDao pictureDao();
    private static volatile MyRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static MyRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (MyRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            MyRoomDatabase.class,
                            "my_database"
                    ).fallbackToDestructiveMigration()
                    .addCallback(sRoomDatabaseCallback)
                    .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            databaseWriteExecutor.execute(() -> {
                WordDao wordDao = INSTANCE.wordDao();
                wordDao.deleteAll();
                Word nuevaPalabra = new Word("Hola");
                wordDao.insert(nuevaPalabra);
                nuevaPalabra = new Word("Adiós");
                wordDao.insert(nuevaPalabra);

                PictureDao pictureDao = INSTANCE.pictureDao();
                pictureDao.deleteAll();
            });
        }
    };


}
