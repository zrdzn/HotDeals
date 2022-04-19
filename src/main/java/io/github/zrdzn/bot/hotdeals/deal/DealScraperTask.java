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

import io.github.zrdzn.bot.hotdeals.configuration.Configuration;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.MessageEmbed;
import org.slf4j.Logger;

import java.awt.Color;
import java.io.IOException;
import java.time.Instant;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class DealScraperTask implements Runnable {

    public static final long DEFAULT_PERIOD = 15L * 60L;

    private final Logger logger;
    private final Configuration configuration;
    private final DealScraper dealScraper;

    private ScheduledExecutorService executor;
    private JDA jda;
    private Deal previousDeal;

    public DealScraperTask(Logger logger, Configuration configuration, DealScraper dealScraper) {
        this.logger = logger;
        this.configuration = configuration;
        this.dealScraper = dealScraper;
    }

    public void start(long period) {
        this.executor = Executors.newScheduledThreadPool(1);
        this.executor.scheduleAtFixedRate(this, 10, period == 0L ? DEFAULT_PERIOD : period, TimeUnit.SECONDS);
    }

    public void stop() {
        this.executor.shutdown();
    }

    @Override
    public void run() {
        try {
            MessageChannel channel = this.jda.getTextChannelById(this.configuration.getDealChannelId());
            if (channel == null) {
                this.logger.error("Deal channel is not specified. Use set-channel command to resolve that.");
                return;
            }

            DealSite site = DealSite.XKom;

            Optional<Deal> dealMaybe = this.dealScraper.scrape(site);
            if (dealMaybe.isEmpty()) {
                channel.sendMessage("Specified website is not responding: " + site.getUrl()).queue();
                return;
            }

            Deal deal = dealMaybe.get();

            String url = deal.getUrl();

            if (this.previousDeal != null) {
                if (url.equals(this.previousDeal.getUrl())) {
                    return;
                }
            }

            MessageEmbed embed = new EmbedBuilder()
                .setTitle("New hot deal!")
                .addField("Product name", deal.getName(), false)
                .addField("Original price", this.formatPrice(deal.getOriginalPrice()), true)
                .addField("Discounted price", this.formatPrice(deal.getDiscountedPrice()), true)
                .addField("Discount link", site.getUrl(), false)
                .addField("Original link", deal.getUrl(), false)
                .setThumbnail(deal.getImageUrl())
                .setTimestamp(Instant.now())
                .setColor(Color.CYAN)
                .build();

            channel.sendMessageEmbeds(embed).queue();

            this.previousDeal = deal;
        } catch (IOException exception) {
            this.logger.error("Something went wrong while scraping website.", exception);
        }
    }

    public void setJda(JDA jda) {
        this.jda = jda;
    }

    private String formatPrice(double number) {
        return (number == (long) number ? String.format("%d", (long) number) : String.format("%s", number))
            .concat("z\u0142");
    }

}
