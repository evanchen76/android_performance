package evan.chen.tutorial.containertransformsample

import java.io.Serializable

data class Scenery (var sceneryId: Int, var name: String, var imageUrl: String?, var thumbUrl: String, var desc: String?):
    Serializable