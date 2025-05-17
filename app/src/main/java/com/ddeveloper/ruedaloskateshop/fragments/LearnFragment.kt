package com.ddeveloper.ruedaloskateshop.fragments

import VideoAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ddeveloper.ruedaloskateshop.R
import com.ddeveloper.ruedaloskateshop.model.Video

class LearnFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: VideoAdapter

    private val videoList = listOf(
        Video("https://www.youtube.com/embed/LXk6K0Pp8Do?si=chmVHRFEz6Odxl6O", "¿ CÓMO HACER OLLIE ?"),
        Video("https://www.youtube.com/embed/tKokG16C2AA?si=DFw4bUrG9envGDiN","¿ COMO HACER FLIP 360 ?"),
        Video("https://www.youtube.com/embed/28fEeukv3o0?si=TDvenFCPnzEGXHd3","¿ CÓMO HACER HARDFLIP ?")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_learn, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = view.findViewById(R.id.videoWebView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = VideoAdapter(videoList)
        recyclerView.adapter = adapter
    }
}
