package cjminecraft.domesticcraft.init;

import cjminecraft.domesticcraft.blocks.BlockCounter;
import cjminecraft.domesticcraft.utils.registries.annotations.RegisterBlock;
import cjminecraft.domesticcraft.utils.registries.annotations.RegisterItem;
import cjminecraft.domesticcraft.utils.registries.annotations.RegisterItemBlock;
import cjminecraft.domesticcraft.utils.registries.annotations.RegisterRender;

public class DCBlocks {

    @RegisterBlock(registryName = "counter")
    @RegisterItemBlock(registryName = "counter")
    @RegisterRender
    public static BlockCounter COUNTER = new BlockCounter();

}
