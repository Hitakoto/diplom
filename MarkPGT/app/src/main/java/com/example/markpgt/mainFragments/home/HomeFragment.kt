package com.example.markpgt.mainFragments.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.markpgt.R
import com.example.markpgt.RecordActivity

class HomeFragment : Fragment() {

    lateinit var ivbRecord : ImageView
    lateinit var v : View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        v = inflater.inflate(R.layout.fragment_home, container, false)

        ivbRecord = v.findViewById(R.id.ivb_rec)

        ivbRecord.setOnClickListener(View.OnClickListener { _ ->
            startActivity(Intent(activity, RecordActivity::class.java))
        })

        return v
    }
}