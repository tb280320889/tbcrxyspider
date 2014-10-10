package wh.tb.crxyspider.scheduler;

import org.apache.http.annotation.ThreadSafe;
import wh.tb.crxyspider.Request;
import wh.tb.crxyspider.Task;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * IntelliJ IDEA
 *
 * Basic Scheduler implementation.<br>
 * Store urls to fetch in LinkedBlockingQueue and remove duplicate urls by HashMap.
 *
 * @author 280320889@qq.com <br>
 * @since 1.0
 */
@ThreadSafe
public class QueueScheduler extends DuplicateRemovedScheduler implements MonitorableScheduler {

	private BlockingQueue<Request> queue = new LinkedBlockingQueue<Request>();

	@Override
	public void pushWhenNoDuplicate(Request request, Task task) {
		queue.add(request);
	}

	@Override
	public synchronized Request poll(Task task) {
		return queue.poll();
	}

	@Override
	public int getLeftRequestsCount(Task task) {
		return queue.size();
	}

	@Override
	public int getTotalRequestsCount(Task task) {
		return getDuplicateRemover().getTotalRequestsCount(task);
	}
}

