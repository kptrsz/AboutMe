package com.ptr.aboutme

import android.content.Context
import android.hardware.input.InputManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethod
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import com.ptr.aboutme.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val myName: MyName = MyName("ptr.", "Jóska")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.myName = myName

        b_done.setOnClickListener {
            addNickName(it)
        }
    }

    private fun addNickName(v: View) {
        swapVisibleLayouts()
        hideSoftKeyboard(v)
    }

    private fun hideSoftKeyboard(v: View) {
        val inputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(v.windowToken, 0)
    }

    private fun swapVisibleLayouts() {
        binding.apply {
            myName?.nickName = tv_nickname.text.toString()
            invalidateAll()
            tv_nickname.text = et_name_edit.text
            et_name_edit.visibility = View.GONE
            b_done.visibility = View.GONE
            tv_nickname.visibility = View.VISIBLE
        }
    }

}
