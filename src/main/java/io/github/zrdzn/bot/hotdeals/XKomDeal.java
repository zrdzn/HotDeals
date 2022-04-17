package io.github.zrdzn.bot.hotdeals;

public class XKomDeal implements Deal {

    private final String url;
    private final String name;
    private final double originalPrice;
    private final double discountedPrice;

    public XKomDeal(String url, String name, double originalPrice, double discountedPrice) {
        this.url = url;
        this.name = name;
        this.originalPrice = originalPrice;
        this.discountedPrice = discountedPrice;
    }

    @Override
    public String getUrl() {
        return this.url;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public double getOriginalPrice() {
        return this.originalPrice;
    }

    @Override
    public double getDiscountedPrice() {
        return this.discountedPrice;
    }

}
