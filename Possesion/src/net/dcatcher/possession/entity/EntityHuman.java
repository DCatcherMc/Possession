package net.dcatcher.possession.entity;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.dcatcher.possession.client.rendering.ThreadDownloadSkin;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IImageBuffer;
import net.minecraft.client.renderer.ImageBufferDownload;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureObject;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StringUtils;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;

import java.util.Collection;

public class EntityHuman extends EntityAnimal implements IEntityAdditionalSpawnData{

    public static int entityID;
    public InventoryHuman inventory;

    private String username = "";

    public static final ResourceLocation steveSkin = new ResourceLocation("textures/entity/steve.png");

    protected ThreadDownloadSkin skinDownloadThread_a;
    protected ThreadDownloadSkin skinDownloadThread_b;
    protected ResourceLocation skin_a;
    protected ResourceLocation skin_b;

    protected boolean canDropItems = true;



    @SideOnly(Side.CLIENT)
    private String LayeredName;
    @SideOnly(Side.CLIENT)
    public String getLayeredName() {
        if(LayeredName==null)
            BuildLayeredName();
        return LayeredName;
    }
    @SideOnly(Side.CLIENT)
    public void setLayeredName(String layeredName) {
        LayeredName = layeredName;
    }
    @SideOnly(Side.CLIENT)
    public void BuildLayeredName()
    {
        LayeredName="skins/" + StringUtils.stripControlCodes(getUsername())+"/zombie";
    }
    @SideOnly(Side.CLIENT)
    public ResourceLocation[] getSkins()
    {
        return new ResourceLocation[] {this.func_110306_p()};
    }

    @Override
    public void onUpdate() {
        if(worldObj.isDaytime()){
            if(this.getUsername() != null){
                EntityPlayer player = worldObj.getPlayerEntityByName(this.username);
                if(player != null){
                    player.setDead();
                }
            }
            this.setDead();
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
        this.setCustomNameTag(username);
    }


    public EntityHuman(World par1World) {
        super(par1World);
        setSize(0.8F, 1.6F);
        inventory=new InventoryHuman(this);

    }

    @Override
    public EntityAgeable createChild(EntityAgeable entityageable) {
        return null;
    }

    @SideOnly(Side.CLIENT)
    protected void func_110302_j()
    {
        System.out.println("Setting up custom skins");

        if (this.getUsername() != null && !this.getUsername().isEmpty())
        {
            this.skin_a = func_110311_f(this.getUsername());
            this.skin_b = func_110299_g(this.getUsername());
            this.skinDownloadThread_a = func_110304_a(this.skin_a, this.getUsername());
            this.skinDownloadThread_b = func_110307_b(skin_b, this.getUsername());
        }
    }
    @SideOnly(Side.CLIENT)
    public ThreadDownloadSkin func_110309_l()
    {
        return this.skinDownloadThread_a;
    }
    @SideOnly(Side.CLIENT)
    public ThreadDownloadSkin func_110310_o()
    {
        return this.skinDownloadThread_a;
    }
    @SideOnly(Side.CLIENT)
    public ResourceLocation func_110306_p()
    {
        return this.skin_a;
    }
    @SideOnly(Side.CLIENT)
    public ResourceLocation func_110303_q()
    {
        return this.skin_b;
    }
    @SideOnly(Side.CLIENT)
    public ThreadDownloadSkin func_110304_a(ResourceLocation par0ResourceLocation, String par1Str)
    {
        return func_110301_a(par0ResourceLocation, func_110300_d(par1Str), steveSkin, new ImageBufferDownload());
    }
    @SideOnly(Side.CLIENT)
    public  ThreadDownloadSkin func_110307_b(ResourceLocation par0ResourceLocation, String par1Str)
    {
        return func_110301_a(par0ResourceLocation, func_110308_e(par1Str), (ResourceLocation)null, (IImageBuffer)null);
    }
    @SideOnly(Side.CLIENT)
    private ThreadDownloadSkin func_110301_a(ResourceLocation par0ResourceLocation, String par1Str, ResourceLocation par2ResourceLocation, IImageBuffer par3IImageBuffer)
    {
        TextureManager texturemanager = Minecraft.getMinecraft().func_110434_K();
        Object object = texturemanager.func_110581_b(par0ResourceLocation);

        if (object == null)
        {
            object = new ThreadDownloadSkin(par1Str, par2ResourceLocation, par3IImageBuffer);
            texturemanager.func_110579_a(par0ResourceLocation, (TextureObject)object);
        }

        return (ThreadDownloadSkin)object;
    }
    @SideOnly(Side.CLIENT)
    public String func_110300_d(String par0Str)
    {
        return String.format("http://skins.minecraft.net/MinecraftSkins/%s.png", new Object[] {StringUtils.stripControlCodes(par0Str)});
    }
    @SideOnly(Side.CLIENT)
    public  String func_110308_e(String par0Str)
    {
        return String.format("http://skins.minecraft.net/MinecraftCloaks/%s.png", new Object[] {StringUtils.stripControlCodes(par0Str)});
    }
    @SideOnly(Side.CLIENT)
    public  ResourceLocation func_110311_f(String par0Str)
    {
        return new ResourceLocation(getSkinName(par0Str));
    }
    @SideOnly(Side.CLIENT)
    private  String getSkinName(String par0Str) {
        return "zskins/" + StringUtils.stripControlCodes(par0Str);
    }
    @SideOnly(Side.CLIENT)
    public  ResourceLocation func_110299_g(String par0Str)
    {
        return new ResourceLocation("zcloaks/" + StringUtils.stripControlCodes(par0Str));
    }
    @SideOnly(Side.CLIENT)
    public  ResourceLocation func_110305_h(String par0Str)
    {
        return new ResourceLocation("zskull/" + StringUtils.stripControlCodes(par0Str));
    }
    @Override
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readEntityFromNBT(par1NBTTagCompound);
        NBTTagList nbttaglist = par1NBTTagCompound.getTagList("Inventory");
        this.inventory.readFromNBT(nbttaglist);
        this.inventory.currentItem = par1NBTTagCompound.getInteger("SelectedItemSlot");
        this.setUsername(par1NBTTagCompound.getString("username"));
        this.canDropItems=par1NBTTagCompound.getBoolean("canDropItems");
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeEntityToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setTag("Inventory", this.inventory.writeToNBT(new NBTTagList()));
        par1NBTTagCompound.setInteger("SelectedItemSlot", this.inventory.currentItem);
        par1NBTTagCompound.setString("username", getUsername());
        par1NBTTagCompound.setBoolean("canDropItems", canDropItems);
    }


