package evan.chen.tutorial.containertransformsample

import android.os.Handler
import android.os.Looper

class SceneryRepository {

    private val scenerys = listOf(
        Scenery(
            1,
            "羅東運動公園",
            "https://firebasestorage.googleapis.com/v0/b/blog-7e837.appspot.com/o/screnery1.jpg?alt=media&token=56df0774-9bc8-426e-82d0-018a6542b88d",
            "https://firebasestorage.googleapis.com/v0/b/blog-7e837.appspot.com/o/screnerym.jpg?alt=media&token=eb6464e4-ac86-4c59-a0bd-67f982055f8c",
            "羅東運動公園位於宜蘭縣羅東鎮近郊，是座充滿運動設施、綠地以及親水設計的休憩場所。運動公園佔地47公頃，有著開闊的草地以及扶疏的林木，籃球場、網球場、棒球場、田徑場、游泳池等等設施座落其中，並有交橫的流水及湖泊串聯各項景觀，十分美麗，是羅東人平時休閒的去處也是外縣市民眾至宜蘭縣的一個主要觀光旅遊景點。"
        ), Scenery(
            2,
            "葛瑪蘭",
            "https://firebasestorage.googleapis.com/v0/b/blog-7e837.appspot.com/o/scenery2.JPG?alt=media&token=68ac1745-1551-44b1-a61e-bbeef2d847ae",
            "https://firebasestorage.googleapis.com/v0/b/blog-7e837.appspot.com/o/scenery2m.JPG?alt=media&token=bd61167b-8ea4-4e73-b0ad-1cb3dc90cf32",
            "是宜蘭的舊稱，最早的原住民族以踏實辛勤\n" +
                    "將此地耕耘成一塊瑰寶，冀望回饋土地賦予的恩賜，噶瑪蘭威士忌以此為名\n" +
                    "讓金黃酒液的醇香之中映出這片寶地的美麗風情"
        ), Scenery(
            3,
            "冬山車站",
            "https://firebasestorage.googleapis.com/v0/b/blog-7e837.appspot.com/o/scenery3.JPG?alt=media&token=e21c037a-1679-4184-aeed-1c95ec38309e",
            "https://firebasestorage.googleapis.com/v0/b/blog-7e837.appspot.com/o/scenery3m.JPG?alt=media&token=5d48e76c-e7cb-4837-acb1-f0d3d930ac27",
            "冬山車站位於台灣宜蘭縣冬山鄉，為臺灣鐵路管理局宜蘭線的鐵路車站。本站作為冬山鄉的主要車站，站前的中正路上聚集了該鄉大部分的主要機構與建築，包括鄉公所、郵局、農會乃至於旅遊服務中心。"
        ), Scenery(
            4,
            "高美溼地",
            "https://firebasestorage.googleapis.com/v0/b/blog-7e837.appspot.com/o/scenery4.JPG?alt=media&token=761affe2-320a-4d7f-a335-eb464446468f",
            "https://firebasestorage.googleapis.com/v0/b/blog-7e837.appspot.com/o/scenery4m.JPG?alt=media&token=07d60769-21b8-4a68-935f-4bca0509d0ee",
            "高美野生動物保護區是臺灣西海岸一處生態保護區，位於臺中市清水區西側，包括大甲溪出海口，綜合淡水注水與潮汐交替所構成的海岸溼地[1][2]。其前身原為海水浴場，自從臺中港北岸沙堤築起後，大甲溪挾帶泥沙於此，逐漸淤積成漂飛砂地帶，成為現今所見「高美溼地」[3][4]。在保護區南北兩處有清水大排與大甲溪注入淡水，並以雲林莞草、大安水蓑衣兩種瀕危植物生長最受關注"
        ), Scenery(
            5,
            "阿里山",
            "https://firebasestorage.googleapis.com/v0/b/blog-7e837.appspot.com/o/scenery5.JPG?alt=media&token=012ce7fc-9362-41f4-af04-3bfe8f6815c0",
            "https://firebasestorage.googleapis.com/v0/b/blog-7e837.appspot.com/o/scenery5m.JPG?alt=media&token=fcf290ba-8b41-42a6-af97-365d1b8c9db7",
            "阿里山國家風景區位於臺灣嘉義縣東部，是由交通部觀光局規劃與管理的一座國家級風景特定區，設立於2001年。其範圍涵蓋了位於阿里山鄉、仍由林務局經營管理的阿里山國家森林遊樂區，並且擴大至附近的梅山鄉、竹崎鄉和番路鄉一帶的知名風景區[1]。\n" +
                    "\n" +
                    "阿里山實際上並不是一座山的名稱，只是特定範圍的統稱，正確說法應是「阿里山區」，地理上屬於阿里山山脈主山脈的一部份，東鄰玉山山脈，北接雪山山脈。某些資料指稱阿里山又名塔山，標高2,484公尺，實則不然：塔山為「阿里山區」之最高峰，標高2,663公尺。"
        ), Scenery(
            6,
            "大稻呈",
            "https://firebasestorage.googleapis.com/v0/b/blog-7e837.appspot.com/o/scenery6.JPG?alt=media&token=0a216d12-7bdc-41c8-8bf1-f1a626bb569b",
            "https://firebasestorage.googleapis.com/v0/b/blog-7e837.appspot.com/o/scenery6m.JPG?alt=media&token=9ada8859-d683-450f-aba9-6bc8992d8133",
            "大稻埕（白話字：Tōa-tiū-tiâⁿ，英語：Twatutia或Dadaocheng），雅稱稻江、稻津[註 1]，是臺北市大同區西南部的一個傳統地域名稱，因具有大片曬稻穀的空地（稻埕[註 2]）而得名。咸豐元年（1851年）泉州府同安縣人林藍田為躲避海盜洗劫，自雞籠（今基隆）遷至大稻埕經商並開設店鋪，開始此地的商業活動。三年（1853年）三邑人與同安人為艋舺碼頭的泊船權利，發生頂下郊拚分類械鬥事件，下郊的同安人無力抵抗，於是帶著自身信仰的霞海城隍敗逃至大稻埕，大稻埕旁淡水河的碼頭交易逐漸興起。十年（1860年）淡水開港後，大稻埕成為臺北最繁華的物資集散中心，以茶葉與布料的貿易為主。大稻埕的茶葉貿易在洋行的帶領下，不僅市場擴大，更造就驚人的財富與繁榮。進入日治時期後日商抵制洋行勢力，轉而以日本與東南亞為主要市場。戰後大稻埕因淡水河淤淺逐漸失去河港功能，且臺產茶葉不敵錫蘭紅茶的競爭而逐漸沒落。後在臺北市區逐漸向東發展中人口大量外移，成為臺北市外圍的老舊市區。"
        ), Scenery(
            7,
            "奇美博物館",
            "https://firebasestorage.googleapis.com/v0/b/blog-7e837.appspot.com/o/scenery7.jpg?alt=media&token=0e887895-637a-4bb9-a5d5-52c8a63258a9",
            "https://firebasestorage.googleapis.com/v0/b/blog-7e837.appspot.com/o/scenery7m.jpg?alt=media&token=a3a1fb26-ddf4-4f99-9a6b-f84dd7f820c7",
            "奇美博物館，位於臺灣臺南市仁德區，為奇美實業創辦人許文龍創立，是臺灣館藏最豐富的私人博物館、美術館。以典藏西洋藝術品為主，展出藝術、樂器、兵器與自然史四大領域。樂器領域，擁有全球數量最多小提琴收藏，其中包含世界各大製琴師名作。藝術方面，典藏台灣最完整西方繪畫雕塑，目標為建構出基礎西洋藝術史脈絡。兵器領域，展示亞洲最完整之各國珍貴古兵器，透過戰爭兵器呈現歷史與科技演進史。自然史領域，擁有亞洲最大動物標本收藏，範圍涵蓋五大洲哺乳類及鳥類。\n" +
                    "\n" +
                    "舊奇美博物館於1992年正式開放，利用奇美實業大樓5至8樓供民眾免費參觀，個人或團體可先行預約[1]。奇美實業大樓本館自2013年5月起停用，並陸續將展品遷至台南都會公園內的新館，工程於2008年12月動工，花費新臺幣約18.5億元興建，佔地9.5公頃，已於2012年5月17日捐贈給台南市政府[2]，於2015年1月1日啟用"
        )

    )

