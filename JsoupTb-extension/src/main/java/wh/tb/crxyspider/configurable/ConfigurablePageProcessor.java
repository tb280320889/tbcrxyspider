package wh.tb.crxyspider.configurable;


import wh.tb.crxyspider.Page;
import wh.tb.crxyspider.Site;
import wh.tb.crxyspider.processor.PageProcessor;
import wh.tb.crxyspider.utils.Experimental;

import java.util.List;

/**
 * @author 280320889@qq.com <br>
 */
@Experimental
public class ConfigurablePageProcessor implements PageProcessor {

    private Site site;

    private List<wh.tb.crxyspider.configurable.ExtractRule> extractRules;

    public ConfigurablePageProcessor(Site site, List<wh.tb.crxyspider.configurable.ExtractRule> extractRules) {
        this.site = site;
        this.extractRules = extractRules;
    }

    @Override
    public void process(Page page) {
        for (ExtractRule extractRule : extractRules) {
            if (extractRule.isMulti()) {
                List<String> results = page.getHtml().selectDocumentForList(extractRule.getSelector());
                if (extractRule.isNotNull() && results.size() == 0) {
                    page.setSkip(true);
                } else {
                    page.getResultItems().put(extractRule.getFieldName(), results);
                }
            } else {
                String result = page.getHtml().selectDocument(extractRule.getSelector());
                if (extractRule.isNotNull() && result == null) {
                    page.setSkip(true);
                } else {
                    page.getResultItems().put(extractRule.getFieldName(), result);
                }
            }
        }
    }

    @Override
    public Site getSite() {
        return site;
    }

}