    public void initialise(EntityPlayer par7EntityPlayer) {
        this.setUsername(par7EntityPlayer.getCommandSenderName());
        this.inventory.copyPlayerInv(par7EntityPlayer.inventory);
        Side side1=FMLCommonHandler.instance().getEffectiveSide();
        Side side2=FMLCommonHandler.instance().getSide();
        if(!this.worldObj.isRemote)
        {
            setDropItems();
        }
        copyPotionEffects(par7EntityPlayer);
        //
    }
    public void copyPotionEffects(EntityPlayer player)
    {
        Collection<PotionEffect> effects=player.getActivePotionEffects();
        for(PotionEffect effect:effects)
        {
            PotionEffect toEffect=new PotionEffect(effect);
            this.addPotionEffect(toEffect);
        }

    }
    private void setDropItems() {
        GameRules gr=this.worldObj.getGameRules();
        canDropItems=!gr.getGameRuleBooleanValue("keepInventory");
    }


    public EntityLivingData func_110161_a(EntityLivingData par1EntityLivingData)
    {
        return null;
    }

    @Override
    public void writeSpawnData(ByteArrayDataOutput data) {
        NBTTagCompound compound=new NBTTagCompound();
        compound.setName("Human");
        compound.setTag("Inventory", this.inventory.writeToNBT(new NBTTagList()));
        compound.setInteger("SelectedItemSlot", this.inventory.currentItem);
        compound.setString("username", getUsername());
        compound.setBoolean("canDropItems", canDropItems);
        try {

            NBTBase.writeNamedTag(compound, data);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
    @Override
    public void readSpawnData(ByteArrayDataInput data) {
        NBTTagCompound compound;
        try {
            compound = (NBTTagCompound) NBTBase.readNamedTag(data);
            NBTTagList nbttaglist = compound.getTagList("Inventory");
            this.inventory.readFromNBT(nbttaglist);
            this.inventory.currentItem = compound.getInteger("SelectedItemSlot");
            this.setUsername(compound.getString("username"));
            canDropItems=compound.getBoolean("canDropItems");
            if(FMLCommonHandler.instance().getEffectiveSide()==Side.CLIENT)
            {
                this.func_110302_j();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }




    }

    // Theese zombies can't be converted back
    @Override
    public boolean interact(EntityPlayer par1EntityPlayer)
    {
        return false;
    }

    /**
     * 0 = item, 1-n is armor
     */
    public ItemStack getCurrentItemOrArmor(int par1)
    {

        if(par1==0)
            if(this.inventory.currentItem==-1)
                return null;
            else
                return this.inventory.mainInv[this.inventory.currentItem];
        return this.inventory.mainInv[par1-1];
    }

    public ItemStack func_130225_q(int i)
    {
        return this.inventory.armorInv[i];
    }

    public void setCurrentItemOrArmor(int par1, ItemStack par2ItemStack)
    {

        if(par1==0){
            System.out.println(this.inventory.currentItem + "");
            this.inventory.mainInv[this.inventory.currentItem] = par2ItemStack;
        }
        else
            this.inventory.mainInv[par1-1] = par2ItemStack;
    }


    @Override
    protected void dropEquipment(boolean par1, int par2)
    {
        if(canDropItems)
            this.inventory.dropEverything();
    }

    public ItemStack getHeldItem()
    {
        if(this.inventory.currentItem==-1)
            return null;
        return this.inventory.mainInv[this.inventory.currentItem];
    }
    @Override
    protected boolean canDespawn() {
        return false;
    }
}
