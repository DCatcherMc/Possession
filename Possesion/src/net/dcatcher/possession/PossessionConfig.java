package net.dcatcher.possession;

import net.minecraftforge.common.Configuration;

import java.io.File;
import java.io.IOException;

/**
 * Copyright: DCatcher
 */
public class PossessionConfig {


    //If this is set to true, when you sleep, you will automatically possess a nearby mob.
    public static boolean randomPossess;


    public static void initConfig(File directory){
        File configFile = new File(directory + "/Possession.cfg");

        if(!configFile.exists()) {
            try {
                configFile.createNewFile();
            } catch (IOException ex) {
                System.out.println("Cannot make a config file for Possession! OH NOES!");
                ex.printStackTrace();
            }
        }
        Configuration c = new Configuration(configFile);


        //Load config:
        c.load();

        randomPossess = c.get("Possession", "randomPossess", true,
                "If this is true, you will automatically possess a nearby mob").getBoolean(true);

        //Save config:
        c.save();
    }
}
