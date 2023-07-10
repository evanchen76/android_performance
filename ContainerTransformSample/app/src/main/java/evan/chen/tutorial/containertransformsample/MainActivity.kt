package evan.chen.tutorial.containertransformsample

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import android.view.View
import evan.chen.tutorial.containertransformsample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), MainAdapter.OnItemClickListener{

    lateinit var scenerys: List<Scenery>

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        scenerys = SceneryRepository().sceneryList()

        binding.recyclerView.layoutManager =
            GridLayoutManager(this, 2)
        val mainAdapter = MainAdapter(this, scenerys)
        mainAdapter.listener = this
        binding.recyclerView.adapter = mainAdapter
    }

    override fun onItemClick(view: View, position: Int) {
        val item = scenerys[position]

        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("scenery", item.sceneryId)

        val options = ActivityOptions.makeSceneTransitionAnimation(
            this,
            view,
            "shared_element_container"
        )

        startActivity(intent, options.toBundle())
    }

}
