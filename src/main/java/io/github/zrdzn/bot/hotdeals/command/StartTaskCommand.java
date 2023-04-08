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
package io.github.zrdzn.bot.hotdeals.command;

import io.github.zrdzn.bot.hotdeals.configuration.Configuration;
import io.github.zrdzn.bot.hotdeals.deal.DealScraperTask;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.slf4j.Logger;

import java.util.List;
import java.util.Optional;

public class StartTaskCommand extends BaseCommand {

    private final Configuration configuration;
    private final DealScraperTask task;

    public StartTaskCommand(Logger logger, CommandRegistry registry, Configuration configuration, DealScraperTask task) {
        super(logger, registry);
        this.configuration = configuration;
        this.task = task;
    }

    @Override
    public String getName() {
        return "start-task";
    }

    @Override
    public Optional<String> getDescription() {
        return Optional.of("Starts scrape repeating task for all websites.");
    }

    @Override
    public Optional<String> getUsage() {
        return Optional.of("!start-task [period]");
    }

    @Override
    public void execute(MessageReceivedEvent event, List<String> optionList) {
        MessageChannel channel = event.getChannel();

        if (event.getMember().getRoles().stream().noneMatch(role ->
            role.getIdLong() == this.configuration.getControllerRoleId())) {
            channel.sendMessage("You do not have controller's role.").queue();
            return;
        }

        if (optionList.isEmpty()) {
            this.task.start(30L);
        } else {
            long period;
            try {
                period = Long.parseLong(optionList.get(0));
            } catch (NumberFormatException exception) {
                period = DealScraperTask.DEFAULT_PERIOD;
            }

            this.task.start(period);
        }

        channel.sendMessage("Task successfully started.").queue();
    }

}
