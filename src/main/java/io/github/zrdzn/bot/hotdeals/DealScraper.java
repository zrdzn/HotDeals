package io.github.zrdzn.bot.hotdeals;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;

import java.io.IOException;
import java.util.Optional;

public class DealScraper {

    private final Logger logger;

    public DealScraper(Logger logger) {
        this.logger = logger;
    }

    public Optional<Deal> scrape(DealSite site) throws IOException {
        String url;
        String name;
        double originalPrice;
        double discountedPrice;

        Document document = Jsoup.connect(site.getUrl()).get();

        if (site == DealSite.XKom) {
            url = document.select("link[rel='canonical']").attr("href");
            name = document.select("h1.PFbyR").text();
            originalPrice = this.parsePrice(document.select("span.kqfbmY").text());
            discountedPrice = this.parsePrice(document.select("span.iiwFoN").text());

            return Optional.of(new XKomDeal(url, name, originalPrice, discountedPrice));
        }

        return Optional.empty();
    }

    private double parsePrice(String priceRaw) {
        return Double.parseDouble(priceRaw.substring(0, priceRaw.length() - 2).replace(',', '.'));
    }

}
