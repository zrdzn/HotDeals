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

import org.slf4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

public class ConfigurationLoader {

    private final Logger logger;

    public ConfigurationLoader(Logger logger) {
        this.logger = logger;
    }

    public Optional<Configuration> load() {
        String roleIdRaw;
        long roleId = 0L;
        if (Files.exists(Path.of("data/role-id"))) {
            try {
                roleIdRaw = Files.readString(Path.of("data/role-id"));
            } catch (IOException exception) {
                this.logger.error("Something went wrong while reading a file.", exception);
                return Optional.empty();
            }

            try {
                roleId = Long.parseLong(roleIdRaw);
            } catch (NumberFormatException exception) {
                this.logger.error("Could not parse role id (string) to role id (long).", exception);
                return Optional.empty();
            }
        }


        String channelIdRaw;
        long channelId = 0L;
        if (Files.exists(Path.of("data/channel-id"))) {
            try {
                channelIdRaw = Files.readString(Path.of("data/channel-id"));
            } catch (IOException exception) {
                this.logger.error("Something went wrong while reading a file.", exception);
                return Optional.empty();
            }

            try {
                channelId = Long.parseLong(channelIdRaw);
            } catch (NumberFormatException exception) {
                this.logger.error("Could not parse channel id (string) to channel id (long).", exception);
                return Optional.empty();
            }
        }


        return Optional.of(new Configuration(roleId, channelId));
    }

}
