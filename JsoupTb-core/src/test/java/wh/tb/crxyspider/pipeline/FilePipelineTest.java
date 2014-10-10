package wh.tb.crxyspider.pipeline;

import org.junit.BeforeClass;
import org.junit.Test;
import wh.tb.crxyspider.Request;
import wh.tb.crxyspider.ResultItems;
import wh.tb.crxyspider.Site;
import wh.tb.crxyspider.Task;

import java.util.UUID;

/**        IntelliJ IDEA
 *	@author 280320889@qq.com <br>
 */

public class FilePipelineTest {

    private static ResultItems resultItems;
    private static Task task;

    @BeforeClass
    public static void before() {
        resultItems = new ResultItems();
        resultItems.put("content", "wh.tb.crxyspider 爬虫工具");
        Request request = new Request("http://www.baidu.com");
        resultItems.setRequest(request);

        task = new Task() {
            @Override
            public String getUUID() {
                return UUID.randomUUID().toString();
            }

            @Override
            public Site getSite() {
                return null;
            }
        };
    }
    @Test
    public void testProcess() {
        FilePipeline filePipeline = new FilePipeline();
        filePipeline.process(resultItems, task);
    }
}
