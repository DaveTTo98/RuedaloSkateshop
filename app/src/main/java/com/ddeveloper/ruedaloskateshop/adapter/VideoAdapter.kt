import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ddeveloper.ruedaloskateshop.R
import com.ddeveloper.ruedaloskateshop.model.Video

class VideoAdapter(private val videos: List<Video>) :
    RecyclerView.Adapter<VideoAdapter.VideoViewHolder>() {

    class VideoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val webView: WebView = view.findViewById(R.id.videoWebView)
        val title: TextView = view.findViewById(R.id.videoTitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_video, parent, false)
        return VideoViewHolder(view)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        val video = videos[position]
        holder.title.text = video.title
        val html = """
            <html><body style="margin:0">
            <iframe width="100%" height="100%" src="${video.url}" frameborder="0" allowfullscreen></iframe>
            </body></html>
        """.trimIndent()

        holder.webView.settings.javaScriptEnabled = true
        holder.webView.settings.loadWithOverviewMode = true
        holder.webView.settings.useWideViewPort = true
        holder.webView.loadData(html, "text/html", "utf-8")
    }

    override fun getItemCount(): Int = videos.size
}
