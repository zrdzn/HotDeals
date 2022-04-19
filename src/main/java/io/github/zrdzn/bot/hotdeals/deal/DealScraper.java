/*
 * Copyright (c) 2022 zrdzn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.zrdzn.bot.hotdeals.deal;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.Optional;

public class DealScraper {

    public Optional<Deal> scrape(DealSite site) throws IOException {
        String url;
        String name;
        double originalPrice;
        double discountedPrice;

        Document document = Jsoup.connect(site.getUrl()).get();

        if (site == DealSite.XKom) {
            url = document.select("link[rel='canonical']").attr("href");

            String nameRaw = document.select("title").text();
            name = nameRaw.substring(15, nameRaw.length() - 11);

            Element originalPriceRaw = document.select("span.kqfbmY").first();
            originalPrice = this.parsePrice(originalPriceRaw.text());

            discountedPrice = this.parsePrice(originalPriceRaw.nextElementSibling().text());

            return Optional.of(new XKomDeal(url, name, originalPrice, discountedPrice));
        }

        return Optional.empty();
    }

    private double parsePrice(String priceRaw) {
        return Double.parseDouble(priceRaw.substring(0, priceRaw.length() - 2).replace(',', '.'));
    }

}
