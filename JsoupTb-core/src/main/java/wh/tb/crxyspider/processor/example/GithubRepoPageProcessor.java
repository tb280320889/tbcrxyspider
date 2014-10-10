package wh.tb.crxyspider.processor.example;

import wh.tb.crxyspider.Page;
import wh.tb.crxyspider.Site;
import wh.tb.crxyspider.Spider;
import wh.tb.crxyspider.processor.PageProcessor;

/**
 * @author 280320889@qq.com <br>
 * @since 1.0
 */
public class GithubRepoPageProcessor implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(0);

    @Override
    public void process(Page page) {
        page.addTargetRequests(page.getHtml().links().regex("(https://github\\.com/\\w+/\\w+)").all());
        page.addTargetRequests(page.getHtml().links().regex("(https://github\\.com/\\w+)").all());
        page.putField("author", page.getUrl().regex("https://github\\.com/(\\w+)/.*").toString());
        page.putField("name", page.getHtml().xpath("//h1[@class='entry-title public']/strong/a/text()").toString());
        if (page.getResultItems().get("name")==null){
            //skip this page
            page.setSkip(true);
        }
        page.putField("readme", page.getHtml().xpath("//div[@id='readme']/tidyText()"));
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new wh.tb.crxyspider.processor.example.GithubRepoPageProcessor()).addUrl("https://github.com/code4craft").thread(5).run();
    }
}
