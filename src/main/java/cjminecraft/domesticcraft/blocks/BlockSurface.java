package cjminecraft.domesticcraft.blocks;

import cjminecraft.domesticcraft.DomesticCraft;
import cjminecraft.domesticcraft.client.models.SurfaceBakedModel;
import cjminecraft.domesticcraft.utils.UnlistedPropertyBlockCopy;
import cjminecraft.domesticcraft.utils.registries.ICustomStateMapper;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.property.ExtendedBlockState;
import net.minecraftforge.common.property.IExtendedBlockState;
import net.minecraftforge.common.property.IUnlistedProperty;

public class BlockSurface extends BlockBase implements ICustomStateMapper {

    public static final UnlistedPropertyBlockCopy DEFAULT_BLOCK = new UnlistedPropertyBlockCopy();
    public static final UnlistedPropertyBlockCopy ALT_BLOCK = new UnlistedPropertyBlockCopy();

    public BlockSurface() {
        super(Material.WOOD);
    }

    @Override
    protected BlockStateContainer createBlockState() {
        IProperty[] listedProperties = new IProperty[0];
        IUnlistedProperty[] unlistedProperties = new IUnlistedProperty[]{DEFAULT_BLOCK, ALT_BLOCK};
        return new ExtendedBlockState(this, listedProperties, unlistedProperties);
    }

    @Override
    public StateMapperBase getStateMapper() {
        return new StateMapperBase() {
            @Override
            protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
                return SurfaceBakedModel.VARIANT_TAG;
            }
        };
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (state instanceof IExtendedBlockState) {
            if (player.getHeldItem(hand).getItem() instanceof ItemBlock) {
                ItemBlock itemBlock = (ItemBlock) player.getHeldItem(hand).getItem();
                IExtendedBlockState extendedBlockState = (IExtendedBlockState) state;
                DomesticCraft.LOGGER.info("UPDATED BLOCK STATE");
                world.setBlockState(pos, extendedBlockState.withProperty(DEFAULT_BLOCK, Blocks.ACACIA_FENCE.getDefaultState()), 2);
                //world.setBlockState(pos, extendedBlockState.withProperty(DEFAULT_BLOCK, itemBlock.getBlock().getStateForPlacement(world, pos, facing, hitX, hitY, hitZ, itemBlock.getDamage(player.getHeldItem(hand)), player, hand)), 3);
            }
        }
        return true;
    }
}
