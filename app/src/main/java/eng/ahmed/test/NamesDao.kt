package eng.ahmed.test

import androidx.room.*
import eng.ahmed.test.model.Name

@Dao
interface NamesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(name: Name)

    @Query("select * from names_table order by name asc")
     suspend fun get():List<Name>



}