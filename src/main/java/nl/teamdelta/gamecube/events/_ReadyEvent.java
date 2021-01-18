/*
 * Copyright (c) 2020. Team Delta
 *
 * You are not allowed to redistribute this code. You are not allowed to modify this code without permission of
 * Ortwin Vanpottalsberghe, Any company members of Team Delta  Can get permission to rewrite this code.
 * This code is licensed under our custom Copyright license agreement and is in current use as of from 2020
 */

package nl.teamdelta.gamecube.events;

import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import nl.teamdelta.gamecube.GameCube;
import nl.teamdelta.gamecube.utils.DB;
import nl.teamdelta.gamecube.utils.ServerManager;

public class _ReadyEvent extends ListenerAdapter {




    public void onReady(ReadyEvent e){
        Guild g = GameCube.jda.getGuildById("716986564974084116");


        if(DB.isServerRegistered(g.getIdLong())){
            DB.getPrefix(g.getIdLong());
            DB.getMemberCount(g.getIdLong());
            DB.updateMemberCount(g.getIdLong(), g.getMemberCount());
            ServerManager.updateMemberCountVis(g.getIdLong(), 717081219493527623L);
            GameCube.jda.getPresence().setActivity(Activity.watching(DB.pref + "help | 0.0.1"));

        }else {

            DB.registerServer(g.getIdLong(), g.getOwnerId());
            GameCube.jda.getPresence().setActivity(Activity.watching("Registering server in database..."));
            DB.getPrefix(g.getIdLong());
            DB.getMemberCount(g.getIdLong());
            DB.updateMemberCount(g.getIdLong(), g.getMemberCount());
            ServerManager.updateMemberCountVis(g.getIdLong(), 722023134567596082L);
            GameCube.jda.getPresence().setActivity(Activity.watching(DB.pref + "help | 0.0.1"));
        }


        while(!DB.isServerRegistered(g.getIdLong())){
            GameCube.jda.getPresence().setActivity(Activity.watching("Trying to register server"));
            DB.registerServer(g.getIdLong(), g.getOwnerId());
        }
    }
}
