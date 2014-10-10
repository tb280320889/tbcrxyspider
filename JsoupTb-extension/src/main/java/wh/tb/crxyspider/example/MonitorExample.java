package wh.tb.crxyspider.example;

import wh.tb.crxyspider.Spider;
import wh.tb.crxyspider.monitor.SpiderMonitor;
import wh.tb.crxyspider.processor.example.GithubRepoPageProcessor;
import wh.tb.crxyspider.processor.example.OschinaBlogPageProcessor;

/**
 * @author 280320889@qq.com
 * @since 1.0
 */
public class MonitorExample {

    public static void main(String[] args) throws Exception {

        Spider oschinaSpider = Spider.create(new OschinaBlogPageProcessor())
                .addUrl("http://my.oschina.net/flashsword/blog");
        Spider githubSpider = Spider.create(new GithubRepoPageProcessor())
                .addUrl("https://github.com/code4craft");

        SpiderMonitor.instance().register(oschinaSpider);
        SpiderMonitor.instance().register(githubSpider);
        oschinaSpider.start();
        githubSpider.start();
    }
}
