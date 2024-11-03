package com.example.myapptodo.fragments.add

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.navigation.fragment.findNavController
import com.example.myapptodo.R
import com.example.myapptodo.data.ListViewModel
import com.example.myapptodo.data.Task



class AddFragment : Fragment() {

    private lateinit var mUserViewModel: ListViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        mUserViewModel = ViewModelProvider(this).get(ListViewModel::class.java)

        view.findViewById<Button>(R.id.save_btn).setOnClickListener{
            insertDataToDatabase()
        }

        return view
    }

    private fun insertDataToDatabase(){
        val titleTxt = edit_Txt2.text.toString()
        val  discriptionTxt = edit_Txt2.text.toString()

        if(inputCheck(titleTxt, discriptionTxt)){
            val task = Task(id:0, titleTxt, discriptionTxt)
         mUserViewModel.addTask(task)
          Toast.makeText(requireContext(),"Tarefa adicionada com sucesso!",Toast.LENGTH_LONG).show()

          findNavController().navigate(R.id.action_addFragment_to_listFragment)

        }
        else{
            Toast.makeText(requireContext(),"Favor,preenchir todos os requisitos!", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(titleTxt: String, discriptionTxt: String): Boolean{
        return !(TextUtils.isEmpty(titleTxt) && TextUtils.isEmpty(discriptionTxt))
   }

}
