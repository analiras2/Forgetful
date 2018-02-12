package br.com.ascal.forgetful

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import br.com.ascal.forgetful.data.AppDatabase

class ForgetfulApplication : Application() {

    private var instance: ForgetfulApplication? = null
    private lateinit var db: AppDatabase

    fun getInstance() = instance

    fun getDatabase() = db


    private fun setInstance(instance: ForgetfulApplication) {
        this.instance = instance
    }

    override fun onCreate() {
        super.onCreate()
        setInstance(this)
        setAppDatabase(this)
    }

    private fun setAppDatabase(context: Context) {
        db = Room.databaseBuilder(context, AppDatabase::class.java, "forgetful-db").build()
    }

}