package wh.tb.crxyspider.model.formatter;

/**
 * @author 280320889@qq.com
 */
public interface ObjectFormatter<T> {

    T format(String raw) throws Exception;

    Class<T> clazz();

    void initParam(String[] extra);

}
