import java.util.ArrayList;
import java.util.Arrays;

public class Constants {
    public final static int maxPagesToFetch = 10;
    public final static int maxDepthToFetch =0;
    public final static String crawlStorageFolder = "data/crawl";
    public final static int numberOfCrawlers = 1;
    public final static ArrayList<String> listOfAllSources = new ArrayList<String>(
            Arrays.asList("https://en.wikipedia.org/wiki/2015%E2%80%9316_Premier_League",
                    "https://en.wikipedia.org/wiki/Premier_League",
                    "https://en.wikipedia.org/wiki/1992%E2%80%9393_FA_Premier_League",
                    "https://en.wikipedia.org/wiki/Arsenal_F.C.",
                    "https://en.wikipedia.org/wiki/2012%E2%80%9313_Premier_League",
                    "https://en.wikipedia.org/wiki/2017%E2%80%9318_Premier_League",
                    "https://en.wikipedia.org/wiki/2013%E2%80%9314_Premier_League",
                    "https://en.wikipedia.org/wiki/Manchester_United_F.C.",
                    "https://en.wikipedia.org/wiki/Liverpool_F.C.",
                    "https://en.wikipedia.org/wiki/Paul_Pogba")

    );

    //[].,%! punctuation plus [].

}
