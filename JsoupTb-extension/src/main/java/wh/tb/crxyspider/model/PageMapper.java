package wh.tb.crxyspider.model;

import wh.tb.crxyspider.Page;

import java.util.List;

/**
 * @author 280320889@qq.com
 * @since 1.0
 */
public class PageMapper<T> {

    private Class<T> clazz;

    private PageModelExtractor pageModelExtractor;

    public PageMapper(Class<T> clazz) {
        this.clazz = clazz;
        this.pageModelExtractor = PageModelExtractor.create(clazz);
    }

    public T get(Page page) {
        return (T) pageModelExtractor.process(page);
    }

    public List<T> getAll(Page page) {
        return (List<T>) pageModelExtractor.process(page);
    }
}
