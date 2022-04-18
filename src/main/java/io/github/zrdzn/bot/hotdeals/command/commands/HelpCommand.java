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
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.slf4j.Logger;

import java.util.List;
import java.util.Optional;

public class HelpCommand extends BaseCommand {

    public HelpCommand(Logger logger, CommandRegistry registry) {
        super(logger, registry);
    }

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public Optional<String> getDescription() {
        return Optional.of("List all available commands.");
    }

    @Override
    public void execute(MessageReceivedEvent event, List<String> optionList) {
        EmbedBuilder embedBuilder = new EmbedBuilder();

        this.getRegistry().getCommands().values()
            .forEach(command -> embedBuilder.addField(command.getName(), command.getDescription().orElse("<None>"), false));

        event.getChannel().sendMessageEmbeds(embedBuilder.build()).queue();
    }

}
