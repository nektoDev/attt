package ru.nektodev.attt.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.nektodev.attt.model.Torrent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class TrackerParser {

    public String getMagnetFromUrl(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        Elements elements = doc.select("a");
        elements.attr("href");

        for (Element element : elements) {
            String magnet = element.attr("href");
            if (isMagnet(element)) {
                return magnet;
            }
        }

        return null;
    }

    private boolean isMagnet(Element element) {
        return element.hasAttr("href") && element.attr("href").startsWith("magnet:");
    }
}
