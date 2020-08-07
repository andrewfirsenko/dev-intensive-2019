package ru.skillbranch.devintensive

import android.graphics.Color
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import ru.skillbranch.devintensive.models.Bender

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val KEY_STATUS = "ru.skillbranch.devintensive.STATUS"
    private val KEY_QUESTION = "ru.skillbranch.devintensive.QUESTION"

    lateinit var benderImage: ImageView
    lateinit var textTxt: TextView
    lateinit var messageEt: EditText
    lateinit var sendBtn: ImageView
    lateinit var benderObj: Bender

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("M_MainActivity", "onCreate")

        init(savedInstanceState)
        sendBtn.setOnClickListener(this)
    }

    private fun init(savedInstanceState: Bundle?) {
        benderObj = if (savedInstanceState != null) {
            val status= savedInstanceState.getSerializable(KEY_STATUS) as Bender.Status
            val question = savedInstanceState.getSerializable(KEY_QUESTION) as Bender.Question
            Bender(status, question)
        } else {
            Bender()
        }
        Log.d("M_MainActivity", "${benderObj.status} ${benderObj.question}")

        benderImage = iv_bender
        textTxt = tv_text
        messageEt = et_message
        sendBtn = iv_send

        val (r, g, b) = benderObj.status.color
        benderImage.setColorFilter(Color.rgb(r, g, b), PorterDuff.Mode.MULTIPLY)
        textTxt.text = benderObj.askQuestion()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putSerializable(KEY_STATUS, benderObj.status)
        outState.putSerializable(KEY_QUESTION, benderObj.question)
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.iv_send) {
            val (phrase, color) = benderObj.listenAnswer(messageEt.text.toString().toLowerCase())
            messageEt.setText("")
            val (r, g, b) = color
            benderImage.setColorFilter(Color.rgb(r, g, b), PorterDuff.Mode.MULTIPLY)
            textTxt.text = phrase
        }
    }
}
