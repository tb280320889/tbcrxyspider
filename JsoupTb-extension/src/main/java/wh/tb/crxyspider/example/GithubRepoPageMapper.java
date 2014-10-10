package wh.tb.crxyspider.example;

import wh.tb.crxyspider.Page;
import wh.tb.crxyspider.Site;
import wh.tb.crxyspider.Spider;
import wh.tb.crxyspider.model.PageMapper;
import wh.tb.crxyspider.processor.PageProcessor;

/**
 * @author 280320889@qq.com <br>
 * @since 1.0
 */
public class GithubRepoPageMapper implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(0);

    private PageMapper<GithubRepo> githubRepoPageMapper = new PageMapper<GithubRepo>(GithubRepo.class);

    @Override
    public void process(Page page) {
        page.addTargetRequests(page.getHtml().links().regex("(https://github\\.com/\\w+/\\w+)").all());
        page.addTargetRequests(page.getHtml().links().regex("(https://github\\.com/\\w+)").all());
        GithubRepo githubRepo = githubRepoPageMapper.get(page);
        if (githubRepo == null) {
            page.setSkip(true);
        } else {
            page.putField("repo", githubRepo);
        }

    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new wh.tb.crxyspider.example.GithubRepoPageMapper()).addUrl("https://github.com/code4craft").thread(5).run();
    }
}