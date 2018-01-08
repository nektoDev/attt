package ru.nektodev.attt.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.nektodev.attt.model.TrackerParserException;

import javax.annotation.Nonnull;
import java.io.IOException;

public class TrackerParser {

    @Nonnull
    public String getMagnetFromUrl(@Nonnull String url) throws IOException, TrackerParserException {
        Document doc = Jsoup.connect(url).get();
        Elements elements = doc.select("a");
        elements.attr("href");

        for (Element element : elements) {
            String magnet = element.attr("href");
            if (isMagnet(element)) {
                return magnet;
            }
        }

        throw new TrackerParserException(String.format("Magnet URL has not been found on URL: %s", url));
    }

    private boolean isMagnet(Element element) {
        return element.hasAttr("href") && element.attr("href").startsWith("magnet:");
    }
}
