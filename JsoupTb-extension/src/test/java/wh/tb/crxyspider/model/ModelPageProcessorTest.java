package wh.tb.crxyspider.model;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import wh.tb.crxyspider.Page;
import wh.tb.crxyspider.Request;
import wh.tb.crxyspider.model.annotation.ExtractBy;
import wh.tb.crxyspider.model.annotation.TargetUrl;
import wh.tb.crxyspider.selector.PlainText;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author 280320889@qq.com
 */
public class ModelPageProcessorTest {

    @TargetUrl("http://codecraft.us/foo")
    public static class ModelFoo {

        @ExtractBy(value = "//div/@foo", notNull = true)
        private String foo;

    }

    @TargetUrl("http://codecraft.us/bar")
    public static class ModelBar {

        @ExtractBy(value = "//div/@bar", notNull = true)
        private String bar;

    }

    @Test
    public void testMultiModel_should_not_skip_when_match() throws Exception {
        Page page = new Page();
        page.setRawText("<div foo='foo'></div>");
        page.setRequest(new Request("http://codecraft.us/foo"));
        page.setUrl(PlainText.create("http://codecraft.us/foo"));
        wh.tb.crxyspider.model.ModelPageProcessor modelPageProcessor = wh.tb.crxyspider.model.ModelPageProcessor.create(null, ModelFoo.class, ModelBar.class);
        modelPageProcessor.process(page);
        assertThat(page.getResultItems().isSkip()).isFalse();
    }

    @Test
    public void testExtractLinks() throws Exception {
        wh.tb.crxyspider.model.ModelPageProcessor modelPageProcessor = wh.tb.crxyspider.model.ModelPageProcessor.create(null, MockModel.class);
        Page page = getMockPage();
        modelPageProcessor.process(page);
        assertThat(page.getTargetRequests()).containsExactly(new Request("http://wh.tb.crxyspider.io/list/1"), new Request("http://wh.tb.crxyspider.io/list/2"), new Request("http://wh.tb.crxyspider.io/post/1"), new Request("http://wh.tb.crxyspider.io/post/2"));

    }

    private Page getMockPage() throws IOException {
        Page page = new Page();
        page.setRawText(IOUtils.toString(getClass().getClassLoader().getResourceAsStream("html/mock-wh.tb.crxyspider.html")));
        page.setRequest(new Request("http://wh.tb.crxyspider.io/list/0"));
        page.setUrl(new PlainText("http://wh.tb.crxyspider.io/list/0"));
        return page;
    }
}
