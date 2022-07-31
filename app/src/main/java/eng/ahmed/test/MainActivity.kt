package eng.ahmed.test

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import eng.ahmed.test.databinding.ActivityMainBinding
import java.lang.Exception
import androidx.activity.viewModels
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: MyAdapter
    private lateinit var binding: ActivityMainBinding
    private val namesViewModel: NamesViewModel by viewModels{
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

    private suspend fun getResponse() {
        val response = try {
            RetrofitInstance.api.getNames()
        } catch (x: Exception) {
            Toast.makeText(this, x.message, Toast.LENGTH_LONG).show()
            Log.e("retrofit", "getResponse: ${x.message}")
            return
        }
        if (response.isSuccessful && response.body() != null) {
            val list=response.body()!!

            namesViewModel.allNames.observe(this) { names ->
                names.let {
                    adapter.submitList(it)
                }
            }

//            adapter.people=list
            list.forEach {
//                TimeUnit.SECONDS.sleep(1L)
                namesViewModel.insert(Name(it)
                )}


            adapter.notifyDataSetChanged()
        } else {
            Log.e("MainActivity", "Response not successful")
        }
    }

    private fun setupRv() {
        adapter = MyAdapter()
        binding.rv.adapter = adapter
        binding.rv.layoutManager = LinearLayoutManager(this)
    }
}
