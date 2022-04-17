package io.github.zrdzn.bot.hotdeals;

public enum DealSite {

    XKom("https://www.x-kom.pl/goracy_strzal");

    private final String url;

    DealSite(String url) {
        this.url = url;
    }

    public String getUrl() {
        return this.url;
    }

}
