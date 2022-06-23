package com.techgv.themovielibrary.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.techgv.themovielibrary.R
import com.techgv.themovielibrary.data.remote.response.VideoResults

class VideoAdapter(var list: List<VideoResults>) :
    RecyclerView.Adapter<VideoAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val videoView: com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView =
            itemView.findViewById(R.id.youtube_player_view)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.video_clip_item, parent, false
        )
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val key = list[position].key
        holder.videoView.addYouTubePlayerListener(object :
            com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                youTubePlayer.cueVideo(key, 0f)
            }
        })
    }


    override fun getItemCount(): Int = list.size
}