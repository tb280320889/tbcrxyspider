package wh.tb.crxyspider.example;

import org.apache.log4j.Logger;
import wh.tb.crxyspider.*;
import wh.tb.crxyspider.handler.CompositePageProcessor;
import wh.tb.crxyspider.handler.CompositePipeline;
import wh.tb.crxyspider.handler.PatternProcessor;
import wh.tb.crxyspider.handler.RequestMatcher;

/**
 * Created with IntelliJ IDEA.
 */
public class PatternProcessorExample {

    private static Logger log = Logger.getLogger(wh.tb.crxyspider.example.PatternProcessorExample.class);

    public static void main(String... args) {

        // define a patternProcessor which handles only "http://item.jd.com/.*"
        PatternProcessor githubRepoProcessor = new PatternProcessor("https://github\\.com/[\\w\\-]+/[\\w\\-]+") {

            @Override
            public RequestMatcher.MatchOther processPage(Page page) {
                page.putField("reponame", page.getHtml().xpath("//h1[@class='entry-title public']/strong/a/text()").toString());
                return RequestMatcher.MatchOther.YES;
            }

            @Override
            public RequestMatcher.MatchOther processResult(ResultItems resultItems, Task task) {
                log.info("Extracting from repo" + resultItems.getRequest());
                System.out.println("Repo name: "+resultItems.get("reponame"));
                return RequestMatcher.MatchOther.YES;
            }
        };

        PatternProcessor githubUserProcessor = new PatternProcessor("https://github\\.com/[\\w\\-]+") {

            @Override
            public RequestMatcher.MatchOther processPage(Page page) {
                log.info("Extracting from " + page.getUrl());
                page.addTargetRequests(page.getHtml().links().regex("https://github\\.com/[\\w\\-]+/[\\w\\-]+").all());
                page.addTargetRequests(page.getHtml().links().regex("https://github\\.com/[\\w\\-]+").all());
                page.putField("username", page.getHtml().xpath("//span[@class='vcard-fullname']/text()").toString());
                return RequestMatcher.MatchOther.YES;
            }

            @Override
            public RequestMatcher.MatchOther processResult(ResultItems resultItems, Task task) {
                System.out.println("User name: "+resultItems.get("username"));
                return RequestMatcher.MatchOther.YES;
            }
        };

        CompositePageProcessor pageProcessor = new CompositePageProcessor(Site.me().setDomain("github.com").setRetryTimes(3));
        CompositePipeline pipeline = new CompositePipeline();

        pageProcessor.setSubPageProcessors(githubRepoProcessor, githubUserProcessor);
        pipeline.setSubPipeline(githubRepoProcessor, githubUserProcessor);

        Spider.create(pageProcessor).addUrl("https://github.com/tb280320889").thread(5).addPipeline(pipeline).runAsync();
    }

}
