package net.dcatcher.possession;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.EntityRegistry;
import net.dcatcher.possession.common.CommonProxy;
import net.dcatcher.possession.entity.EntityHuman;
import net.dcatcher.possession.event.SleepEventHandler;
import net.minecraftforge.common.MinecraftForge;

/**
 * Copyright: DCatcher
 */
@Mod(modid="Possession", name="Possession", version="0.1.0")
@NetworkMod(serverSideRequired = false, clientSideRequired = true)
public class Possession {


    @Mod.Instance
    public static Possession instance = new Possession();

    @SidedProxy(clientSide="net.dcatcher.possession.client.ClientProxy", serverSide="net.dcatcher.possession.common.CommonProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event){

        PossessionConfig.initConfig(event.getModConfigurationDirectory());
        MinecraftForge.EVENT_BUS.register(new SleepEventHandler());

    }
    public static int playerID;

    @Mod.EventHandler
    public void init(FMLInitializationEvent event){
              //TODO: This is temporary;

        playerID = EntityRegistry.findGlobalUniqueEntityId();
        EntityRegistry.registerGlobalEntityID(EntityHuman.class, "entityHuman", playerID);
        EntityRegistry.registerModEntity(EntityHuman.class, "entityHuman", playerID, this, 80, 3, true);

        proxy.registerRendering();


    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event){

    }

}
