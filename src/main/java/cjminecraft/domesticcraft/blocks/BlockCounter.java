package cjminecraft.domesticcraft.blocks;

import cjminecraft.domesticcraft.client.models.CounterBakedModel;
import cjminecraft.domesticcraft.utils.UnlistedPropertyBlockCopy;
import cjminecraft.domesticcraft.utils.registries.ICustomStateMapper;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraftforge.common.property.ExtendedBlockState;
import net.minecraftforge.common.property.IUnlistedProperty;

public class BlockCounter extends BlockBase implements ICustomStateMapper {

    public static final UnlistedPropertyBlockCopy DEFAULT_BLOCK = new UnlistedPropertyBlockCopy();
    public static final UnlistedPropertyBlockCopy ALT_BLOCK = new UnlistedPropertyBlockCopy();

    public BlockCounter() {
        super(Material.WOOD);
        this.useNeighborBrightness = true;
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
                return CounterBakedModel.VARIANT_TAG;
            }
        };
    }

    @Override
    public boolean isFullBlock(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }
}
