package com.zenibryum.knolth.blocks;

import java.util.Random;

import com.zenibryum.knolth.Knolth;
import com.zenibryum.knolth.init.KnolthBlocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3i;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockMultiEdge extends Block
{
	public BlockMultiEdge() {
		super(Material.rock);
        stepSound = soundTypeSnow;
        blockParticleGravity = 1.0F;
        slipperiness = 0.6F;
        setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        lightOpacity = 20; // cast a light shadow
        setTickRandomly(false);
        useNeighborBrightness = false;
	}
	
    public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);

    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
    {
        //this.setDefaultFacing(worldIn, pos, state);
    }
    
    @Override
    public void onBlockDestroyedByPlayer(World worldIn, BlockPos pos, IBlockState thisState)
    {
        //IBlockState thisState = worldIn.getBlockState(pos);
        EnumFacing direction = thisState.getValue(FACING);
        Vec3i dirVec = direction.getDirectionVec();
        
        worldIn.destroyBlock( new BlockPos(pos.getX() + dirVec.getX(), pos.getY() + dirVec.getY(), pos.getZ() + dirVec.getZ()), true );
        worldIn.destroyBlock( new BlockPos(pos.getX() + dirVec.getX()*2, pos.getY() + dirVec.getY()*2, pos.getZ() + dirVec.getZ()*2 ), true );
        this.dropBlockAsItem(worldIn, pos, thisState, 0);
    }
    
    
    @Override
    public void onBlockDestroyedByExplosion(World worldIn, BlockPos pos, Explosion explosionIn)
    {
        IBlockState thisState = worldIn.getBlockState(pos);
        EnumFacing direction = thisState.getValue(FACING);
        Vec3i dirVec = direction.getDirectionVec();
        
        worldIn.destroyBlock( new BlockPos(pos.getX() + dirVec.getX(), pos.getY() + dirVec.getY(), pos.getZ() + dirVec.getZ()), true );
        worldIn.destroyBlock( new BlockPos(pos.getX() + dirVec.getX()*2, pos.getY() + dirVec.getY()*2, pos.getZ() + dirVec.getZ()*2 ), true );
        this.dropBlockAsItem(worldIn, pos, thisState, 0);
    }
    
    @Override
    public boolean onBlockActivated(
          World parWorld, 
          BlockPos parBlockPos, 
          IBlockState parIBlockState, 
          EntityPlayer parPlayer, 
          EnumFacing parSide, 
          float hitX, 
          float hitY, 
          float hitZ)
    {
        if (!parWorld.isRemote)
        {   
            IBlockState thisState = parWorld.getBlockState(parBlockPos);
            EnumFacing direction = thisState.getValue(FACING);
            Vec3i dirVec = direction.getDirectionVec();
            
            parPlayer.openGui(Knolth.instance, Knolth.GUI_ENUM.MULTI.ordinal(), parWorld,
            parBlockPos.getX() + dirVec.getX(), parBlockPos.getY() + dirVec.getY(), parBlockPos.getZ() + dirVec.getZ()); 
        }
        
        return true;
    }


    private void setDefaultFacing(World worldIn, BlockPos pos, IBlockState state)
    {
        if (!worldIn.isRemote)
        {
            Block block = worldIn.getBlockState(pos.north()).getBlock();
            Block block1 = worldIn.getBlockState(pos.south()).getBlock();
            Block block2 = worldIn.getBlockState(pos.west()).getBlock();
            Block block3 = worldIn.getBlockState(pos.east()).getBlock();
            EnumFacing enumfacing = (EnumFacing)state.getValue(FACING);

            if (enumfacing == EnumFacing.NORTH && block.isFullBlock() && !block1.isFullBlock())
            {
                enumfacing = EnumFacing.SOUTH;
            }
            else if (enumfacing == EnumFacing.SOUTH && block1.isFullBlock() && !block.isFullBlock())
            {
                enumfacing = EnumFacing.NORTH;
            }
            else if (enumfacing == EnumFacing.WEST && block2.isFullBlock() && !block3.isFullBlock())
            {
                enumfacing = EnumFacing.EAST;
            }
            else if (enumfacing == EnumFacing.EAST && block3.isFullBlock() && !block2.isFullBlock())
            {
                enumfacing = EnumFacing.WEST;
            }

            worldIn.setBlockState(pos, state.withProperty(FACING, enumfacing), 2);
        }
    }
    
    /**
     * Called by ItemBlocks just before a block is actually set in the world, to allow for adjustments to the
     * IBlockstate
     */
    /*
     * 
    public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
    }
    
     */
    /**
     * Called by ItemBlocks after a block is set in the world, to allow post-place logic
     */
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
        worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);

        if (stack.hasDisplayName())
        {
            TileEntity tileentity = worldIn.getTileEntity(pos);

            if (tileentity instanceof TileEntityFurnace)
            {
                ((TileEntityFurnace)tileentity).setCustomInventoryName(stack.getDisplayName());
            }
        }
    }
    
    @SideOnly(Side.CLIENT)
    public Item getItem(World worldIn, BlockPos pos)
    {
        return Item.getItemFromBlock(KnolthBlocks.multi_block_part);
    }
    
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(KnolthBlocks.multi_block_part);
    }

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}
	
	@Override
    public boolean isFullCube()
    {
        return false;
    }

    /**
     * Possibly modify the given BlockState before rendering it on an Entity (Minecarts, Endermen, ...)
     */
    @SideOnly(Side.CLIENT)
    public IBlockState getStateForEntityRender(IBlockState state)
    {
        return this.getDefaultState().withProperty(FACING, EnumFacing.SOUTH);
    }

    /**
     * Convert the given metadata into a BlockState for this Block
     */
    public IBlockState getStateFromMeta(int meta)
    {
        EnumFacing enumfacing = EnumFacing.getFront(meta);

        if (enumfacing.getAxis() == EnumFacing.Axis.Y)
        {
            enumfacing = EnumFacing.NORTH;
        }

        return this.getDefaultState().withProperty(FACING, enumfacing);
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state)
    {
        return ((EnumFacing)state.getValue(FACING)).getIndex();
    }

    protected BlockState createBlockState()
    {
        return new BlockState(this, new IProperty[] {FACING});
    }
}