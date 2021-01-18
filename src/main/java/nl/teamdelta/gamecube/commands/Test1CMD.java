/*
 * Copyright (c) 2020. Team Delta
 *
 * You are not allowed to redistribute this code. You are not allowed to modify this code without permission of
 * Ortwin Vanpottalsberghe, Any company members of Team Delta  Can get permission to rewrite this code.
 * This code is licensed under our custom Copyright license agreement and is in current use as of from 2020
 */

package nl.teamdelta.gamecube.commands;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import nl.teamdelta.gamecube.GameCube;
import nl.teamdelta.gamecube.utils.DB;

public class Test1CMD extends ListenerAdapter {

    public void onGuildMessageReceived(GuildMessageReceivedEvent e){
        if(e.getMessage().getContentRaw().equalsIgnoreCase(DB.pref + "prefix")){
            Guild g = GameCube.jda.getGuildById("716986564974084116");

            System.out.println("prefix =  " + DB.getPrefix(g.getIdLong()));

            e.getChannel().sendMessage("prefix = " + DB.pref).queue();
        }
    }
}
