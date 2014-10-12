import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;

/**
 * IntelliJ IDEA
 * Created by 280320889@qq.com
 *
 */

public class JsoupTest {
	public static void main(String[] args) throws IOException {
		File input = new File("JsoupTb-extension\\src\\test\\resources\\HTMLS\\tbtest.html");
		Document doc = Jsoup.parse(input, "gb2312");
		Elements links = doc.select("dt");
			for( Element link : links){
				String linkText = link.text();
				System.out.println(linkText);
		}
	}
}
