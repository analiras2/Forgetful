package br.com.ascal.forgetful.data.dao

import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import br.com.ascal.forgetful.data.entity.Item
import io.reactivex.Flowable
import io.reactivex.Single


@Dao
interface ItemDao {

    @Query("SELECT * FROM item")
    fun getAll(): Flowable<List<Item>>

    @Query("SELECT * FROM item WHERE uid = :arg0 ")
    fun findById(id: Long): Single<Item>

    @Insert(onConflict = REPLACE)
    fun insertAll(vararg items: Item)

    @Update(onConflict = REPLACE)
    fun update(item: Item)

    @Delete
    fun delete(item: Item)
}