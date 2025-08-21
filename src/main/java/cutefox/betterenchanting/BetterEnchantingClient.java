package cutefox.betterenchanting;

import java.util.*;

import cutefox.betterenchanting.Util.BetterEnchantingConstants;
import cutefox.betterenchanting.datagen.ModEnchantIngredientMap;
import cutefox.betterenchanting.datagen.ModEnchantIngredientMap.MAP_CODEC;
import cutefox.betterenchanting.registry.ModHandledScreens;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

public class BetterEnchantingClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		ModHandledScreens.registerModScreen();
		ClientPlayNetworking.registerGlobalReceiver(BetterEnchantingConstants.ENCHANT_INGREDIENT_MAP_PACKET_ID,  (client, handler, buf, responseSender) -> {
			client.execute(() -> {
				Map<String, List<String>> decodedMap = MAP_CODEC.decode(buf);
				ModEnchantIngredientMap.genMapFromJsonStringMap(client.world, decodedMap);
			});
		});
	}
}