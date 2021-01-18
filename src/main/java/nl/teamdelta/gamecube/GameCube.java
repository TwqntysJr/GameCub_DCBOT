/*
 * Copyright (c) 2020. Team Delta
 *
 * You are not allowed to redistribute this code. You are not allowed to modify this code without permission of
 * Ortwin Vanpottalsberghe, Any company members of Team Delta  Can get permission to rewrite this code.
 * This code is licensed under our custom Copyright license agreement and is in current use as of from 2020
 */

package nl.teamdelta.gamecube;import nl.teamdelta.gamecube.commands.ChangelogCMD;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import nl.teamdelta.gamecube.commands.SetPrefixCmd;
import nl.teamdelta.gamecube.commands.Test1CMD;
import nl.teamdelta.gamecube.commands.TestCMD;
import nl.teamdelta.gamecube.events.MemberJoinEvent;
import nl.teamdelta.gamecube.events._ReadyEvent;
import nl.teamdelta.gamecube.utils.Config;
import nl.teamdelta.gamecube.utils.DB;

import javax.naming.CommunicationException;
import javax.security.auth.login.LoginException;

public class GameCube {

    public static JDA jda;
    public static void main(String[] args) throws Exception {

       //new DB("localhost", "discord_local", "root", "");


        JDABuilder builder = JDABuilder.createDefault(Config.get("token"));


        builder.setActivity(Activity.watching("Initializing"));

        ChangelogCMD changelogCMD = new ChangelogCMD();
        _ReadyEvent readyEvent = new _ReadyEvent();
        MemberJoinEvent memberJoinEvent = new MemberJoinEvent();
        SetPrefixCmd setPrefixCmd = new SetPrefixCmd();
        TestCMD testCMD = new TestCMD();
        Test1CMD test1CMD = new Test1CMD();

        builder.addEventListeners(readyEvent);
        builder.addEventListeners(memberJoinEvent);
        builder.addEventListeners(changelogCMD);
        builder.addEventListeners(setPrefixCmd);
        builder.addEventListeners(test1CMD);
        builder.addEventListeners(testCMD);


        try{
            jda = builder.build();

        }catch (LoginException e){
            System.out.println("Login exception, login failed. Invalid token or network issues.");
        }
        /*
        if(!DB.connected){
            System.out.println("Didn't connect to the database");
        }else{
            try{
                jda = builder.build();

            }catch (LoginException e){
                System.out.println("Login exception, login failed. Invalid token or network issues.");
            }
        }
         */
    }
}
