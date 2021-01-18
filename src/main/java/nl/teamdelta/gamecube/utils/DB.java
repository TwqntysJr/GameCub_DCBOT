/*
 * Copyright (c) 2020. Team Delta
 *
 * You are not allowed to redistribute this code. You are not allowed to modify this code without permission of
 * Ortwin Vanpottalsberghe, Any company members of Team Delta  Can get permission to rewrite this code.
 * This code is licensed under our custom Copyright license agreement and is in current use as of from 2020
 */

package nl.teamdelta.gamecube.utils;

import net.dv8tion.jda.api.entities.Activity;
import nl.teamdelta.gamecube.GameCube;

import java.sql.*;

public class DB {
    public static boolean connected = false;
    private static Connection conn;


    public DB(String host, String db, String username, String password){
        try {
            conn = DriverManager.getConnection("jdbc:mysql://" + host + "/" + db, username, password);

            createTables();
            connected = true;
        }catch(SQLException e){
            connected = false;
            e.printStackTrace();
        }
    }

    private void createTables(){

        try{
            Statement stmt = conn.createStatement();
            stmt.addBatch("CREATE TABLE IF NOT EXISTS `settings` (`ID` BIGINT(40) NOT NULL AUTO_INCREMENT, `SERVER_OWNER` VARCHAR(50) NOT NULL, `PREFIX` VARCHAR(8) NOT NULL DEFAULT '-', `MEMBER_COUNT` INT, PRIMARY KEY (`ID`));");
            stmt.executeBatch();
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public static Boolean registered = false;
    public static void registerServer(long ID, String OwnerId){
        try{
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO `settings` (ID, SERVER_OWNER) VALUES (?, ?);");
            pstmt.setLong(1, ID);
            pstmt.setString(2, OwnerId);
            pstmt.execute();
            registered = true;
        }catch (SQLException e){
            registered = false;
            System.out.println("Server ID Already existed try again.");
        }
    }
    public static boolean isServerRegistered(Long ID){
        try{
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM `settings` WHERE ID=?;");
            pstmt.setLong(1,ID);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public static String pref;
    public static String getPrefix(long ServerID){
        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT PREFIX FROM `settings` WHERE ID=?;");
            pstmt.setLong(1, ServerID);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                pref = rs.getString("PREFIX");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return pref;
    }

    public static void setPrefix(long ServerID, String prefix){
        try{
            PreparedStatement pstmt = conn.prepareStatement("UPDATE `settings` SET `PREFIX`=? WHERE `settings` . `ID`=?;");
            pstmt.setString(1, prefix);
            pstmt.setLong(2, ServerID);
            pstmt.execute();
            getPrefix(ServerID);
            GameCube.jda.getPresence().setActivity(Activity.watching(DB.pref + "help | 0.0.1"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateMemberCount(long ServerID, int memberCount){
        try{
            PreparedStatement pstmt = conn.prepareStatement("UPDATE `settings` SET `MEMBER_COUNT`=? WHERE `settings` . `ID`=?;");
            pstmt.setInt(1, memberCount);
            pstmt.setLong(2, ServerID);
            pstmt.execute();
            getMemberCount(ServerID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static int membercount;
    public static int getMemberCount(long ServerID){
        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT MEMBER_COUNT FROM `settings` WHERE ID=?;");
            pstmt.setLong(1, ServerID);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                membercount = rs.getInt("MEMBER_COUNT");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return membercount;
    }
}
