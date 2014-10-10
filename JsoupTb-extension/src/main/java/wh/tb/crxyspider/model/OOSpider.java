package wh.tb.crxyspider.model;

import wh.tb.crxyspider.Site;
import wh.tb.crxyspider.Spider;
import wh.tb.crxyspider.pipeline.CollectorPipeline;
import wh.tb.crxyspider.pipeline.PageModelPipeline;
import wh.tb.crxyspider.processor.PageProcessor;

import java.util.ArrayList;
import java.util.List;

/**
 * The spider for page model extractor.<br>
 * In wh.tb.crxyspider, we call a POJO containing extract result as "page model". <br>
 * You can customize a crawler by write a page model with annotations. <br>
 * Such as:
 * <pre>
 * {@literal @}TargetUrl("http://my.oschina.net/flashsword/blog/\\d+")
 *  public class OschinaBlog{
 *
 *      {@literal @}ExtractBy("//title")
 *      private String title;
 *
 *      {@literal @}ExtractBy(value = "div.BlogContent",type = ExtractBy.Type.Css)
 *      private String content;
 *
 *      {@literal @}ExtractBy(value = "//div[@class='BlogTags']/a/text()", multi = true)
 *      private List<String> tags;
 * }
 * </pre>
 * And start the spider by:
 * <pre>
 *   OOSpider.create(Site.me().addStartUrl("http://my.oschina.net/flashsword/blog")
 *        ,new JsonFilePageModelPipeline(), OschinaBlog.class).run();
 * }
 * </pre>
 *
 * @author 280320889@qq.com <br>
 * @since 1.0
 */
public class OOSpider<T> extends Spider {

    private wh.tb.crxyspider.model.ModelPageProcessor modelPageProcessor;

    private wh.tb.crxyspider.model.ModelPipeline modelPipeline;

    private PageModelPipeline pageModelPipeline;

    private List<Class> pageModelClasses = new ArrayList<Class>();

    protected OOSpider(wh.tb.crxyspider.model.ModelPageProcessor modelPageProcessor) {
        super(modelPageProcessor);
        this.modelPageProcessor = modelPageProcessor;
    }

    public OOSpider(PageProcessor pageProcessor) {
        super(pageProcessor);
    }

    /**
     * create a spider
     *
     * @param site
     * @param pageModelPipeline
     * @param pageModels
     */
    public OOSpider(Site site, PageModelPipeline pageModelPipeline, Class... pageModels) {
        this(wh.tb.crxyspider.model.ModelPageProcessor.create(site, pageModels));
        this.modelPipeline = new wh.tb.crxyspider.model.ModelPipeline();
        super.addPipeline(modelPipeline);
        for (Class pageModel : pageModels) {
            if (pageModelPipeline != null) {
                this.modelPipeline.put(pageModel, pageModelPipeline);
            }
            pageModelClasses.add(pageModel);
        }
    }

    @Override
    protected CollectorPipeline getCollectorPipeline() {
        return new wh.tb.crxyspider.model.PageModelCollectorPipeline<T>(pageModelClasses.get(0));
    }

    public static wh.tb.crxyspider.model.OOSpider create(Site site, Class... pageModels) {
        return new wh.tb.crxyspider.model.OOSpider(site, null, pageModels);
    }

    public static wh.tb.crxyspider.model.OOSpider create(Site site, PageModelPipeline pageModelPipeline, Class... pageModels) {
        return new wh.tb.crxyspider.model.OOSpider(site, pageModelPipeline, pageModels);
    }

    public wh.tb.crxyspider.model.OOSpider addPageModel(PageModelPipeline pageModelPipeline, Class... pageModels) {
        for (Class pageModel : pageModels) {
            modelPageProcessor.addPageModel(pageModel);
            modelPipeline.put(pageModel, pageModelPipeline);
        }
        return this;
    }

}
