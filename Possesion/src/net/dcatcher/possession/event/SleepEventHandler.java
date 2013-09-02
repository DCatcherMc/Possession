package net.dcatcher.possession.event;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;

/**
 * Copyright: DCatcher
 */
public class SleepEventHandler {

    @ForgeSubscribe
    public void onPlayerSleep(PlayerSleepInBedEvent event){
        EntityPlayer player = event.entityPlayer;
        InventoryPlayer currentInv = player.inventory;
    }
}
