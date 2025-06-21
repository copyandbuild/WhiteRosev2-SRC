package gg.whiterose.mixin.client;

import net.minecraft.client.ClientBrandRetriever;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientBrandRetriever.class)
public class ClientBrandRetrieverMxin {

    @Inject(method = "getClientModName()Ljava/lang/String;", at = @At("HEAD"), cancellable = true, remap = false)
    private static void getClientModName(CallbackInfoReturnable<String> cir) {
        cir.setReturnValue("WhiteRoseClient:fabric-1.20+");
        cir.cancel();
    }
}
