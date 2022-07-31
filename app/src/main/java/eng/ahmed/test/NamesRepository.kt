package eng.ahmed.test

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class NamesRepository(private val namesDao:NamesDao) {

    val allNames : Flow<List<Name>> = namesDao.get()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(name: Name){
        namesDao.insert(name)
    }


}