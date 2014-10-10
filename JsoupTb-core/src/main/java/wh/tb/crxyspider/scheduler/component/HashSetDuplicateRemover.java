package wh.tb.crxyspider.scheduler.component;

import com.google.common.collect.Sets;
import wh.tb.crxyspider.Request;
import wh.tb.crxyspider.Task;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * IntelliJ IDEA
 *
 * @author 280320889@qq.com <br>
 * @since 1.0
 */

public class HashSetDuplicateRemover implements DuplicateRemover {

	private Set<String> urls = Sets.newSetFromMap(new ConcurrentHashMap<String, Boolean>());

	@Override
	public boolean isDuplicate(Request request, Task task) {
		return !urls.add(getUrl(request));
	}

	protected String getUrl(Request request) {
		return request.getUrl();
	}

	@Override
	public void resetDuplicateCheck(Task task) {
		urls.clear();
	}

	@Override
	public int getTotalRequestsCount(Task task) {
		return urls.size();
	}
}
