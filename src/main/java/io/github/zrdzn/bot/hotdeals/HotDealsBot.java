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
package io.github.zrdzn.bot.hotdeals;

import io.github.zrdzn.bot.hotdeals.command.CommandListener;
import io.github.zrdzn.bot.hotdeals.command.CommandRegistry;
import io.github.zrdzn.bot.hotdeals.command.commands.HelpCommand;
import io.github.zrdzn.bot.hotdeals.command.commands.SetRoleCommand;
import io.github.zrdzn.bot.hotdeals.command.commands.StartTaskCommand;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.internal.utils.JDALogger;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;

import javax.security.auth.login.LoginException;

public class HotDealsBot {

    public static void main(String[] args) throws LoginException {
        if (args.length == 0) {
            throw new IllegalArgumentException("Token was not provided.");
        }

        JDABuilder jdaBuilder = JDABuilder.createDefault(args[0]);

        HotDealsBot app = new HotDealsBot();
        app.run(jdaBuilder);
    }

    public void run(JDABuilder jdaBuilder) throws LoginException {
        BasicConfigurator.configure();
        Logger logger = JDALogger.getLog("DISCORD-BOT");

        CommandRegistry commandRegistry = new CommandRegistry();

        commandRegistry.register(new HelpCommand(logger, commandRegistry));
        commandRegistry.register(new SetRoleCommand(logger, commandRegistry));
        commandRegistry.register(new StartTaskCommand(logger, commandRegistry));

        jdaBuilder.addEventListeners(new CommandListener(commandRegistry)).build();
    }

}
