package cjminecraft.domesticcraft.client.models;

import cjminecraft.domesticcraft.DomesticCraft;
import cjminecraft.domesticcraft.blocks.BlockSurface;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockModelShapes;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.block.model.*;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.property.IExtendedBlockState;
import org.apache.commons.lang3.tuple.Pair;

import javax.annotation.Nullable;
import javax.vecmath.Matrix4f;
import java.util.List;

public class SurfaceBakedModel implements IBakedModel {

    public static final ModelResourceLocation BLOCK_STATE_FILE_NAME = new ModelResourceLocation(DomesticCraft.MODID + ":surface");
    public static final ModelResourceLocation VARIANT_TAG = new ModelResourceLocation(new ResourceLocation(DomesticCraft.MODID, "surface"), "normal");

    private IBakedModel originalModel;

    public SurfaceBakedModel(IBakedModel originalModel) {
        this.originalModel = originalModel;
    }

    @Override
    public List<BakedQuad> getQuads(@Nullable IBlockState state, @Nullable EnumFacing side, long rand) {
        BlockModelShapes blockModelShapes = Minecraft.getMinecraft().getBlockRendererDispatcher().getBlockModelShapes();
        ItemStack heldStack = Minecraft.getMinecraft().player.getHeldItem(EnumHand.MAIN_HAND);
        if(!heldStack.isEmpty() && heldStack.getItem() instanceof ItemBlock) {
            ItemBlock itemBlock = (ItemBlock) heldStack.getItem();
            IBlockState newState = itemBlock.getBlock().getStateForPlacement(Minecraft.getMinecraft().world, Minecraft.getMinecraft().objectMouseOver.getBlockPos(), Minecraft.getMinecraft().player.getHorizontalFacing(), 0, 0, 0, itemBlock.getDamage(heldStack), Minecraft.getMinecraft().player, EnumHand.MAIN_HAND);
            return blockModelShapes.getModelForState(newState).getQuads(newState, side, rand);
        }
        return blockModelShapes.getModelForState(Blocks.AIR.getDefaultState()).getQuads(Blocks.AIR.getDefaultState(), side, rand);
//        return handleBlockState(state).getLeft().getQuads(state, side, rand);
    }

    private Pair<IBakedModel, IBakedModel> handleBlockState(@Nullable IBlockState state) {
        if(state instanceof IExtendedBlockState) {
            IExtendedBlockState extendedBlockState = (IExtendedBlockState) state;
            IBlockState defaultBlockState = extendedBlockState.getValue(BlockSurface.DEFAULT_BLOCK);
            IBlockState altBlockState = extendedBlockState.getValue(BlockSurface.ALT_BLOCK);

            BlockModelShapes blockModelShapes = Minecraft.getMinecraft().getBlockRendererDispatcher().getBlockModelShapes();
            return Pair.of(blockModelShapes.getModelForState(defaultBlockState), blockModelShapes.getModelForState(altBlockState));
        }
        return Pair.of(this.originalModel, this.originalModel);
    }

    @Override
    public boolean isAmbientOcclusion() {
        return this.originalModel.isAmbientOcclusion();
    }

    @Override
    public boolean isGui3d() {
        return this.originalModel.isGui3d();
    }

    @Override
    public boolean isBuiltInRenderer() {
        return this.originalModel.isBuiltInRenderer();
    }

    @Override
    public TextureAtlasSprite getParticleTexture() {
        return this.originalModel.getParticleTexture();
    }

    @Override
    public ItemOverrideList getOverrides() {
        return this.originalModel.getOverrides();
    }

    @Override
    public ItemCameraTransforms getItemCameraTransforms() {
        return this.originalModel.getItemCameraTransforms();
    }

    @Override
    public Pair<? extends IBakedModel, Matrix4f> handlePerspective(ItemCameraTransforms.TransformType cameraTransformType) {
        return Pair.of(this, this.originalModel.handlePerspective(cameraTransformType).getRight());
    }
}
