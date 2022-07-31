package eng.ahmed.test

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "names_table")

data class Name (@PrimaryKey val name:String)