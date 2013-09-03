package net.dcatcher.possession.client.rendering.model;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.dcatcher.possession.entity.EntityHuman;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

@SideOnly(Side.CLIENT)
public class ModelHuman extends ModelBiped
{
    public ModelHuman()
    {
        this(0.0F, false);
    }

    protected ModelHuman(float par1, float par2, int par3, int par4)
    {
        super(par1, par2, par3, par4);
    }

    public ModelHuman(float par1, boolean par2)
    {
        super(par1, 0.0F, 64, par2 ? 32 : 64);
    }

    /**
     * Sets the model's various rotation angles. For bipeds, par1 and par2 are used for animating the movement of arms
     * and legs, where par1 represents the time(so that arms and legs swing back and forth) and par2 represents how
     * "far" arms and legs can swing at most.
     */
    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity)
    {
    	super.setRotationAngles(par1, par2, par3, par4, par5, par6, par7Entity);
    }
}
