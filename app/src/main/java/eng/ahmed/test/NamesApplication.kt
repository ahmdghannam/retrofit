package eng.ahmed.test

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

// to access its members from all over the app
class NamesApplication : Application() {

    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy {
        NamesDatabase.getDatabase(this,applicationScope)
    }

    val repository by lazy {
        NamesRepository(database.namesDao())
    }
}