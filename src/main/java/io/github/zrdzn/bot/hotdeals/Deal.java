package io.github.zrdzn.bot.hotdeals;

public interface Deal {

    String getUrl();

    String getName();

    double getOriginalPrice();

    double getDiscountedPrice();

}
