package com.king.view.splitedittext.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.king.view.splitedittext.SplitEditText
import com.king.view.splitedittext.SplitEditText.OnSimpleTextInputListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    companion object{

        const val TAG = "MainActivity"
    }

    private var toast: Toast? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        splitEditText1.setOnTextInputListener(object : SplitEditText.OnTextInputListener {
            override fun onTextInputChanged(text: String, length: Int) {
                Log.d(TAG,"onTextInputChanged: $text")
            }

            override fun onTextInputCompleted(text: String) {
                Log.d(TAG,"onTextInputCompleted: $text")
                showToast(text)
            }

        })

        splitEditText2.setOnTextInputListener(object : OnSimpleTextInputListener() {

            override fun onTextInputCompleted(text: String) {
                Log.d(TAG,"onTextInputCompleted: $text")
                showToast(text)
            }

        })

        splitEditText3.setOnTextInputListener(object : OnSimpleTextInputListener() {

            override fun onTextInputCompleted(text: String) {
                Log.d(TAG,"onTextInputCompleted: $text")
                showToast(text)
            }

        })

        splitEditText4.setOnTextInputListener(object : OnSimpleTextInputListener() {

            override fun onTextInputCompleted(text: String) {
                Log.d(TAG,"onTextInputCompleted: $text")
                showToast(text)
            }

        })

        splitEditText5.setOnTextInputListener(object : OnSimpleTextInputListener() {

            override fun onTextInputCompleted(text: String) {
                Log.d(TAG,"onTextInputCompleted: $text")
                showToast(text)
            }

        })

    }


    private fun showToast(text: String) {
        if(toast == null){
            toast = Toast.makeText(this,text,Toast.LENGTH_SHORT)
        }else{
            toast?.setText(text)
        }

        toast?.show()
    }

}