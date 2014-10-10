package wh.tb.crxyspider.selector;



import java.util.ArrayList;
import java.util.List;

/**
 * All extractors will do extracting separately, <br>
 * and the results of extractors will combined as the final result.
 * @author 280320889@qq.com <br>
 * @since 1.0
 */
public class OrSelector implements wh.tb.crxyspider.selector.Selector {

    private List<wh.tb.crxyspider.selector.Selector> selectors = new ArrayList<>();

    public OrSelector(wh.tb.crxyspider.selector.Selector... selectors) {
        for (wh.tb.crxyspider.selector.Selector selector : selectors) {
            this.selectors.add(selector);
        }
    }

    public OrSelector(List<wh.tb.crxyspider.selector.Selector> selectors) {
        this.selectors = selectors;
    }

    @Override
    public String select(String text) {
        for (wh.tb.crxyspider.selector.Selector selector : selectors) {
            String result = selector.select(text);
            if (result != null) {
                return result;
            }
        }
        return null;
    }

    @Override
    public List<String> selectList(String text) {
        List<String> results = new ArrayList<>();
        for (Selector selector : selectors) {
            List<String> strings = selector.selectList(text);
            results.addAll(strings);
        }
        return results;
    }
}
