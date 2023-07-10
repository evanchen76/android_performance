package evan.chen.tutorial.containertransformsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback
import com.squareup.picasso.Picasso
import evan.chen.tutorial.containertransformsample.Scenery
import evan.chen.tutorial.containertransformsample.SceneryRepository
import evan.chen.tutorial.containertransformsample.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var scenery: Scenery
    private val binding by lazy { ActivityDetailBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.root.transitionName = "shared_element_container"
        setEnterSharedElementCallback(MaterialContainerTransformSharedElementCallback())
        setExitSharedElementCallback(MaterialContainerTransformSharedElementCallback())
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        scenery = (intent.getSerializableExtra("scenery") as Scenery?)!!

        binding.sceneryTitle.text = scenery.name
        binding.sceneryDesc.text = scenery.desc
        loadThumbnail()

        loadData()
    }

    private fun loadData() {
        SceneryRepository().scenery(scenery.sceneryId) { result ->
            result?.let {
                scenery = it
                binding.sceneryTitle.text = scenery.name
                binding.sceneryDesc.text = scenery.desc
                loadFullSizeImage()
            }
        }
    }


    private fun loadThumbnail() {
        Picasso.with(binding.sceneryImageView.context).load(scenery.thumbUrl)
            .noFade()
            .into(binding.sceneryImageView)
    }

    private fun loadFullSizeImage() {
        Picasso.with(binding.sceneryImageView.context).load(scenery.imageUrl)
            .noFade()
            .noPlaceholder()
            .into(binding.sceneryImageView)
    }
}
