package com.clikzop.mvvm_complete.util

import android.app.Dialog
import android.app.ProgressDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.clikzop.mvvm_complete.R
import com.google.android.material.snackbar.Snackbar

object Utils {

    fun snackbarMessage(context: Context, view: View, msg:String){
        val snackbar = Snackbar.make(view,msg, Snackbar.LENGTH_SHORT)
        snackbar.view.setBackgroundColor(ContextCompat.getColor(context, R.color.purple_200))
        snackbar.setTextColor(Color.WHITE)
        snackbar.setActionTextColor(Color.YELLOW)
        snackbar.setAction("Dismiss") { snackbar.dismiss() }
        snackbar.show()
    }

    private var progressDialog:ProgressDialog? = null

    fun showProgress(context: Context){
        if(progressDialog != null && progressDialog!!.isShowing){
            return
        }

        progressDialog = ProgressDialog(context)
        progressDialog!!.setTitle("Loading...")
        progressDialog?.setCancelable(false)
        progressDialog?.show()
    }

    fun hideProgress() {
        progressDialog?.dismiss()
        progressDialog = null
    }

    fun toastMessage(context: Context,msg:String){
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show()
    }
}