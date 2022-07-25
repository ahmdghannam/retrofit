package eng.ahmed.test

import android.view.LayoutInflater

import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import eng.ahmed.test.databinding.PeopleNamesBinding


class MyAdapter() : RecyclerView.Adapter<MyAdapter.Holder>() {
    private lateinit var binding: PeopleNamesBinding
    var people= emptyList<Person>()

    inner class Holder(val binding: PeopleNamesBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {

        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.people_names,
            parent,
            false
        )
//        val layoutInflater = LayoutInflater.from(parent.context)
        return Holder(binding)

    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
//        binding.textView.text = list[position].name  // replaced with databinding in xml
        holder.binding.person = people[position]
    }

    override fun getItemCount(): Int {
        return people.size
    }


}