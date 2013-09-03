package net.dcatcher.possession.event;

import net.dcatcher.possession.entity.EntityHuman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;

/**
 * Copyright: DCatcher
 */
public class SleepEventHandler {

    @ForgeSubscribe
    public void onPlayerSleep(PlayerSleepInBedEvent event){
        if(!event.entityPlayer.worldObj.isDaytime()){
            EntityPlayer player = event.entityPlayer;
            World world = event.entityPlayer.worldObj;

            EntityHuman entity = new EntityHuman(world);
            entity.setPosition(player.posX + 2, player.posY + 1, player.posZ + 2);
            entity.initialise(player);

            player.inventory.clearInventory(-1, -1);
            player.wakeUpPlayer(true, false, true);

            world.spawnEntityInWorld(entity);
        }
    }
}
