package io.github.zrdzn.bot.hotdeals;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.internal.utils.JDALogger;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;

import javax.security.auth.login.LoginException;
import java.io.IOException;

public class HotDealsBot {

    public static void main(String[] args) throws LoginException {
        if (args.length == 0) {
            throw new IllegalArgumentException("Token was not provided.");
        }

        JDABuilder jdaBuilder = JDABuilder.createDefault(args[0]);

        HotDealsBot app = new HotDealsBot();
        app.run(jdaBuilder);
    }

    public void run(JDABuilder jdaBuilder) throws LoginException {
        BasicConfigurator.configure();
        Logger logger = JDALogger.getLog("DISCORD-BOT");

        jdaBuilder.build();

        DealScraper scraper = new DealScraper(logger);
        try {
            scraper.scrap(DealSite.XKom);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
