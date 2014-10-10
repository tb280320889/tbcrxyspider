package wh.tb.crxyspider.example;

import wh.tb.crxyspider.Site;
import wh.tb.crxyspider.model.OOSpider;
import wh.tb.crxyspider.model.annotation.ExtractBy;
import wh.tb.crxyspider.model.annotation.TargetUrl;
import wh.tb.crxyspider.pipeline.JsonFilePageModelPipeline;

import java.util.Date;
import java.util.List;

/**
 * @author 280320889@qq.com <br>
 * @since 1.0
 */
@TargetUrl("http://my.oschina.net/flashsword/blog/\\d+")
public class OschinaBlog {

    @ExtractBy("//title/text()")
    private String title;

    @ExtractBy(value = "div.BlogContent", type = ExtractBy.Type.Css)
    private String content;

    @ExtractBy(value = "//div[@class='BlogTags']/a/text()", multi = true)
    private List<String> tags;

    @ExtractBy("//div[@class='BlogStat']/regex('\\d+-\\d+-\\d+\\s+\\d+:\\d+')")
    private Date date;

    public static void main(String[] args) {
        //results will be saved to "/data/wh.tb.crxyspider/" in json format
        OOSpider.create(Site.me(), new JsonFilePageModelPipeline("/data/wh.tb.crxyspider/"), wh.tb.crxyspider.example.OschinaBlog.class)
                .addUrl("http://my.oschina.net/flashsword/blog").run();
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public List<String> getTags() {
        return tags;
    }

    public Date getDate() {
        return date;
    }

}
