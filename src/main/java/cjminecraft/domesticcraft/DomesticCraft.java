package cjminecraft.domesticcraft;

import cjminecraft.domesticcraft.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Mod(modid = DomesticCraft.MODID, name = DomesticCraft.NAME, version = DomesticCraft.VERSION, customProperties = {@Mod.CustomProperty(k = "useVersionChecker", v = "false")}, useMetadata = true)
public class DomesticCraft {

    public static final String MODID = "domesticcraft";
    public static final String NAME = "DomesticCraft";
    public static final String VERSION = "${version}";
    public static final String SERVER_PROXY_CLASS = "cjminecraft.domesticcraft.proxy.ServerProxy";
    public static final String CLIENT_PROXY_CLASS = "cjminecraft.domesticcraft.proxy.ClientProxy";

    public static Logger LOGGER = LogManager.getFormatterLogger(NAME);

    @Mod.Instance
    public static DomesticCraft instance;

    @SidedProxy(serverSide = SERVER_PROXY_CLASS, clientSide = CLIENT_PROXY_CLASS, modId = MODID)
    public static CommonProxy proxy;

    @Mod.EventHandler
    public static void preInit(FMLPreInitializationEvent event) {
        proxy.preInit();
    }

    @Mod.EventHandler
    public static void init(FMLInitializationEvent event) {
        proxy.init();
    }

    @Mod.EventHandler
    public static void postInit(FMLPostInitializationEvent event) {
        proxy.postInit();
    }

}
