package eng.ahmed.test.view.activities

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import eng.ahmed.test.*
import eng.ahmed.test.databinding.ActivityMainBinding
import eng.ahmed.test.model.Name
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter
    private lateinit var binding: ActivityMainBinding

    private val namesViewModel: NamesViewModel by viewModels {
        NamesViewModelFactory((application as NamesApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRv()
        lifecycleScope.launchWhenCreated {
            getResponse()
        }
    }

    private fun setupRv() {
        recyclerViewAdapter = RecyclerViewAdapter()
        binding.rv.adapter = recyclerViewAdapter
        binding.rv.layoutManager = LinearLayoutManager(this)
    }

    private fun initViewModel() {
//        namesViewModel.allNames.observe(this) { names ->
//            names.let {
//                adapter.submitList(it)
//            }
//        }
    }

    private suspend fun getResponse() {
        val response = try {
            RetrofitInstance.api.getNames()
        } catch (x: Exception) {
            Toast.makeText(this, x.message, Toast.LENGTH_LONG).show()
            Log.e("retrofit", "getResponse: ${x.message}")
            return
        }
        if (response.isSuccessful && response.body() != null) {
            val list = response.body()!!

            // insert to the room database
            list.forEach {
//              TimeUnit.SECONDS.sleep(1L)
                namesViewModel.insert(
                    Name(it)
                )
            }

            val names = namesViewModel.getNamesFromDB()
            recyclerViewAdapter.submitList(names)

        } else {
            Log.e("MainActivity", "Response not successful")
        }
    }
}
