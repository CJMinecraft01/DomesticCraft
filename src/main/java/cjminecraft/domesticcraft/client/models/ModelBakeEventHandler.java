package cjminecraft.domesticcraft.client.models;

import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class ModelBakeEventHandler {

    @SubscribeEvent
    public static void onModelBakeEvent(ModelBakeEvent event) {
        Object object = event.getModelRegistry().getObject(SurfaceBakedModel.VARIANT_TAG);
        if(object instanceof IBakedModel) {
            IBakedModel originalModel = (IBakedModel) object;
            SurfaceBakedModel customModel = new SurfaceBakedModel(originalModel);
            event.getModelRegistry().putObject(SurfaceBakedModel.VARIANT_TAG, customModel);
        }
    }
}
