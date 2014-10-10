package wh.tb.crxyspider;

import org.junit.Test;
import wh.tb.crxyspider.selector.Html;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author 280320889@qq.com <br>
 */
public class HtmlTest {

    @Test
    public void testRegexSelector() {
        Html selectable = new Html("aaaaaaab");
		assertThat(selectable.regex("(a+b)").replace("aa(a)", "$1bb").toString()).isEqualTo("abbabbab");
    }

	@Test
	public void testDisableJsoupHtmlEntityEscape() throws Exception {
		Html.DISABLE_HTML_ENTITY_ESCAPE = true;
		Html html = new Html("aaaaaaa&b");
		assertThat(html.regex("(aaaaaaa&b)").toString()).isEqualTo("aaaaaaa&b");
	}

	@Test
	public void testEnableJsoupHtmlEntityEscape() throws Exception {
		Html.DISABLE_HTML_ENTITY_ESCAPE = false;
		Html html = new Html("aaaaaaa&b");
		assertThat(html.regex("(aaaaaaa&amp;b)").toString()).isEqualTo("aaaaaaa&amp;b");
	}
}
