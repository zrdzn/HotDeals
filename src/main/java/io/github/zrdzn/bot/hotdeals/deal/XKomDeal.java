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
