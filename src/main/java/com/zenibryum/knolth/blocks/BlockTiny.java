package com.zenibryum.knolth.blocks;

import java.util.Random;

import com.zenibryum.knolth.tileentity.TileEntityTiny;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.World;

public class BlockTiny extends BlockContainer {

        public BlockTiny () {
                super( Material.wood );
                setHardness(2.0F);
                setResistance(5.0F);
        }

        @Override
        public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ) {
                TileEntity tileEntity = worldIn.getTileEntity(pos);
                if (tileEntity == null || playerIn.isSneaking()) {
                   	return false;
                }
        //code to open gui explained later
        //player.openGui(Tiny.instance, 0, world, x, y, z);
        	return true;
        }
        
    	@Override
    	public boolean isOpaqueCube()
    	{
    		return true;
    	}
    	
    	@Override
        public boolean isFullCube()
        {
            return true;
        }

    	@Override
    	public EnumWorldBlockLayer getBlockLayer()
    	{
    		return EnumWorldBlockLayer.CUTOUT; // TRANSLUCENT
    	}
    	
        @Override
        public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
                dropItems(worldIn, pos);
                super.breakBlock(worldIn, pos, state);
        }

        private void dropItems(World world, BlockPos pos){
                Random rand = new Random();

                TileEntity tileEntity = world.getTileEntity(pos);
                if (!(tileEntity instanceof IInventory)) {
                        return;
                }
                IInventory inventory = (IInventory) tileEntity;

                for (int i = 0; i < inventory.getSizeInventory(); i++) {
                        ItemStack item = inventory.getStackInSlot(i);

                        if (item != null && item.stackSize > 0) {
                                float rx = rand.nextFloat() * 0.8F + 0.1F;
                                float ry = rand.nextFloat() * 0.8F + 0.1F;
                                float rz = rand.nextFloat() * 0.8F + 0.1F;
                                
                                pos.add(rx, ry, rz);
                                //(World worldIn, double x, double y, double z)

                                EntityItem entityItem = new EntityItem( world, pos.getX(), pos.getY(), pos.getZ() );
                                entityItem.dropItem(item.getItem(), item.stackSize);
                                //new ItemStack(item.getItem(), item.stackSize, item.getItemDamage())

                                if (item.hasTagCompound()) {
                                        entityItem.getEntityItem().setTagCompound((NBTTagCompound) item.getTagCompound().copy());
                                }

                                float factor = 0.05F;
                                entityItem.motionX = rand.nextGaussian() * factor;
                                entityItem.motionY = rand.nextGaussian() * factor + 0.2F;
                                entityItem.motionZ = rand.nextGaussian() * factor;
                                world.spawnEntityInWorld(entityItem);
                                item.stackSize = 0;
                        }
                }
        }

        @Override
        public TileEntity createNewTileEntity(World worldIn, int meta) {
          return new TileEntityTiny();
        }

}