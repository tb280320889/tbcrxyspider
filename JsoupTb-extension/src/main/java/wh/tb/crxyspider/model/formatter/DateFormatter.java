package wh.tb.crxyspider.model.formatter;

import org.apache.commons.lang3.time.DateUtils;

import java.util.Date;

/**
 * @author 280320889@qq.com
 * @since 1.0
 */
public class DateFormatter implements ObjectFormatter<Date> {

    public static final String[] DEFAULT_PATTERN = new String[]{"yyyy-MM-dd HH:mm"};
    private String[] datePatterns = DEFAULT_PATTERN;

    @Override
    public Date format(String raw) throws Exception {
        return DateUtils.parseDate(raw, datePatterns);
    }

    @Override
    public Class<Date> clazz() {
        return Date.class;
    }

    @Override
    public void initParam(String[] extra) {
        if (extra != null && !(extra.length == 1 && extra[0].length() == 0)) {
            datePatterns = extra;
        }
    }
}
