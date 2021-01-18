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
import java.util.Random;

public class TestCMD extends ListenerAdapter {
//
// TODO: FIX THIS SO YOU CAN MANUALLY REGISTER A SERVER
    public void onGuildMessageReceived(GuildMessageReceivedEvent e){
        if(e.getMessage().getContentRaw().equalsIgnoreCase("-test")){

            Guild g = GameCube.jda.getGuildById("716986564974084116");
            Random r = new Random();
            int n = r.nextInt(9999);

            g.getMemberCount();

            assert g != null;
            DB.registerServer(g.getIdLong(), g.getOwnerId());

            if(DB.registered){
                e.getChannel().sendMessage("Server registered").queue();
                DB.registered = false;
            }else if(!DB.registered){
                e.getChannel().sendMessage("Server is already registered or server id might already exist in database").queue();
            }

        }

    }
}
