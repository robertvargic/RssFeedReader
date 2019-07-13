package com.example.rssfeedreader.ui.feedView

import android.util.Log
import com.example.rssfeedreader.models.FeedItem
import org.w3c.dom.Document
import javax.xml.parsers.DocumentBuilderFactory


class RssFeedPresenter : RssFeedContract.Presenter {

    lateinit var rssFeedView: RssFeedContract.View

    constructor(rssFeedView: RssFeedContract.View) {
        this.rssFeedView = rssFeedView
    }

    override fun loadRssFeedForUrl(url: String) {
        var shit = FeedItem("title", "aa", "aa", "aa", "aa")
        var feedItems = mutableListOf (shit)
        rssFeedView.initListView(feedItems)
    }

    override fun start() {
    }

    private fun processXml(data: Document?): MutableList<FeedItem> {
        lateinit var feedItems: MutableList<FeedItem>
        if (data != null) {
            val root = data.documentElement
            val channel = root.childNodes.item(1)
            val items = channel.childNodes
            for (i in 0 until items.length) {
                val currentChild = items.item(i)
                if (currentChild.nodeName == "item") {
                    val feedItem = FeedItem()
                    val feedItemChilds = currentChild.childNodes
                    for (j in 0 until feedItemChilds.length) {
                        val currenctChild = feedItemChilds.item(j)
                        if (currenctChild.nodeName == "title") {
                            feedItem.title = currenctChild.textContent
                        } else if (currenctChild.nodeName == "description") {
                            feedItem.description = currenctChild.textContent
                        } else if (currenctChild.nodeName == "pubDate") {
                            feedItem.pubDate = currenctChild.textContent
                        } else if (currenctChild.nodeName == "link") {
                            feedItem.link = currenctChild.textContent
                        } else if (currenctChild.nodeName == "media:thumbnail") {
                            //this will return us thumbnail url
                            val url = currenctChild.attributes.item(0).textContent
                            feedItem.thumbnailUrl = url
                        }
                    }
                    feedItems.add(feedItem)
                }
            }
            Log.e("stuff", feedItems.toString())
            return feedItems
        }

        return feedItems
    }

