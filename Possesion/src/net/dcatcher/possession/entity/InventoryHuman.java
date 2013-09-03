package net.dcatcher.possession.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

/**
 * Copyright: DCatcher
 */
public class InventoryHuman implements IInventory {

    public ItemStack[] mainInv = new ItemStack[36];
    public ItemStack[] armorInv = new ItemStack[4];

    public int currentItem;


    @SideOnly(Side.CLIENT)
    private ItemStack currentItemStack;
    public EntityHuman entityHuman;
    private ItemStack itemStack;

    public boolean isInvDifferent;

    public InventoryHuman(EntityHuman human){
        this.entityHuman = human;
    }

    public ItemStack getCurrentItem(){
        return this.currentItem < 36 && this.currentItem >= 0 ? this.mainInv[this.currentItem] : null;
    }

    public NBTTagList writeToNBT(NBTTagList nbt)
    {
        int i;
        NBTTagCompound nbttagcompound;

        for (i = 0; i < this.mainInv.length; ++i)
        {
            if (this.mainInv[i] != null)
            {
                nbttagcompound = new NBTTagCompound();
                nbttagcompound.setByte("Slot", (byte)i);
                this.mainInv[i].writeToNBT(nbttagcompound);
                nbt.appendTag(nbttagcompound);
            }
        }

        for (i = 0; i < this.armorInv.length; ++i)
        {
            if (this.armorInv[i] != null)
            {
                nbttagcompound = new NBTTagCompound();
                nbttagcompound.setByte("Slot", (byte)(i + 100));
                this.armorInv[i].writeToNBT(nbttagcompound);
                nbt.appendTag(nbttagcompound);
            }
        }

        return nbt;
    }

    /**
     * Reads from the given tag list and fills the slots in the inventory with the correct items.
     */
    public void readFromNBT(NBTTagList nbt)
    {
        this.mainInv = new ItemStack[36];
        this.mainInv = new ItemStack[9];

        for (int i = 0; i < nbt.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound = (NBTTagCompound)nbt.tagAt(i);
            int j = nbttagcompound.getByte("Slot") & 255;
            ItemStack itemstack = ItemStack.loadItemStackFromNBT(nbttagcompound);

            if (itemstack != null)
            {
                if (j >= 0 && j < this.mainInv.length)
                {
                    this.mainInv[j] = itemstack;
                }

                if (j >= 100 && j < this.armorInv.length + 100)
                {
                    this.armorInv[j - 100] = itemstack;
                }
            }
        }
    }


    @Override
    public int getSizeInventory() {
        return this.mainInv.length + this.armorInv.length;
    }

    @Override
    public ItemStack getStackInSlot(int i) {
        ItemStack[] itemStacks = this.mainInv;

        if (i >= itemStacks.length)
        {
            i -= itemStacks.length;
            itemStacks = this.armorInv;
        }

        return itemStacks[i];
    }

    @Override
    public ItemStack decrStackSize(int i, int j) {
        ItemStack[] aitemstack = this.mainInv;

        if (i >= this.mainInv.length)
        {
            aitemstack = this.armorInv;
            i -= this.mainInv.length;
        }

        if (aitemstack[i] != null)
        {
            ItemStack itemstack;

            if (aitemstack[i].stackSize <= j)
            {
                itemstack = aitemstack[i];
                aitemstack[i] = null;
                return itemstack;
            }
            else
            {
                itemstack = aitemstack[i].splitStack(j);

                if (aitemstack[i].stackSize == 0)
                {
                    aitemstack[i] = null;
                }

                return itemstack;
            }
        }
        else
        {
            return null;
        }

    }

    @Override
    public ItemStack getStackInSlotOnClosing(int i) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setInventorySlotContents(int i, ItemStack itemstack) {
        this.mainInv[i] = itemstack;
    }

    @Override
    public String getInvName() {
        return this.entityHuman.getEntityName() + "_inventory";
    }

    @Override
    public boolean isInvNameLocalized() {
        return false;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public void onInventoryChanged() {
        this.isInvDifferent = true;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer entityplayer) {
        return false;
    }

    public void copyPlayerInv(InventoryPlayer par1InventoryPlayer)
    {
        int i;

        for (i = 0; i < this.mainInv.length; ++i)
        {
            this.mainInv[i] = ItemStack.copyItemStack(par1InventoryPlayer.mainInventory[i]);
        }

        for (i = 0; i < this.armorInv.length; ++i)
        {
            this.armorInv[i] = ItemStack.copyItemStack(par1InventoryPlayer.armorInventory[i]);
        }

        this.currentItem = par1InventoryPlayer.currentItem;
    }

    @Override
    public void openChest() {    }

    @Override
    public void closeChest() {    }

    @Override
    public boolean isItemValidForSlot(int i, ItemStack itemstack) {
        return true;
    }

    public void dropEverything()
    {
        int i;

        for (i = 0; i < this.mainInv.length; i++)
        {
            if (this.mainInv[i] != null)
            {
                this.entityHuman.entityDropItem(this.mainInv[i], 0.0f);
                this.mainInv[i] = null;
            }
        }

        for (i = 0; i < this.armorInv.length; i++)
        {
            if (this.armorInv[i] != null)
            {
                this.entityHuman.entityDropItem(this.armorInv[i], 0.0f);
                this.armorInv[i] = null;
            }
        }
    }
}
