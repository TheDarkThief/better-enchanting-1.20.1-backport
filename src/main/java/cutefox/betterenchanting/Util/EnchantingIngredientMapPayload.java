// TODO figure out networking

package cutefox.betterenchanting.Util;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.CustomPayload;

import java.util.*;

import cutefox.betterenchanting.datagen.ModEnchantIngredientMap;

public record EnchantingIngredientMapPayload(Map<String, List<String>> map) implements CustomPayload {
    public static final Id<EnchantingIngredientMapPayload> ID = new Id<>(
            BetterEnchantingConstants.ENCHANT_INGREDIENT_MAP_PACKET_ID);
    public static final PacketCodec<RegistryByteBuf, EnchantingIngredientMapPayload> CODEC = PacketCodec.tuple(
            ModEnchantIngredientMap.MAP_CODEC, EnchantingIngredientMapPayload::map,
            EnchantingIngredientMapPayload::new);

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}