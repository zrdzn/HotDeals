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
package io.github.zrdzn.bot.hotdeals.command.commands;

import io.github.zrdzn.bot.hotdeals.command.Command;
import io.github.zrdzn.bot.hotdeals.command.CommandRegistry;
import org.slf4j.Logger;

import java.util.Optional;

public abstract class BaseCommand implements Command {

    private final Logger logger;
    private final CommandRegistry registry;

    public BaseCommand(Logger logger, CommandRegistry registry) {
        this.logger = logger;
        this.registry = registry;
    }

    @Override
    public Optional<String> getDescription() {
        return Optional.empty();
    }

    @Override
    public Optional<String> getUsage() {
        return Optional.empty();
    }

    public Logger getLogger() {
        return this.logger;
    }

    public CommandRegistry getRegistry() {
        return this.registry;
    }

}
