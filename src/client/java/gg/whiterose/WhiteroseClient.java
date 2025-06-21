package gg.whiterose;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.ClientBrandRetriever;

public class WhiteroseClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		RPC.start();
	}
}