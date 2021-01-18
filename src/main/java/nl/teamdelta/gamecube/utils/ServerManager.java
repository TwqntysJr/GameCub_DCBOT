/*
 * Copyright (c) 2020. Team Delta
 *
 * You are not allowed to redistribute this code. You are not allowed to modify this code without permission of
 * Ortwin Vanpottalsberghe, Any company members of Team Delta  Can get permission to rewrite this code.
 * This code is licensed under our custom Copyright license agreement and is in current use as of from 2020
 */

package nl.teamdelta.gamecube.utils;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.VoiceChannel;
import nl.teamdelta.gamecube.GameCube;

public class ServerManager {
    public static void updateMemberCountVis(long guild_ID, long channel_ID){
        Guild g = GameCube.jda.getGuildById(guild_ID);
        VoiceChannel membercountchannel = g.getVoiceChannelById(channel_ID);
        int membercount = DB.getMemberCount(guild_ID);

        membercountchannel.getManager().setName("Member count: " + membercount).queue();
    }
}
