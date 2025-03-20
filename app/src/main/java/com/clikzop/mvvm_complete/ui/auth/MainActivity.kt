package com.clikzop.mvvm_complete.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.clikzop.mvvm_complete.R
import com.clikzop.mvvm_complete.data.local.sharedPref.PreferencesHelper
import com.clikzop.mvvm_complete.data.network.NetworkState
import com.clikzop.mvvm_complete.data.network.RetrofitClient
import com.clikzop.mvvm_complete.data.repository.Mainrepository
import com.clikzop.mvvm_complete.databinding.ActivityMainBinding
import com.clikzop.mvvm_complete.ui.auth.model.LoginRequest
import com.clikzop.mvvm_complete.ui.auth.viewModel.ViewModelFactoryLogin
import com.clikzop.mvvm_complete.ui.auth.viewModel.ViewModelLogin
import com.clikzop.mvvm_complete.util.NetworkUtils
import com.clikzop.mvvm_complete.util.Utils
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModelLogin: ViewModelLogin
    private lateinit var preferencesHelper: PreferencesHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        preferencesHelper = PreferencesHelper(this)

        val isLogin = preferencesHelper.getLogin()
        Log.i("TAG", "onCreate: ${isLogin}")
        if(isLogin != null){
            Utils.toastMessage(this,isLogin.toString())
            Log.i("TAG", "onCreate: ${isLogin}")
        }

        val retrofitclient = RetrofitClient.apiService
        val mainLoginResponse = Mainrepository(retrofitclient)
        viewModelLogin = ViewModelProvider(this,ViewModelFactoryLogin(mainLoginResponse))[ViewModelLogin::class.java]

        binding.button.setOnClickListener {
            if (NetworkUtils.isNetworkAvailable(this)) {
                if(binding.etEmail.text.toString().trim().isEmpty() ||binding.etPassword.text.toString().trim().isEmpty()){
                    Utils.snackbarMessage(this,binding.root,"Enter Credentials")
                }else{
                    apiIntegration()
                }

            } else {
                Utils.snackbarMessage(this,binding.root,"No Internet Connection")
            }
        }

        viewModelLogin._loginData.observe(this, Observer {
            networkState ->
            Utils.hideProgress()
            when(networkState){
                is NetworkState.Success ->{
                    Utils.snackbarMessage(this,binding.root,"Login Successfully...")
                    preferencesHelper.saveLogin("login")
                    Log.i("TAG", "onCreate: ${networkState.data}")

                }

                is NetworkState.Error ->{
                    Utils.snackbarMessage(this,binding.root,"Invalid Credentials")
                }
            }
        })

    }

    private fun apiIntegration() {
        Utils.showProgress(this)
        val loginRequest =
            LoginRequest(binding.etPassword.text.toString(), binding.etEmail.text.toString())
        lifecycleScope.launch { viewModelLogin.loginCall(loginRequest) }

    }

    //New Branch Code
}