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
