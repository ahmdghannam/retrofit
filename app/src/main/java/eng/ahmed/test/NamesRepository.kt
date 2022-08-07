package eng.ahmed.test

import androidx.annotation.WorkerThread
import eng.ahmed.test.model.Name

class NamesRepository(private val namesDao: NamesDao) {

//    val allNames: Flow<List<Name>> = namesDao.get()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(name: Name) {
        namesDao.insert(name)
    }

    suspend fun getNames():List<Name> {
        return namesDao.get()
    }
}
