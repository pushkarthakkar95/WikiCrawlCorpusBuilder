import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

public class Controller {
    public static void main(String[] args) throws Exception {

        CrawlConfig config = new CrawlConfig();
        config.setCrawlStorageFolder(Constants.crawlStorageFolder);
        config.setMaxDepthOfCrawling(Constants.maxDepthToFetch);
        config.setMaxPagesToFetch(Constants.maxPagesToFetch);
        /*
         * Instantiate the controller for this crawl.
         */
        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);
        //Adding seeds from source
        for (String source: Constants.listOfAllSources){
            controller.addSeed(source);
        }
        /*
         * Start the crawl. This is a blocking operation, meaning that code
         * will reach the line after this only when crawling is finished.
         */
        controller.start(MyCrawler.class, Constants.numberOfCrawlers);
    }
}
