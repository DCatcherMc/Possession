package net.dcatcher.possession.client.rendering;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.dcatcher.possession.client.rendering.model.ModelHuman;
import net.dcatcher.possession.entity.EntityHuman;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelZombieVillager;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderHuman extends RenderBiped
{
    private static final ResourceLocation field_110865_p = new ResourceLocation("possession:textures/entity/steve.png");

    private ModelBiped field_82434_o; 

    protected ModelBiped field_82437_k; 
    protected ModelBiped field_82435_l; 
    protected ModelBiped field_82436_m; 
    protected ModelBiped field_82433_n; 

    public RenderHuman()
    {
        super(new ModelHuman(), 0.5F, 1.0F);
        this.field_82434_o = this.modelBipedMain;
    }

    protected void func_82421_b()
    {
        this.field_82423_g = new ModelHuman(1.0F, true);
        this.field_82425_h = new ModelHuman(0.5F, true);
        this.field_82437_k = this.field_82423_g;
        this.field_82435_l = this.field_82425_h;
    }

    protected int func_82429_a(EntityHuman entityhuman, int par2, float par3)
    {
        this.func_82427_a(entityhuman);
        return super.func_130006_a(entityhuman, par2, par3);
    }

    public void func_82426_a(EntityHuman entityHuman, double par2, double par4, double par6, float par8, float par9)
    {
        this.func_82427_a(entityHuman);
        super.doRenderLiving(entityHuman, par2, par4, par6, par8, par9);
    }



    protected void func_82428_a(EntityHuman entityHuman, float par2)
    {
        this.func_82427_a(entityHuman);
        super.func_130005_c(entityHuman, par2);
    }

    private void func_82427_a(EntityHuman entityHuman)
    {
            this.mainModel = this.field_82434_o;
            this.field_82423_g = this.field_82437_k;
            this.field_82425_h = this.field_82435_l;

        this.modelBipedMain = (ModelBiped)this.mainModel;
    }

    protected void func_82430_a(EntityHuman entityHuman, float par2, float par3, float par4)
    {
        super.rotateCorpse(entityHuman, par2, par3, par4);
    }

    protected void func_130005_c(EntityLiving par1EntityLiving, float par2)
    {
        this.func_82428_a((EntityHuman)par1EntityLiving, par2);
    }

    protected ResourceLocation func_110856_a(EntityLiving par1EntityLiving)
    {
        return this.func_110863_a((EntityHuman)par1EntityLiving);
    }

    protected ResourceLocation func_110863_a(EntityHuman entityHuman)
    {
        return   field_110865_p;
    }

    public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
    {
        this.func_82426_a((EntityHuman)par1EntityLiving, par2, par4, par6, par8, par9);
    }

    protected int func_130006_a(EntityLiving par1EntityLiving, int par2, float par3)
    {
        return this.func_82429_a((EntityHuman)par1EntityLiving, par2, par3);
    }

    /**
     * Queries whether should render the specified pass or not.
     */
    protected int shouldRenderPass(EntityLivingBase par1EntityLivingBase, int par2, float par3)
    {
        return this.func_82429_a((EntityHuman)par1EntityLivingBase, par2, par3);
    }

    protected void renderEquippedItems(EntityLivingBase par1EntityLivingBase, float par2)
    {
        this.func_82428_a((EntityHuman)par1EntityLivingBase, par2);
    }

    protected void rotateCorpse(EntityLivingBase par1EntityLivingBase, float par2, float par3, float par4)
    {
        this.func_82430_a((EntityHuman)par1EntityLivingBase, par2, par3, par4);
    }

    public void renderPlayer(EntityLivingBase par1EntityLivingBase, double par2, double par4, double par6, float par8, float par9)
    {
        this.func_82426_a((EntityHuman)par1EntityLivingBase, par2, par4, par6, par8, par9);
    }


    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity) and this method has signature public void doRender(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
     */
    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
        this.func_82426_a((EntityHuman)par1Entity, par2, par4, par6, par8, par9);
    }
    
    
}
