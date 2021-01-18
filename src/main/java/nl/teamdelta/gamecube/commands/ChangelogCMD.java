/*
 * Copyright (c) 2020. Team Delta
 *
 * You are not allowed to redistribute this code. You are not allowed to modify this code without permission of
 * Ortwin Vanpottalsberghe, Any company members of Team Delta  Can get permission to rewrite this code.
 * This code is licensed under our custom Copyright license agreement and is in current use as of from 2020
 */

package nl.teamdelta.gamecube.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import nl.teamdelta.gamecube.utils.Config;

public class ChangelogCMD extends ListenerAdapter {
// invis space alt code ALT+0160
    public void onGuildMessageReceived(GuildMessageReceivedEvent e){
        if(e.getMessage().getContentRaw().equalsIgnoreCase("-changelog")){
            EmbedBuilder eb = new EmbedBuilder();
            eb.setTitle("Changelog");

            eb.setDescription("Current bot version: " + Config.get("bot_version"));

            eb.addBlankField(false);
            eb.addField("0.0.1", "Restarted Development of the bot\n" +
                                            "**Added:**\n" +
                                            "                [ ] Bot\n" +
                                            "                [ ] Changelog Command\n" +
                                            "                [ ] Config\n" +
                                            "**Removed:**\n" +
                                            "                [ ] Accidentally previous bot files\n" +
                                            "**Notes:**\n" +
                                            "                [ ] 28th of June at 15.20 Zulu", true);
            eb.addBlankField(true);
            e.getChannel().sendMessage(eb.build()).queue();
        }
    }
}
