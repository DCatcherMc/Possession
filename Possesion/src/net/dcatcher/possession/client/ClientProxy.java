package net.dcatcher.possession.client;

import cpw.mods.fml.client.registry.RenderingRegistry;
import net.dcatcher.possession.client.rendering.RenderHuman;
import net.dcatcher.possession.common.CommonProxy;
import net.dcatcher.possession.entity.EntityHuman;

/**
 * Copyright: DCatcher
 */
public class ClientProxy extends CommonProxy {

    @Override
    public void registerRendering(){
        RenderingRegistry.registerEntityRenderingHandler(EntityHuman.class, new RenderHuman());

    }
}
