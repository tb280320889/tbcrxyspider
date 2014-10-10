package wh.tb.crxyspider.example;

import wh.tb.crxyspider.Site;
import wh.tb.crxyspider.model.OOSpider;
import wh.tb.crxyspider.model.annotation.ExtractBy;
import wh.tb.crxyspider.utils.Experimental;

import java.util.List;

/**
 * @author 280320889@qq.com
 * @since 1.0
 */
@Experimental
public class AppStore {

    @ExtractBy(type = ExtractBy.Type.JsonPath, value = "$..trackName")
    private String trackName;

    @ExtractBy(type = ExtractBy.Type.JsonPath, value = "$..description")
    private String description;

    @ExtractBy(type = ExtractBy.Type.JsonPath, value = "$..userRatingCount")
    private int userRatingCount;

    @ExtractBy(type = ExtractBy.Type.JsonPath, value = "$..screenshotUrls")
    private List<String> screenshotUrls;

    @ExtractBy(type = ExtractBy.Type.JsonPath, value = "$..supportedDevices")
    private List<String> supportedDevices;

    public static void main(String[] args) {
        wh.tb.crxyspider.example.AppStore appStore = OOSpider.create(Site.me(), wh.tb.crxyspider.example.AppStore.class).<wh.tb.crxyspider.example.AppStore>get("http://itunes.apple.com/lookup?id=653350791&country=cn&entity=software");
        System.out.println(appStore.trackName);
        System.out.println(appStore.description);
        System.out.println(appStore.userRatingCount);
        System.out.println(appStore.screenshotUrls);
        System.out.println(appStore.supportedDevices);
    }
}
