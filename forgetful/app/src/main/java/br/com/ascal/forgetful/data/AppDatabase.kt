package br.com.ascal.forgetful.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import br.com.ascal.forgetful.data.dao.ItemDao
import br.com.ascal.forgetful.data.entity.Item


@Database(entities = arrayOf(Item::class), version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun itemDao(): ItemDao
}