    fun sceneryList(): List<Scenery> {
        //模擬一開始取得的List的desc沒有資料
        return listOf(
            Scenery(
                1,
                "羅東運動公園",
                null,
                "https://firebasestorage.googleapis.com/v0/b/blog-7e837.appspot.com/o/screnerym.jpg?alt=media&token=eb6464e4-ac86-4c59-a0bd-67f982055f8c",
                null
            ), Scenery(
                2,
                "葛瑪蘭",
                null,
                "https://firebasestorage.googleapis.com/v0/b/blog-7e837.appspot.com/o/scenery2m.JPG?alt=media&token=bd61167b-8ea4-4e73-b0ad-1cb3dc90cf32",
                null
            ), Scenery(
                3,
                "冬山車站",
                null,
                "https://firebasestorage.googleapis.com/v0/b/blog-7e837.appspot.com/o/scenery3m.JPG?alt=media&token=5d48e76c-e7cb-4837-acb1-f0d3d930ac27",
                null
            ), Scenery(
                4,
                "高美溼地",
                null,
                "https://firebasestorage.googleapis.com/v0/b/blog-7e837.appspot.com/o/scenery4m.JPG?alt=media&token=07d60769-21b8-4a68-935f-4bca0509d0ee",
                null
            ), Scenery(
                5,
                "阿里山",
                null,
                "https://firebasestorage.googleapis.com/v0/b/blog-7e837.appspot.com/o/scenery5m.JPG?alt=media&token=fcf290ba-8b41-42a6-af97-365d1b8c9db7",
                null
            ), Scenery(
                6,
                "大稻呈",
                null,
                "https://firebasestorage.googleapis.com/v0/b/blog-7e837.appspot.com/o/scenery6m.JPG?alt=media&token=9ada8859-d683-450f-aba9-6bc8992d8133",
                null
            ), Scenery(
                7,
                "奇美博物館",
                null,
                "https://firebasestorage.googleapis.com/v0/b/blog-7e837.appspot.com/o/scenery7m.jpg?alt=media&token=a3a1fb26-ddf4-4f99-9a6b-f84dd7f820c7",
                null
            )
        )
    }

    fun scenery(id: Int, callback: (Scenery?) -> Unit) {
        Handler(Looper.getMainLooper())
            .postDelayed({
                val result = scenerys.firstOrNull { it.sceneryId == id }
                callback(result)
            }, 1500)
    }
}