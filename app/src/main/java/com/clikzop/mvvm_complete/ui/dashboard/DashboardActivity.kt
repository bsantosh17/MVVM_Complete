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
                binding.rv1.text = pro1.toString()
            } else {
                Utils.snackbarMessage(this, binding.root, "Failed to load Product1")
            }

        }


        viewModelProduct1.pro2Live.observe(this) { pro2 ->
            Utils.hideProgress()
            if (pro2 != null) {
                binding.rv2.text = pro2.toString()
            } else {
                Utils.snackbarMessage(this, binding.root, "Failed to load Product2")
            }
        }

        viewModelProduct1.pro3Live.observe(this) { pro3 ->
            Utils.hideProgress()
            if (pro3 != null) {
                //binding.rv3.text = pro3.toString()

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
                binding.rv4.text = pro4.toString()
            } else {
                Utils.snackbarMessage(this, binding.root, "Failed to load Product3")
            }
        }
    }
}