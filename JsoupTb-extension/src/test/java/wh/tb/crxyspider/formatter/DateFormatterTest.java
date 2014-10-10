package wh.tb.crxyspider.formatter;

import org.junit.Test;
import wh.tb.crxyspider.model.formatter.DateFormatter;

import java.util.Date;

/**
 * @author 280320889@qq.com
 */
public class DateFormatterTest {

    @Test
    public void testDateFormatter() throws Exception {
        DateFormatter dateFormatter = new DateFormatter();
        dateFormatter.initParam(new String[]{"yyyy-MM-dd HH:mm"});
        Date format = dateFormatter.format("2013-09-10 22:11");
        System.out.println(format);
    }
}
