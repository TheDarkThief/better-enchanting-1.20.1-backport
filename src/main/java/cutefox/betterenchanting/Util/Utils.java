package cutefox.betterenchanting.Util;

import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.util.Identifier;

public class Utils {

    private static DynamicRegistryManager registryManager;

    public static Identifier id(String path) {
        return Identifier.of("betterenchanting", path);
    }

    public static Identifier id() {
        return Identifier.tryParse("betterenchanting");
    }


    public static DynamicRegistryManager getRegistryManager() {
        return registryManager;
    }

    public static void setRegistryManager(DynamicRegistryManager registryManager) {
        Utils.registryManager = registryManager;
    }
}
