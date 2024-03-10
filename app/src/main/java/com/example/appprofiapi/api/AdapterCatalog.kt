package com.example.appprofiapi.api

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appprofiapi.R
import com.example.appprofiapi.databinding.ItemCatalogBinding
import com.example.appprofiapi.models.Catalog

class AdapterCatalog : RecyclerView.Adapter<AdapterCatalog.Holder>() {
    private var listCatalog = ArrayList<Catalog>()

    class Holder(item: View) : RecyclerView.ViewHolder(item) {
        private val binding = ItemCatalogBinding.bind(item)

        //получаем модель Catalog
        //чтобы не ругался само пишется SuppressLint
        @SuppressLint("SetTextI18n")
        fun bind(catalog: Catalog) {
            //чтобы каждый раз не прописывать заново binding.что-то
            with(binding) {
                tName.text = catalog.id.toString() + ". " + catalog.name
                tDescription.text = catalog.description
                tBiomaterial.text = catalog.bio
                tCategory.text = catalog.category
                tPodgotovka.text = catalog.preparation
                tPrice.text = catalog.price.toString() + " р."
                tResults.text = catalog.timeResul
            }
        }
    }

    //ВЫУЧИТЬ ААА
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_catalog, parent, false)
        return Holder(view)
    }

    //получаем размер getItemCount по количеству элементов листа
    override fun getItemCount(): Int = listCatalog.size

    //один элемент из массива по позиции
    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(listCatalog[position])
    }

    //Добавляем в лист один экзмпляр модели Catalog
    @SuppressLint("NotifyDataSetChanged")
    fun addCatalog(catalog: Catalog) {
        listCatalog.add(catalog)
        //указывает адаптеру, что полученные ранее данные изменились
        // и следует перерисовать список на экране
        notifyDataSetChanged()
    }

}