package wh.tb.crxyspider.example;

import wh.tb.crxyspider.Site;
import wh.tb.crxyspider.model.OOSpider;
import wh.tb.crxyspider.model.annotation.ExtractBy;

import java.util.ArrayList;
import java.util.List;

/**
 * @since 1.0
 * @author 280320889@qq.com
 */
public class BaiduBaike{

    @ExtractBy("//h1[@class=title]/div[@class=lemmaTitleH1]/text()")
    private String name;

    @ExtractBy("//div[@id='lemmaContent-0']//div[@class='para']/allText()")
    private String description;

    @Override
    public String toString() {
        return "BaiduBaike{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public static void main(String[] args) {
        OOSpider ooSpider = OOSpider.create(Site.me().setSleepTime(0), wh.tb.crxyspider.example.BaiduBaike.class);
        //single download
        String urlTemplate = "http://baike.baidu.com/search/word?word=%s&pic=1&sug=1&enc=utf8";
        wh.tb.crxyspider.example.BaiduBaike baike = ooSpider.<wh.tb.crxyspider.example.BaiduBaike>get("http://baike.baidu.com/search/word?word=httpclient&pic=1&sug=1&enc=utf8");
        System.out.println(baike);

        //multidownload
        List<String> list = new ArrayList<String>();
        list.add(String.format(urlTemplate,"风力发电"));
        list.add(String.format(urlTemplate,"太阳能"));
        list.add(String.format(urlTemplate,"地热发电"));
        list.add(String.format(urlTemplate,"地热发电"));
        List<wh.tb.crxyspider.example.BaiduBaike> resultItemses = ooSpider.<wh.tb.crxyspider.example.BaiduBaike>getAll(list);
        for (wh.tb.crxyspider.example.BaiduBaike resultItemse : resultItemses) {
            System.out.println(resultItemse);
        }
        ooSpider.close();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
