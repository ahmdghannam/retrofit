package eng.ahmed.test

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import eng.ahmed.test.databinding.ActivityMainBinding
import java.lang.Exception
class MainActivity : AppCompatActivity() {
    private lateinit var adapter: MyAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRv()
        lifecycleScope.launchWhenCreated {
            getResponse()
        }
    }
   private suspend fun getResponse(){
        val response =try {
            RetrofitInstance.api.getNames()
        }catch (x:Exception){
            Toast.makeText(this, x.message, Toast.LENGTH_LONG).show()
            return
        }
       if (response.isSuccessful && response.body() != null) {
           adapter.people = response.body()!!   // this !! notation assure that this is not null
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


