package wh.tb.crxyspider.scheduler;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import wh.tb.crxyspider.Request;
import wh.tb.crxyspider.Site;
import wh.tb.crxyspider.Task;

/**
 * @author 280320889@qq.com <br>
 */
public class RedisSchedulerTest {

    private wh.tb.crxyspider.scheduler.RedisScheduler redisScheduler;

    @Before
    public void setUp() {
        redisScheduler = new RedisScheduler("localhost");
    }

    @Ignore("environment depended")
    @Test
    public void test() {
        Task task = new Task() {
            @Override
            public String getUUID() {
                return "1";
            }

            @Override
            public Site getSite() {
                return null;
            }
        };
        Request request = new Request("http://www.ibm.com/developerworks/cn/java/j-javadev2-22/");
        request.putExtra("1","2");
        redisScheduler.push(request, task);
        Request poll = redisScheduler.poll(task);
        System.out.println(poll);

    }
}
