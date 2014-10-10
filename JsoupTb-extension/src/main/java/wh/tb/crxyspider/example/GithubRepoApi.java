package wh.tb.crxyspider.example;

import wh.tb.crxyspider.Site;
import wh.tb.crxyspider.model.ConsolePageModelPipeline;
import wh.tb.crxyspider.model.HasKey;
import wh.tb.crxyspider.model.OOSpider;
import wh.tb.crxyspider.model.annotation.ExtractBy;
import wh.tb.crxyspider.model.annotation.ExtractByUrl;

import java.util.List;

/**
 * @author 280320889@qq.com <br>
 * @since 1.0
 */
public class GithubRepoApi implements HasKey {

    @ExtractBy(type = ExtractBy.Type.JsonPath, value = "$.name")
    private String name;

    @ExtractBy(type = ExtractBy.Type.JsonPath, value = "$..owner.login")
    private String author;

    @ExtractBy(type = ExtractBy.Type.JsonPath, value = "$.language",multi = true)
    private List<String> language;

    @ExtractBy(type = ExtractBy.Type.JsonPath, value = "$.stargazers_count")
    private int star;

    @ExtractBy(type = ExtractBy.Type.JsonPath, value = "$.homepage")
    private int fork;

    @ExtractByUrl
    private String url;

    public static void main(String[] args) {
        OOSpider.create(Site.me().setSleepTime(100)
				, new ConsolePageModelPipeline(), wh.tb.crxyspider.example.GithubRepoApi.class)
                .addUrl("https://api.github.com/repos/code4craft/wh.tb.crxyspider").run();
    }

    @Override
    public String key() {
        return author + ":" + name;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public List<String> getLanguage() {
        return language;
    }

    public String getUrl() {
        return url;
    }

    public int getStar() {
        return star;
    }

    public int getFork() {
        return fork;
    }
}
