/*
 * Copyright (c) 2020. Team Delta
 *
 * You are not allowed to redistribute this code. You are not allowed to modify this code without permission of
 * Ortwin Vanpottalsberghe, Any company members of Team Delta  Can get permission to rewrite this code.
 * This code is licensed under our custom Copyright license agreement and is in current use as of from 2020
 */

package nl.teamdelta.gamecube.events;

import com.sun.security.ntlm.Server;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import nl.teamdelta.gamecube.GameCube;
import nl.teamdelta.gamecube.utils.DB;
import nl.teamdelta.gamecube.utils.ServerManager;

public class MemberJoinEvent extends ListenerAdapter {

    public void onGuildMemberJoin(GuildMemberJoinEvent e){
        Guild guild = GameCube.jda.getGuildById("716986564974084116");

        Member member = e.getMember();

        DB.updateMemberCount(guild.getIdLong(), guild.getMemberCount());
        ServerManager.updateMemberCountVis(guild.getIdLong(), 717081219493527623L);
    }
}
