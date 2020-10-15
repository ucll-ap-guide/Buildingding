package buildingding.proximus.view

import android.graphics.Color
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import buildingding.proximus.R
import buildingding.proximus.model.Dijkstra
import buildingding.proximus.model.Graph
import buildingding.proximus.model.Location
import buildingding.proximus.repository.LocationRepository
import com.devs.vectorchildfinder.VectorChildFinder
import com.devs.vectorchildfinder.VectorDrawableCompat

class Navigation2DActivity : AppCompatActivity() {
    var res : List<String>? = null
    var path: VectorDrawableCompat.VFullPath? = null
    var vb0: VectorChildFinder? = null
    var imgB0: ImageView? = null
    var pathS: VectorDrawableCompat.VFullPath? = null
    var pathE: VectorDrawableCompat.VFullPath? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation2_d)
        setTitle(R.string.title_2D_navigation)
        imgB0 = findViewById(R.id.image)
        vb0 = VectorChildFinder(this, R.drawable.ic_bb, imgB0)

        res = intent.getStringArrayListExtra("list")
        onButtonClick()

    }

    private fun onButtonClick() {
        var x = res?.size
        pathS = vb0?.findPathByName(res?.get(0))
        pathS?.setFillColor(Color.parseColor("#002757"))
        if (x != null) {
            pathE = vb0?.findPathByName(res?.get(x - 1))
        }
        pathE?.setFillColor(Color.parseColor("#E00049"))
        for (i in 1 until x!!) {
            when (res!![i - 1]?.substring(0, 2)) {
                "B0" -> {
                    var pathName = res!![i - 1] + "_" + res!![i]
                    path = vb0?.findPathByName(pathName)
                    path?.setStrokeColor(Color.parseColor("#E00049"))
                    path?.setFillColor(Color.parseColor("#E00049"))
                }
            }
            imgB0!!.invalidate()
        }
    }
}