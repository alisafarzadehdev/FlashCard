package ir.safarzadehali.myapp.retro


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ir.safarzadehali.myapp.model.FlashCardDao
import ir.safarzadehali.myapp.model.FlashCardEntity
import ir.safarzadehali.myapp.model.WordEntity

@Database(
    entities = [FlashCardEntity::class, WordEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun flashCardDao(): FlashCardDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "flashcard_db"
                ).fallbackToDestructiveMigration().build()

                INSTANCE = instance
                instance
            }
        }
    }
}
