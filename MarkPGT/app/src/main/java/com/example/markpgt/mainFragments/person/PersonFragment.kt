package com.example.markpgt.mainFragments.person

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.markpgt.EditUserInfoActivity
import com.example.markpgt.R
import com.example.markpgt.SignInActivity

class PersonFragment : Fragment() {

    lateinit var btnEditInfoUser : Button
    lateinit var v : View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        v = inflater.inflate(R.layout.fragment_person, container, false)

        btnEditInfoUser = v.findViewById(R.id.editInfoUser)

        btnEditInfoUser.setOnClickListener(View.OnClickListener { _ ->
            startActivity(Intent(activity, EditUserInfoActivity::class.java))
        })

        return v
    }
}