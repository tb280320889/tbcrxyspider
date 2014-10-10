package wh.tb.crxyspider;

import org.junit.Ignore;
import org.junit.Test;
import wh.tb.crxyspider.downloader.Downloader;
import wh.tb.crxyspider.pipeline.Pipeline;
import wh.tb.crxyspider.processor.PageProcessor;
import wh.tb.crxyspider.processor.SimplePageProcessor;
import wh.tb.crxyspider.scheduler.Scheduler;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 280320889@qq.com
 */
public class SpiderTest {

    @Ignore("long time")
    @Test
    public void testStartAndStop() throws InterruptedException {
        wh.tb.crxyspider.Spider spider = wh.tb.crxyspider.Spider.create(new SimplePageProcessor("http://www.oschina.net/", "http://www.oschina.net/*")).addPipeline(new Pipeline() {
            @Override
            public void process(ResultItems resultItems, wh.tb.crxyspider.Task task) {
                System.out.println(1);
            }
        }).thread(1);
        spider.start();
        Thread.sleep(10000);
        spider.stop();
        Thread.sleep(10000);
        spider.start();
        Thread.sleep(10000);
    }

    @Ignore("long time")
    @Test
    public void testWaitAndNotify() throws InterruptedException {
        for (int i = 0; i < 10000; i++) {
            System.out.println("round " + i);
            testRound();
        }
    }

    private void testRound() {
        wh.tb.crxyspider.Spider spider = Spider.create(new PageProcessor() {

			private AtomicInteger count = new AtomicInteger();

			@Override
			public void process(wh.tb.crxyspider.Page page) {
				page.setSkip(true);
			}

			@Override
			public wh.tb.crxyspider.Site getSite() {
				return Site.me().setSleepTime(0);
			}
		}).setDownloader(new Downloader() {
            @Override
            public wh.tb.crxyspider.Page download(wh.tb.crxyspider.Request request, wh.tb.crxyspider.Task task) {
                return new Page().setRawText("");
            }

            @Override
            public void setThread(int threadNum) {

            }
        }).setScheduler(new Scheduler() {

            private AtomicInteger count = new AtomicInteger();

            private Random random = new Random();

            @Override
            public void push(wh.tb.crxyspider.Request request, wh.tb.crxyspider.Task task) {

            }

            @Override
            public synchronized wh.tb.crxyspider.Request poll(Task task) {
                if (count.incrementAndGet() > 1000) {
                    return null;
                }
                if (random.nextInt(100)>90){
                    return null;
                }
                return new Request("test");
            }
        }).thread(10);
        spider.run();
    }
}
