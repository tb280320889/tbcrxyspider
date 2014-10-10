package wh.tb.crxyspider;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author 280320889@qq.com
 */
public class ResultItemsTest {

    @Test
    public void testOrderOfEntries() throws Exception {
        ResultItems resultItems = new ResultItems();
        resultItems.put("a", "a");
        resultItems.put("b", "b");
        resultItems.put("c", "c");
        assertThat(resultItems.getAll().keySet()).containsExactly("a","b","c");

    }
}
