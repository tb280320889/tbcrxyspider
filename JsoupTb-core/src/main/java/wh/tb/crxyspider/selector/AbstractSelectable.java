package wh.tb.crxyspider.selector;

import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 280320889@qq.com
 * @since 1.0
 */
public abstract class AbstractSelectable implements wh.tb.crxyspider.selector.Selectable {

    protected abstract List<String> getSourceTexts();

    @Override
    public wh.tb.crxyspider.selector.Selectable css(String selector) {
        return $(selector);
    }

    @Override
    public wh.tb.crxyspider.selector.Selectable css(String selector, String attrName) {
        return $(selector, attrName);
    }

    protected wh.tb.crxyspider.selector.Selectable select(Selector selector, List<String> strings) {
        List<String> results = new ArrayList<String>();
        for (String string : strings) {
            String result = selector.select(string);
            if (result != null) {
                results.add(result);
            }
        }
        return new PlainText(results);
    }

    protected wh.tb.crxyspider.selector.Selectable selectList(Selector selector, List<String> strings) {
        List<String> results = new ArrayList<>();
        for (String string : strings) {
            List<String> result = selector.selectList(string);
            results.addAll(result);
        }
        return new PlainText(results);
    }

    @Override
    public List<String> all() {
        return getSourceTexts();
    }

    @Override
    public wh.tb.crxyspider.selector.Selectable jsonPath(String jsonPath) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String get() {
        if (CollectionUtils.isNotEmpty(all())) {
            return all().get(0);
        } else {
            return null;
        }
    }

    @Override
    public wh.tb.crxyspider.selector.Selectable select(Selector selector) {
        return select(selector, getSourceTexts());
    }

    @Override
    public Selectable selectList(Selector selector) {
        return selectList(selector, getSourceTexts());
    }

    @Override
    public wh.tb.crxyspider.selector.Selectable regex(String regex) {
        RegexSelector regexSelector = Selectors.regex(regex);
        return selectList(regexSelector, getSourceTexts());
    }

    @Override
    public Selectable regex(String regex, int group) {
        RegexSelector regexSelector = Selectors.regex(regex, group);
        return selectList(regexSelector, getSourceTexts());
    }

    @Override
    public Selectable replace(String regex, String replacement) {
        ReplaceSelector replaceSelector = new ReplaceSelector(regex,replacement);
        return select(replaceSelector, getSourceTexts());
    }

    public String getFirstSourceText() {
        if (getSourceTexts() != null && getSourceTexts().size() > 0) {
            return getSourceTexts().get(0);
        }
        return null;
    }

    @Override
    public String toString() {
        return get();
    }

    @Override
    public boolean match() {
        return getSourceTexts() != null && getSourceTexts().size() > 0;
    }
}
