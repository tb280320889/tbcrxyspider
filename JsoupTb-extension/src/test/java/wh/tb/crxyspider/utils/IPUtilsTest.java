package wh.tb.crxyspider.utils;

import org.junit.Test;

/**
 * @author 280320889@qq.com
 */
public class IPUtilsTest {

    @Test
    public void testGetFirstNoLoopbackIPAddresses() throws Exception {
        System.out.println(IPUtils.getFirstNoLoopbackIPAddresses());
    }
}
