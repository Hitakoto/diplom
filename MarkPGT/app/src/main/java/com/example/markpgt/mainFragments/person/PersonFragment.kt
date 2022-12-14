package com.example.markpgt.mainFragments.person

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.markpgt.EditUserInfoActivity
import com.example.markpgt.MainActivity
import com.example.markpgt.R
import com.example.markpgt.SignInActivity

class PersonFragment : Fragment() {

    lateinit var btnEditInfoUser : Button
    lateinit var v : View

    lateinit var textEmail: TextView
    lateinit var textDescription: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        v = inflater.inflate(R.layout.fragment_person, container, false)

        btnEditInfoUser = v.findViewById(R.id.editInfoUser)

        textEmail = v.findViewById(R.id.editEmPer)
        textDescription = v.findViewById(R.id.editDescriptionPer)

        textEmail.text = activity?.intent?.getStringExtra("Email")
        textDescription.text = activity?.intent?.getStringExtra("Description")
        val id : String? = activity?.intent?.getStringExtra("iduser")


        btnEditInfoUser.setOnClickListener(View.OnClickListener { _ ->
            val intent1 = Intent(activity, EditUserInfoActivity::class.java)
            intent1.putExtra("Id", id);
            startActivity(intent1)
        })

        return v
    }
}