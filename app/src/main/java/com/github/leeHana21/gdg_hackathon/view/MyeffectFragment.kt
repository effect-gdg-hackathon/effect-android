package com.github.leeHana21.gdg_hackathon.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.leeHana21.gdg_hackathon.R
import com.github.leeHana21.gdg_hackathon.databinding.FragmentBookmarkBinding
import com.github.leeHana21.gdg_hackathon.databinding.FragmentMyeffectBinding

class MyeffectFragment : Fragment() {
    private lateinit var binding: FragmentMyeffectBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_bookmark, container, false)
    }
}