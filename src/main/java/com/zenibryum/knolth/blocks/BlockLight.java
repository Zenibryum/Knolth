package com.zenibryum.knolth.blocks;

import java.util.Random;

import com.zenibryum.knolth.init.KnolthBlocks;
import com.zenibryum.knolth.tileentity.TileEntityTube;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockLight extends Block
{
    private final boolean isOn;

    public BlockLight(boolean isOn)
    {
        super(Material.redstoneLight);
        this.isOn = isOn;

        if (isOn)
        {
            this.setLightLevel(1.0F);
        }
    }
    
    public boolean isTubePowered(World worldObj, BlockPos pos)
    {
		if ( worldObj.getTileEntity( pos.up() ) instanceof TileEntityTube )
		{
			TileEntityTube t = (TileEntityTube) worldObj.getTileEntity( pos.up() );
			return t.power;
		}
		else
		{
			return false;
		}
    }
    @Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
    {
        if (!worldIn.isRemote)
        {
            if (this.isOn && !isTubePowered(worldIn,pos))
            {
                worldIn.setBlockState(pos, KnolthBlocks.lightbulb.getDefaultState(), 2);
            }
            else if (!this.isOn && isTubePowered(worldIn,pos))
            {
                worldIn.setBlockState(pos, KnolthBlocks.lightbulb_lit.getDefaultState(), 2);
            }
        }
    }

    /**
     * Called when a neighboring block changes.
     */
    @Override
    public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock)
    {
        if (!worldIn.isRemote)
        {
            if (this.isOn && !isTubePowered(worldIn,pos))
            {
                worldIn.scheduleUpdate(pos, this, 4);
            }
            else if (!this.isOn && isTubePowered(worldIn,pos))
            {
                worldIn.setBlockState(pos, KnolthBlocks.lightbulb_lit.getDefaultState(), 2);
            }
        }
    }
    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        if (!worldIn.isRemote)
        {
            if (this.isOn && !isTubePowered(worldIn,pos))
            {
                worldIn.setBlockState(pos, KnolthBlocks.lightbulb.getDefaultState(), 2);
            }
        }
    }

    /**
     * Get the Item that this Block should drop when harvested.
     */
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(KnolthBlocks.lightbulb);
    }

    @SideOnly(Side.CLIENT)
    public Item getItem(World worldIn, BlockPos pos)
    {
        return Item.getItemFromBlock(KnolthBlocks.lightbulb);
    }

    protected ItemStack createStackedBlock(IBlockState state)
    {
        return new ItemStack(KnolthBlocks.lightbulb);
    }
}