package com.ntt.androidjetpack.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.ntt.androidjetpack.model.Task
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Task::class], version = 1, exportSchema = false)
abstract class TaskDatabase : RoomDatabase() {

    abstract fun getTaskDao(): TaskDao

    companion object {
        private var INSTANCE: TaskDatabase? = null

        fun getInstance(ctx: Context, scope: CoroutineScope): TaskDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    ctx,
                    TaskDatabase::class.java,
                    "task.db"
                ).addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        scope.launch(Dispatchers.IO) {
                            for (i in 0..1000) {
                                INSTANCE!!.getTaskDao().insertTask(
                                    Task(i, "Task $i", "Description $i", i)
                                )
                            }
                        }
                    }
                })
                    .build()
            }
            return INSTANCE!!
        }
    }
}