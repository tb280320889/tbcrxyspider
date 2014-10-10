package wh.tb.crxyspider.model;

import org.junit.Test;
import wh.tb.crxyspider.Site;
import wh.tb.crxyspider.Task;
import wh.tb.crxyspider.downloader.MockGithubDownloader;
import wh.tb.crxyspider.pipeline.PageModelPipeline;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author 280320889@qq.com <br>
 */
public class GithubRepoTest {

    @Test
    public void test() {
        OOSpider.create(Site.me().setSleepTime(0)
				, new PageModelPipeline<GithubRepo>() {
			@Override
			public void process(GithubRepo o, Task task) {
				assertThat(o.getStar()).isEqualTo(86);
				assertThat(o.getFork()).isEqualTo(70);
			}
		}, GithubRepo.class).addUrl("https://github.com/code4craft/wh.tb.crxyspider").setDownloader(new MockGithubDownloader()).test("https://github.com/code4craft/wh.tb.crxyspider");
    }
}
