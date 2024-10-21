package com.anshuman.githubrepofinderapp.LocalDB



//@Database(entities = [RepoSearchResultItem::class], version = 1, exportSchema = false)
//abstract class AppDatabase : RoomDatabase() {
//
//    abstract fun repoDAO(): RepoSearchResultDao // DAO interface for accessing the repository data
//
//    companion object {
//        @Volatile
//        private var INSTANCE: AppDatabase? = null
//
//        // Singleton method to get the database instance
//        fun getDatabase(context: Context): AppDatabase {
//            return INSTANCE ?: synchronized(this) {
//                // Building the Room database
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    AppDatabase::class.java,
//                    "repo_database"  // Name of your SQLite database file
//                )
//                    .fallbackToDestructiveMigration()  // Handles schema migration by resetting the DB
//                    .build()
//                INSTANCE = instance
//                instance
//            }
//        }
//    }
//}
