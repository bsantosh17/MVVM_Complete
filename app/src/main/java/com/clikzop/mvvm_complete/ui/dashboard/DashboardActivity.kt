package com.clikzop.mvvm_complete.ui.dashboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.clikzop.mvvm_complete.R
import com.clikzop.mvvm_complete.data.network.RetrofitClient
import com.clikzop.mvvm_complete.data.repository.Mainrepository
import com.clikzop.mvvm_complete.databinding.ActivityDashboardBinding
import com.clikzop.mvvm_complete.ui.dashboard.adapter.AdapterDashboard
import com.clikzop.mvvm_complete.ui.dashboard.adapter.AdapterProduct1
import com.clikzop.mvvm_complete.ui.dashboard.adapter.AdapterProduct2
import com.clikzop.mvvm_complete.ui.dashboard.adapter.AdapterProduct4
import com.clikzop.mvvm_complete.ui.dashboard.product1.Product
import com.clikzop.mvvm_complete.ui.dashboard.product2.ResponseProduct2Item
import com.clikzop.mvvm_complete.ui.dashboard.product4.Product4Data
import com.clikzop.mvvm_complete.ui.dashboard.viewModel.ViewModelFactory1
import com.clikzop.mvvm_complete.ui.dashboard.viewModel.ViewModelProduct1
import com.clikzop.mvvm_complete.util.NetworkUtils
import com.clikzop.mvvm_complete.util.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class DashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding
    private lateinit var viewModelProduct1: ViewModelProduct1
    private lateinit var adapter3:AdapterDashboard
    private lateinit var adapter1:AdapterProduct1
    private lateinit var adapter2: AdapterProduct2
    private lateinit var adapter4: AdapterProduct4

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard)

        val retrofitClient = RetrofitClient.apiService
        val mainrepository = Mainrepository(retrofitClient)
        viewModelProduct1 = ViewModelProvider(
            this,
            ViewModelFactory1(mainrepository)
        )[ViewModelProduct1::class.java]

        if (NetworkUtils.isNetworkAvailable(this)) {
            Utils.showProgress(this@DashboardActivity)
            fetchData()

        } else {
            Utils.hideProgress()
            Utils.snackbarMessage(this, binding.root, "No Internet Connection")
        }
        observeViewModel()

    }

    private fun fetchData() {
        lifecycleScope.launch {
            try {
                val product1 = async(Dispatchers.IO) { viewModelProduct1.getProduct1() }
                val product2 = async(Dispatchers.IO) { viewModelProduct1.getProduct2() }
                val product3 = async(Dispatchers.IO) { viewModelProduct1.getProduct3() }
                val product4 = async(Dispatchers.IO) { viewModelProduct1.getProduct4() }

                product1.await()
                product2.await()
                product3.await()
                product4.await()
            } catch (e: Exception) {
                Utils.snackbarMessage(this@DashboardActivity, binding.root, "Error fetching data")
            }
        }
    }


    private fun observeViewModel() {
        Log.i("DashboardCheck", "getProduct1: ${Thread.currentThread().name}")
        viewModelProduct1.pro1Live.observe(this) { pro1 ->
            Utils.hideProgress()
            if (pro1 != null) {
                val list1 = mutableListOf<Product>()
                for(i in pro1.products){
                    val product = Product(i.brand,i.category,i.price,i.thumbnail,i.title)
                    list1.add(product)
                }
                adapter1 = AdapterProduct1(this,list1)
                binding.rv1.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)
                binding.rv1.adapter = adapter1

            } else {
                Utils.snackbarMessage(this, binding.root, "Failed to load Product1")
            }

        }

        viewModelProduct1.pro2Live.observe(this) { pro2 ->
            Utils.hideProgress()
            if (pro2 != null) {
                val list = mutableListOf<ResponseProduct2Item>()
                pro2.map {
                    val res = ResponseProduct2Item(it.name,it.slug, it.url)
                    list.add(res)
                }
                adapter2 = AdapterProduct2(this,list)
                binding.rv2.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false)
                binding.rv2.adapter = adapter2
            } else {
                Utils.snackbarMessage(this, binding.root, "Failed to load Product2")
            }
        }

        viewModelProduct1.pro3Live.observe(this) { pro3 ->
            Utils.hideProgress()
            if (pro3 != null) {
                val list = mutableListOf<String>()
                pro3.map { list.add(it) }
                adapter3 = AdapterDashboard(this, list)
                binding.rv3.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
                binding.rv3.adapter = adapter3
            } else {
                Utils.snackbarMessage(this, binding.root, "Failed to load Product3")
            }
        }

        viewModelProduct1.pro4Live.observe(this) { pro4 ->
            Utils.hideProgress()
            if (pro4 != null) {
                val list4 = mutableListOf<Product4Data>()
                for(i in pro4.products){
                    val p4 = Product4Data(i.brand,i.thumbnail,i.price)
                    list4.add(p4)
                }
                adapter4 = AdapterProduct4(this,list4)
                binding.rv4.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
                binding.rv4.adapter = adapter4
            } else {
                Utils.snackbarMessage(this, binding.root, "Failed to load Product3")
            }
        }
    }
}