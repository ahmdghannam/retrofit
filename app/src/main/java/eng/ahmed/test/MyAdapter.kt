package eng.ahmed.test

import android.view.LayoutInflater

import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import eng.ahmed.test.databinding.PeopleNamesBinding


class MyAdapter() : ListAdapter<Name, MyAdapter.NamesViewHolder>(COMPARATOR) {
    private lateinit var binding: PeopleNamesBinding
    var people= emptyList<String>()

    inner class NamesViewHolder(val binding: PeopleNamesBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NamesViewHolder {

        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.people_names,
            parent,
            false
        )
//        val layoutInflater = LayoutInflater.from(parent.context)
        return NamesViewHolder(binding)//        val layoutInflater = LayoutInflater.from(parent.context)

    }

    override fun onBindViewHolder(holder: NamesViewHolder, position: Int) {
//        binding.textView.text = list[position].name  // replaced with databinding in xml
        holder.binding.person = people[position]
    }

    override fun getItemCount(): Int {
        return people.size
    }

    companion object{
        private val COMPARATOR= object :

            DiffUtil.ItemCallback<Name>(){
            override fun areItemsTheSame(oldItem: Name, newItem: Name): Boolean {
              return oldItem==newItem
            }

            override fun areContentsTheSame(oldItem: Name, newItem: Name): Boolean {
                return oldItem.name==newItem.name
            }

        }
    }

}