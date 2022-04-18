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

import io.github.zrdzn.bot.hotdeals.command.CommandRegistry;
import io.github.zrdzn.bot.hotdeals.configuration.Configuration;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.slf4j.Logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class SetChannelCommand extends BaseCommand {

    private final Configuration configuration;

    public SetChannelCommand(Logger logger, CommandRegistry registry, Configuration configuration) {
        super(logger, registry);
        this.configuration = configuration;
    }

    @Override
    public String getName() {
        return "set-channel";
    }

    @Override
    public Optional<String> getDescription() {
        return Optional.of("Sets channel with specified id that will receive all deals.");
    }

    @Override
    public Optional<String> getUsage() {
        return Optional.of("!set-channel <channel-id>");
    }

    @Override
    public void execute(MessageReceivedEvent event, List<String> optionList) {
        MessageChannel channel = event.getChannel();

        if (optionList.isEmpty()) {
            this.getUsage().ifPresent(usage -> channel.sendMessage(usage).queue());
            return;
        }

        long channelId;
        try {
            channelId = Long.parseLong(optionList.get(0));
        } catch (NumberFormatException exception) {
            channel.sendMessage("Provided id is not valid id (long type).").queue();
            return;
        }

        Logger logger = this.getLogger();

        try {
            boolean createdDirectory = new File("data").mkdirs();
            if (createdDirectory) {
                logger.info("Directory 'data' did not exist, created a new one.");
            } else {
                logger.info("Directory 'data' already exist, no need to create a new one.");
            }

            FileWriter myWriter = new FileWriter("data/channel-id");
            myWriter.write(String.valueOf(channelId));
            myWriter.close();
            this.configuration.setDealChannelId(channelId);
            channel.sendMessage("Successfully saved new channel id.").queue();
        } catch (IOException exception) {
            this.getLogger().error("Could not save file with channel id.", exception);
        }

    }

}
