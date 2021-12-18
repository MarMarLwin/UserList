package com.mml.userapp.data_access

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.mml.userapp.models.Address
import com.mml.userapp.models.Geo
import com.mml.userapp.models.User

@Database(entities = [User::class],version=1)

abstract class DatabaseAccess : RoomDatabase() {

    abstract fun userDao():UserDao
    companion object {
        var INSTANCE: DatabaseAccess?=null
        @TypeConverter
        fun getAppDatabase(context: Context): DatabaseAccess?{
            if (INSTANCE == null) {
                synchronized(DatabaseAccess::class.java) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext, DatabaseAccess::class.java,
                        context.applicationInfo.dataDir+"//databases//userinfo.db")
                        //.allowMainThreadQueries().fallbackToDestructiveMigration().build()
                        .allowMainThreadQueries().addMigrations().build()
                }
            }
            return INSTANCE
        }
    }
    object DataTypeConverter {
        private val gson = Gson()
        @TypeConverter
        fun stringToAddress(data: String?): Address {
            if (data == null) {
                return Address("","","","", Geo("",""))
            }

            return gson.fromJson<Address>(data, Address::class.java)
        }

        @TypeConverter
        fun AddressToString(someObjects: Address?): String {
            return gson.toJson(someObjects)
        }
    }
}