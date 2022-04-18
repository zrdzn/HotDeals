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

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.List;
import java.util.Optional;

public interface Command {

    /**
     * Gets the name of the Command.
     * Whatever is the name, it will trigger associated Command
     * if typed right after the global command prefix.
     *
     * @return name of the command
     */
    String getName();

    /**
     * Gets the optional description of the Command.
     *
     * @return optional description of the command
     */
    Optional<String> getDescription();

    /**
     * Gets the optional usage of the Command.
     *
     * @return optional usage of the command
     */
    Optional<String> getUsage();

    /**
     * Main logic of the command. Here all things will be done
     * after triggering command in the chat. Options in the
     * signature method are everything after the prefix and
     * the Command name.
     *
     * @param event event in which the command was triggered
     * @param optionList optional options of the command
     */
    void execute(MessageReceivedEvent event, List<String> optionList);

}
