package wh.tb.crxyspider.processor;

import junit.framework.Assert;
import org.junit.Test;
import wh.tb.crxyspider.Page;
import wh.tb.crxyspider.ResultItems;
import wh.tb.crxyspider.Site;
import wh.tb.crxyspider.Task;
import wh.tb.crxyspider.downloader.MockGithubDownloader;
import wh.tb.crxyspider.model.OOSpider;
import wh.tb.crxyspider.pipeline.Pipeline;

/**
 * @author 280320889@qq.com
 */
public class GithubRepoProcessor implements PageProcessor {
    @Override
    public void process(Page page) {
        page.putField("star",page.getHtml().xpath("//ul[@class='pagehead-actions']/li[2]//a[@class='social-count js-social-count']/text()").toString());
        page.putField("fork",page.getHtml().xpath("//ul[@class='pagehead-actions']/li[3]//a[@class='social-count']/text()").toString());
    }

    @Override
    public Site getSite() {
        return Site.me().addStartUrl("https://github.com/code4craft/wh.tb.crxyspider");
    }

    @Test
    public void test() {
        OOSpider.create(new GithubRepoProcessor()).addPipeline(new Pipeline() {
            @Override
            public void process(ResultItems resultItems, Task task) {
                Assert.assertEquals("78", ((String) resultItems.get("star")).trim());
                Assert.assertEquals("65", ((String) resultItems.get("fork")).trim());
            }
        }).setDownloader(new MockGithubDownloader()).test("https://github.com/code4craft/wh.tb.crxyspider");
    }

}
