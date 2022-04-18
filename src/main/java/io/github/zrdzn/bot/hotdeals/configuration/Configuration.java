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
package io.github.zrdzn.bot.hotdeals.configuration;

public class Configuration {

    private long controllerRoleId;
    private long dealChannelId;

    public Configuration(long controllerRoleId, long dealChannelId) {
        this.controllerRoleId = controllerRoleId;
        this.dealChannelId = dealChannelId;
    }

    public long getControllerRoleId() {
        return this.controllerRoleId;
    }

    public void setControllerRoleId(long controllerRoleId) {
        this.controllerRoleId = controllerRoleId;
    }

    public long getDealChannelId() {
        return this.dealChannelId;
    }

    public void setDealChannelId(long dealChannelId) {
        this.dealChannelId = dealChannelId;
    }

}
