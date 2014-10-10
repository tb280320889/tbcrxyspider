package wh.tb.crxyspider.monitor;

import org.junit.Test;
import wh.tb.crxyspider.Spider;
import wh.tb.crxyspider.processor.example.GithubRepoPageProcessor;
import wh.tb.crxyspider.processor.example.OschinaBlogPageProcessor;

/**
 * @author 280320889@qq.com
 * @since 1.0
 */
public class SpiderMonitorTest {

    @Test
    public void testIherit() throws Exception {
        wh.tb.crxyspider.monitor.SpiderMonitor spiderMonitor = new SpiderMonitor(){
            @Override
            protected SpiderStatusMXBean getSpiderStatusMBean(Spider spider, MonitorSpiderListener monitorSpiderListener) {
                return new CustomSpiderStatus(spider, monitorSpiderListener);
            }
        };

        Spider oschinaSpider = Spider.create(new OschinaBlogPageProcessor())
                .addUrl("http://my.oschina.net/flashsword/blog").thread(2);
        Spider githubSpider = Spider.create(new GithubRepoPageProcessor())
                .addUrl("https://github.com/code4craft");

        spiderMonitor.register(oschinaSpider, githubSpider);

    }
}
