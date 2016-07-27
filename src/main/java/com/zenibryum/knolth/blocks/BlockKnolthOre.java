package com.zenibryum.knolth.blocks;

import java.util.Random;

import com.zenibryum.knolth.items.ItemTesttube;
import com.zenibryum.knolth.tileentity.TileEntityCoordTransporter;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockKnolthOre extends Block
{
	public BlockKnolthOre( ) {
		super(Material.iron); // it has iron's proprieties
		this.setHardness(4.0F);
	}
	
    public void onBlockClicked(World worldIn, BlockPos pos, EntityPlayer playerIn)
    {
        //Uranium touch?
        super.onBlockClicked(worldIn, pos, playerIn);
    }

    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, Entity entityIn)
    {
        //Uranium touch?
        super.onEntityCollidedWithBlock(worldIn, pos, entityIn);
    }

    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ)
    {
    	//Uranium touch?
        return super.onBlockActivated(worldIn, pos, state, playerIn, side, hitX, hitY, hitZ);
    }
    
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(this);
    }
    
    public int quantityDroppedWithBonus(int fortune, Random random)
    {
        return 1;
    }

    public int quantityDropped(Random random)
    {
        return 1;
    }

    // Spawns this Block's drops into the World as EntityItems.
    public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune)
    {
        super.dropBlockAsItemWithChance(worldIn, pos, state, chance, fortune);
    }

    @Override
    public int getExpDrop(net.minecraft.world.IBlockAccess world, BlockPos pos, int fortune)
    {
        if (this.getItemDropped(world.getBlockState(pos), RANDOM, fortune) != Item.getItemFromBlock(this))
        {
            return 1 + RANDOM.nextInt(5);
        }
        return 0;
    }
    
    protected ItemStack createStackedBlock(IBlockState state)
    {
        return new ItemStack(Item.getItemFromBlock(this));
    }
}