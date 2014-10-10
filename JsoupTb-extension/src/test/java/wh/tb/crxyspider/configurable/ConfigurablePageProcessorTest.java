package wh.tb.crxyspider.configurable;

import org.junit.Test;
import wh.tb.crxyspider.ResultItems;
import wh.tb.crxyspider.Site;
import wh.tb.crxyspider.Spider;
import wh.tb.crxyspider.downloader.MockGithubDownloader;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author 280320889@qq.com
 */
public class ConfigurablePageProcessorTest {

    @Test
    public void test() throws Exception {
        List<wh.tb.crxyspider.configurable.ExtractRule> extractRules = new ArrayList<wh.tb.crxyspider.configurable.ExtractRule>();
        wh.tb.crxyspider.configurable.ExtractRule extractRule = new wh.tb.crxyspider.configurable.ExtractRule();
        extractRule.setExpressionType(ExpressionType.XPath);
        extractRule.setExpressionValue("//title");
        extractRule.setFieldName("title");
        extractRules.add(extractRule);
        extractRule = new ExtractRule();
        extractRule.setExpressionType(ExpressionType.XPath);
        extractRule.setExpressionValue("//ul[@class='pagehead-actions']/li[1]//a[@class='social-count js-social-count']/text()");
        extractRule.setFieldName("star");
        extractRules.add(extractRule);
        ResultItems resultItems = Spider.create(new ConfigurablePageProcessor(Site.me(), extractRules))
                .setDownloader(new MockGithubDownloader()).get("https://github.com/code4craft/wh.tb.crxyspider");
        assertThat(resultItems.getAll()).containsEntry("title", "<title>code4craft/wh.tb.crxyspider · GitHub</title>");
        assertThat(resultItems.getAll()).containsEntry("star", " 86 ");

    }
}
