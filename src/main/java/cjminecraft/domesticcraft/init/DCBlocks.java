package cjminecraft.domesticcraft.init;

import cjminecraft.domesticcraft.blocks.BlockSurface;
import cjminecraft.domesticcraft.utils.registries.annotations.RegisterBlock;
import cjminecraft.domesticcraft.utils.registries.annotations.RegisterItem;
import cjminecraft.domesticcraft.utils.registries.annotations.RegisterRender;

public class DCBlocks {

    @RegisterBlock(registryName = "surface")
    @RegisterItem(registryName = "surface")
    @RegisterRender
    public static BlockSurface SURFACE = new BlockSurface();

}
