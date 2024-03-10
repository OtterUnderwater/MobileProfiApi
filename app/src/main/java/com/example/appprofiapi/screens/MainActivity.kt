package com.example.appprofiapi.screens

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.example.appprofiapi.R
import com.example.appprofiapi.api.AdapterCatalog
import com.example.appprofiapi.api.ApiRequest
import com.example.appprofiapi.databinding.ActivityMainBinding
import com.example.appprofiapi.models.Catalog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    //создаем переменную для биндинга
    lateinit var binding: ActivityMainBinding
    //создаем экземпляр адаптера
    private val adapterCatalog = AdapterCatalog()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getCatalog()
    }

    private fun getCatalog() {

        //Описание запроса к api
        val api = Retrofit.Builder()
            .baseUrl("https://iis.ngknn.ru/NGKNN/МамшеваЮС/MedicMadlab/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiRequest::class.java)

        //Сам запрос делаем в отдельном потоке
        CoroutineScope(Dispatchers.IO).launch {
            try {
                //Делаем запрос и получаем ответ в переменную
                val response = api.getCatalog().awaitResponse()
                //Проверяем, успешный ли запрос
                if (response.isSuccessful) {
                    //Если да, парсим
                    val data = response.body()!!
                    //Когда получили ответ, запускаем функцию парса данных в recyclerview
                    runOnUiThread { parseCatalog(data) }
                    Log.d(TAG, data.toString())
                }
            } catch (e: Exception) {
                Log.d(TAG, e.message.toString())
            }
        }

    }

    private fun parseCatalog(listCatalog: List<Catalog>) {
        with(binding) {
            listCatalogView.layoutManager = GridLayoutManager(this@MainActivity, 1)
            listCatalogView.adapter = adapterCatalog
            listCatalog.forEach {
                adapterCatalog.addCatalog(it)
            }
        }
    }
}