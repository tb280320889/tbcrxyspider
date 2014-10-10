package wh.tb.crxyspider.model;

import wh.tb.crxyspider.Site;
import wh.tb.crxyspider.model.annotation.ExtractBy;
import wh.tb.crxyspider.model.annotation.HelpUrl;
import wh.tb.crxyspider.model.annotation.TargetUrl;

/**
 * @author 280320889@qq.com <br>
 * @since 1.0
 */
@TargetUrl("https://github.com/\\w+/\\w+")
@HelpUrl({"https://github.com/\\w+\\?tab=repositories", "https://github.com/\\w+", "https://github.com/explore/*"})
public class GithubRepo extends BaseRepo{

    @ExtractBy("//ul[@class='pagehead-actions']/li[2]//a[@class='social-count']/text()")
    private int fork;

    public static void main(String[] args) {
        OOSpider.create(Site.me().setSleepTime(100)
				, new ConsolePageModelPipeline(), GithubRepo.class)
                .addUrl("https://github.com/code4craft").thread(10).run();
    }

    public int getStar() {
        return star;
    }

    public int getFork() {
        return fork;
    }
}