    var rssFeed = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
            "<rss version=\"2.0\" xmlns:media=\"http://search.yahoo.com/mrss/\">\n" +
            "    <channel>\n" +
            "        <title>Latest News from Science Magazine</title>\n" +
            "        <link>http://www.sciencemag.org/rss/news_current.xml</link>\n" +
            "        <description>Editable in modal at Format : RSS Feed | Settings : RSS Description</description>\n" +
            "        <pubDate>Sat, 13 Jul 2019 11:42:14 -0400</pubDate>\n" +
            "\n" +
            "        <item>\n" +
            "            <title><![CDATA[Anthrax-carrying flies follow monkeys through the forest]]></title>\n" +
            "            <link>https://www.sciencemag.org/news/2019/07/anthrax-carrying-flies-follow-monkeys-through-forest</link>\n" +
            "            <pubDate>Fri, 12 Jul 2019 01:30:00 -0400</pubDate>\n" +
            "            <guid isPermaLink=\"true\">https://www.sciencemag.org/news/2019/07/anthrax-carrying-flies-follow-monkeys-through-forest?rss=1</guid>\n" +
            "            <description><![CDATA[It turns out humans aren’t the only primates with an insect problem]]></description>\n" +
            "            <media:credit role=\"author\"><![CDATA[Eva Frederick]]></media:credit>\n" +
            "            <media:category><![CDATA[Plants & Animals]]></media:category>\n" +
            "            <media:content url=\"https://www.sciencemag.org/sites/default/files/styles/article_main_teaser/public/SS2354405-1280x720.jpg?itok=utWChY2w\" height=\"266\" width=\"127\" />\n" +
            "            <media:thumbnail url=\"https://www.sciencemag.org/sites/default/files/styles/article_main_teaser/public/SS2354405-1280x720.jpg?itok=utWChY2w\" height=\"266\" width=\"127\" />\n" +
            "        </item>\n" +
            "\n" +
            "        <item>\n" +
            "            <title><![CDATA[In killing citizenship question, Trump adopts Census Bureau’s preferred solution to a thorny problem]]></title>\n" +
            "            <link>https://www.sciencemag.org/news/2019/07/killing-citizenship-question-trump-adopts-census-bureau-s-preferred-solution-thorny</link>\n" +
            "            <pubDate>Thu, 11 Jul 2019 09:25:00 -0400</pubDate>\n" +
            "            <guid isPermaLink=\"true\">https://www.sciencemag.org/news/2019/07/killing-citizenship-question-trump-adopts-census-bureau-s-preferred-solution-thorny?rss=1</guid>\n" +
            "            <description><![CDATA[But president’s desire for a tally of “illegal aliens” appears out of reach]]></description>\n" +
            "            <media:credit role=\"author\"><![CDATA[Jeffrey Mervis]]></media:credit>\n" +
            "            <media:category><![CDATA[Science and Policy]]></media:category>\n" +
            "            <media:content url=\"https://www.sciencemag.org/sites/default/files/styles/article_main_teaser/public/720Donald_Trump_%2829496131773%29.jpg?itok=Ucd_LSgt\" height=\"266\" width=\"127\" />\n" +
            "            <media:thumbnail url=\"https://www.sciencemag.org/sites/default/files/styles/article_main_teaser/public/720Donald_Trump_%2829496131773%29.jpg?itok=Ucd_LSgt\" height=\"266\" width=\"127\" />\n" +
            "        </item>\n" +
            "\n" +
            "        <item>\n" +
            "            <title><![CDATA[The point of pointing, and using seabirds to track ocean health]]></title>\n" +
            "            <link>https://www.sciencemag.org/podcast/point-pointing-and-using-seabirds-track-ocean-health</link>\n" +
            "            <pubDate>Thu, 11 Jul 2019 04:30:00 -0400</pubDate>\n" +
            "            <guid isPermaLink=\"true\">https://www.sciencemag.org/podcast/point-pointing-and-using-seabirds-track-ocean-health?rss=1</guid>\n" +
            "            <description><![CDATA[On this week’s show: How seabirds can be “barometers” for marine ecosystems during times of environmental stress, and the origins of pointing ]]></description>\n" +
            "            <media:credit role=\"author\"><![CDATA[Sarah Crespi]]></media:credit>\n" +
            "            <media:category><![CDATA[Scientific Community]]></media:category>\n" +
            "            <media:content url=\"https://www.sciencemag.org/sites/default/files/styles/article_main_teaser/public/images/pointing-1920.jpg?itok=2L4YgJOH\" height=\"266\" width=\"127\" />\n" +
            "            <media:thumbnail url=\"https://www.sciencemag.org/sites/default/files/styles/article_main_teaser/public/images/pointing-1920.jpg?itok=2L4YgJOH\" height=\"266\" width=\"127\" />\n" +
            "        </item>\n" +
            "\n" +
            "        <item>\n" +
            "            <title><![CDATA[Starving children often don’t recover, even when fed enough. Restoring their gut bacteria could help]]></title>\n" +
            "            <link>https://www.sciencemag.org/news/2019/07/starving-children-often-don-t-recover-even-when-fed-enough-restoring-their-gut-bacteria</link>\n" +
            "            <pubDate>Thu, 11 Jul 2019 02:00:00 -0400</pubDate>\n" +
            "            <guid isPermaLink=\"true\">https://www.sciencemag.org/news/2019/07/starving-children-often-don-t-recover-even-when-fed-enough-restoring-their-gut-bacteria?rss=1</guid>\n" +
            "            <description><![CDATA[Most of the new experiments were in animals, but a small group of malnourished children improved]]></description>\n" +
            "            <media:credit role=\"author\"><![CDATA[Elizabeth Pennisi]]></media:credit>\n" +
            "            <media:category><![CDATA[Health]]></media:category>\n" +
            "            <media:content url=\"https://www.sciencemag.org/sites/default/files/styles/article_main_teaser/public/nutrition_16x9.jpg?itok=HhEtibnv\" height=\"266\" width=\"127\" />\n" +
            "            <media:thumbnail url=\"https://www.sciencemag.org/sites/default/files/styles/article_main_teaser/public/nutrition_16x9.jpg?itok=HhEtibnv\" height=\"266\" width=\"127\" />\n" +
            "        </item>\n" +
            "\n" +
            "        <item>\n" +
            "            <title><![CDATA[Giant batteries and cheap solar power are shoving fossil fuels off the grid]]></title>\n" +
            "            <link>https://www.sciencemag.org/news/2019/07/giant-batteries-and-cheap-solar-power-are-shoving-fossil-fuels-grid</link>\n" +
            "            <pubDate>Thu, 11 Jul 2019 01:40:00 -0400</pubDate>\n" +
            "            <guid isPermaLink=\"true\">https://www.sciencemag.org/news/2019/07/giant-batteries-and-cheap-solar-power-are-shoving-fossil-fuels-grid?rss=1</guid>\n" +
            "            <description><![CDATA[Cost of solar power has dropped by 76% since 2012]]></description>\n" +
            "            <media:credit role=\"author\"><![CDATA[Robert F. Service]]></media:credit>\n" +
            "            <media:category><![CDATA[Engineering]]></media:category>\n" +
            "            <media:content url=\"https://www.sciencemag.org/sites/default/files/styles/article_main_teaser/public/ca_0712NID_Solar_Farm_California_online.jpg?itok=1tYyUt3z\" height=\"266\" width=\"127\" />\n" +
            "            <media:thumbnail url=\"https://www.sciencemag.org/sites/default/files/styles/article_main_teaser/public/ca_0712NID_Solar_Farm_California_online.jpg?itok=1tYyUt3z\" height=\"266\" width=\"127\" />\n" +
            "        </item>\n" +
            "\n" +
            "        <item>\n" +
            "            <title><![CDATA[Courting controversy, scientists team with industry to tackle one of the world’s most destructive crops]]></title>\n" +
            "            <link>https://www.sciencemag.org/news/2019/07/courting-controversy-scientists-team-industry-tackle-one-world-s-most-destructive-crops</link>\n" +
            "            <pubDate>Thu, 11 Jul 2019 01:30:00 -0400</pubDate>\n" +
            "            <guid isPermaLink=\"true\">https://www.sciencemag.org/news/2019/07/courting-controversy-scientists-team-industry-tackle-one-world-s-most-destructive-crops?rss=1</guid>\n" +
            "            <description><![CDATA[Growing number of researchers say it’s time to make the best of a bad situation]]></description>\n" +
            "            <media:credit role=\"author\"><![CDATA[Dyna Rochmyaningsih ]]></media:credit>\n" +
            "            <media:category><![CDATA[Environment, Plants & Animals]]></media:category>\n" +
            "            <media:content url=\"https://www.sciencemag.org/sites/default/files/styles/article_main_teaser/public/MA_0712_Feat_PalmOil_1280x720.jpg?itok=xTAnjO_x\" height=\"266\" width=\"127\" />\n" +
            "            <media:thumbnail url=\"https://www.sciencemag.org/sites/default/files/styles/article_main_teaser/public/MA_0712_Feat_PalmOil_1280x720.jpg?itok=xTAnjO_x\" height=\"266\" width=\"127\" />\n" +
            "        </item>\n" +
            "\n" +
            "        <item>\n" +
            "            <title><![CDATA[This ancient bird sported a ginormous toe]]></title>\n" +
            "            <link>https://www.sciencemag.org/news/2019/07/ancient-bird-sported-ginormous-toe</link>\n" +
            "            <pubDate>Thu, 11 Jul 2019 11:00:00 -0400</pubDate>\n" +
            "            <guid isPermaLink=\"true\">https://www.sciencemag.org/news/2019/07/ancient-bird-sported-ginormous-toe?rss=1</guid>\n" +
            "            <description><![CDATA[<em>Elektorornis</em>’s third digit was longer than its lower leg]]></description>\n" +
            "            <media:credit role=\"author\"><![CDATA[Sabine Galvis]]></media:credit>\n" +
            "            <media:category><![CDATA[Plants & Animals]]></media:category>\n" +
            "            <media:content url=\"https://www.sciencemag.org/sites/default/files/styles/article_main_teaser/public/bird_16x9_7.jpg?itok=Q3xo0OEg\" height=\"266\" width=\"127\" />\n" +
            "            <media:thumbnail url=\"https://www.sciencemag.org/sites/default/files/styles/article_main_teaser/public/bird_16x9_7.jpg?itok=Q3xo0OEg\" height=\"266\" width=\"127\" />\n" +
            "        </item>\n" +
            "\n" +
            "        <item>\n" +
            "            <title><![CDATA[In a first, a Japanese spacecraft appears to have collected samples from inside an asteroid]]></title>\n" +
            "            <link>https://www.sciencemag.org/news/2019/07/first-japanese-spacecraft-appears-have-collected-samples-inside-asteroid</link>\n" +
            "            <pubDate>Thu, 11 Jul 2019 11:00:00 -0400</pubDate>\n" +
            "            <guid isPermaLink=\"true\">https://www.sciencemag.org/news/2019/07/first-japanese-spacecraft-appears-have-collected-samples-inside-asteroid?rss=1</guid>\n" +
            "            <description><![CDATA[Hayabusa2’s second touchdown on asteroid Ryugu went well, engineers say]]></description>\n" +
            "            <media:credit role=\"author\"><![CDATA[Dennis Normile]]></media:credit>\n" +
            "            <media:category><![CDATA[Space]]></media:category>\n" +
            "            <media:content url=\"https://www.sciencemag.org/sites/default/files/styles/article_main_teaser/public/hayabusa2_16x9.jpg?itok=jAAbTWzR\" height=\"266\" width=\"127\" />\n" +
            "            <media:thumbnail url=\"https://www.sciencemag.org/sites/default/files/styles/article_main_teaser/public/hayabusa2_16x9.jpg?itok=jAAbTWzR\" height=\"266\" width=\"127\" />\n" +
            "        </item>\n" +
            "\n" +
            "        <item>\n" +
            "            <title><![CDATA[Update: Hawaii governor says construction of controversial giant telescope will begin soon]]></title>\n" +
            "            <link>https://www.sciencemag.org/news/2019/06/divisive-giant-telescope-cleared-construction-hawaiian-peak</link>\n" +
            "            <pubDate>Thu, 11 Jul 2019 09:00:00 -0400</pubDate>\n" +
            "            <guid isPermaLink=\"true\">https://www.sciencemag.org/news/2019/06/divisive-giant-telescope-cleared-construction-hawaiian-peak?rss=1</guid>\n" +
            "            <description><![CDATA[Thirty Meter Telescope surmounts last legal hurdle but protesters plan to disrupt construction]]></description>\n" +
            "            <media:credit role=\"author\"><![CDATA[Daniel Clery]]></media:credit>\n" +
            "            <media:category><![CDATA[Space]]></media:category>\n" +
            "            <media:content url=\"https://www.sciencemag.org/sites/default/files/styles/article_main_teaser/public/TMT_16x9.jpg?itok=NV-yMxqA\" height=\"266\" width=\"127\" />\n" +
            "            <media:thumbnail url=\"https://www.sciencemag.org/sites/default/files/styles/article_main_teaser/public/TMT_16x9.jpg?itok=NV-yMxqA\" height=\"266\" width=\"127\" />\n" +
            "        </item>\n" +
            "\n" +
            "        <item>\n" +
            "            <title><![CDATA[Is the Western mind too WEIRD to study?]]></title>\n" +
            "            <link>https://www.sciencemag.org/news/2019/07/western-mind-too-weird-study</link>\n" +
            "            <pubDate>Thu, 11 Jul 2019 08:00:00 -0400</pubDate>\n" +
            "            <guid isPermaLink=\"true\">https://www.sciencemag.org/news/2019/07/western-mind-too-weird-study?rss=1</guid>\n" +
            "            <description><![CDATA[Psychologist says research relies on too many participants from Western, educated, industrialized, rich, and democratic societies]]></description>\n" +
            "            <media:credit role=\"author\"><![CDATA[Kai Kupferschmidt]]></media:credit>\n" +
            "            <media:category><![CDATA[Sociology]]></media:category>\n" +
            "            <media:content url=\"https://www.sciencemag.org/sites/default/files/styles/article_main_teaser/public/Haun-Child-1280x720.jpg?itok=4au-8EBG\" height=\"266\" width=\"127\" />\n" +
            "            <media:thumbnail url=\"https://www.sciencemag.org/sites/default/files/styles/article_main_teaser/public/Haun-Child-1280x720.jpg?itok=4au-8EBG\" height=\"266\" width=\"127\" />\n" +
            "        </item>\n" +
            "    </channel>\n" +
            "</rss>"
}