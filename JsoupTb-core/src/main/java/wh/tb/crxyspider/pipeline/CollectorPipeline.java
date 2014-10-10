package wh.tb.crxyspider.pipeline;

import java.util.List;

/**
 * IntelliJ IDEA
 *
 * Pipeline that can collect and store results. <br>
 * Used for {@link us.codecraft.webmagic.Spider#getAll(java.util.Collection)}
 *
 * @author 280320889@qq.com <br>
 * @since 1.0
 */

public interface CollectorPipeline<T> extends Pipeline {

	/**
	 * Get all results collected.
	 *
	 * @return collected results
	 */
	public List<T> getCollected();
}
