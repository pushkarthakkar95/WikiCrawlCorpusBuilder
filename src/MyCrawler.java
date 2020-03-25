import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class MyCrawler extends WebCrawler {
    private  ArrayList listOfAllDocsCrawled = new ArrayList<String>();
    private ArrayList<String> textFromSources = new ArrayList<String>();


    /**
     * This method receives two parameters. The first parameter is the page
     * in which we have discovered this new url and the second parameter is
     * the new url.
     */
    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        String href = url.getURL().toLowerCase();
        return Constants.listOfAllSources.contains(href);
    }

    /**
     * This function is called when a page is fetched and ready
     * to be processed by your program.
     */
    @Override
    public void visit(Page page) {
        String url = page.getWebURL().getURL();
        System.out.println("URL: " + url);
        listOfAllDocsCrawled.add(url);
        if (page.getParseData() instanceof HtmlParseData) {
            HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
            String html = htmlParseData.getHtml();
            textFromSources.addAll(parseContentParagraphsWiki(html));
        }
        processAfterParsing();
    }

    /**
     *
     * @param html giving the html content to parse and store on doc
     * @return list of paragraph strings/text to later recreate the content if needed
     */
    private List<String> parseContentParagraphsWiki(String html){
        List<String> result = new ArrayList<>();
        Document doc = Jsoup.parse(html);
        Element element = doc.getElementById("bodyContent")
                .getElementById("mw-content-text")
                .getElementsByClass("mw-parser-output").get(0);
        List<Element> elementsOfAllParagraphs = element.getElementsByTag("p");
        for (Element paragraphs : elementsOfAllParagraphs){
            result.add(paragraphs.text().replaceAll("]"," ")
                                        .replaceAll("\\["," ")
                                        .replaceAll("%","")
            .replaceAll("\\.","")
            .replaceAll(",",""));
        }
        return result;
    }

    /**
     * This method checks if all parsing is done for all sources and creates a file
     * with all text from source wiki pages
     */
    private void processAfterParsing(){
        if(listOfAllDocsCrawled.containsAll(Constants.listOfAllSources)){   //Ensures that file is written to after all text has been extracted
            Path file = Paths.get("output_created.txt");
            try {
                Files.write(file, textFromSources, StandardCharsets.UTF_8);
            } catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                System. exit(0);
            }
        }
    }
}
