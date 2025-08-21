// Vault blocks don't exsist in 1.20.1

// package cutefox.betterenchanting.mixin;

// import com.llamalad7.mixinextras.sugar.Local;
// import com.llamalad7.mixinextras.sugar.ref.LocalRef;
// import cutefox.betterenchanting.Util.ModEnchantmentHelper;
// import net.minecraft.block.BlockState;
// import net.minecraft.block.entity.VaultBlockEntity;
// import net.minecraft.block.enums.VaultState;
// import net.minecraft.block.vault.VaultConfig;
// import net.minecraft.block.vault.VaultServerData;
// import net.minecraft.block.vault.VaultSharedData;
// import net.minecraft.entity.player.PlayerEntity;
// import net.minecraft.item.Item;
// import net.minecraft.item.ItemStack;
// import net.minecraft.item.Items;
// import net.minecraft.server.world.ServerWorld;
// import net.minecraft.util.math.BlockPos;
// import org.spongepowered.asm.mixin.Mixin;
// import org.spongepowered.asm.mixin.injection.At;
// import org.spongepowered.asm.mixin.injection.Inject;
// import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
// import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

// import java.util.ArrayList;
// import java.util.List;

// @Mixin(VaultBlockEntity.Server.class)
// public abstract class VaultBlockEntityMixin {

//     @Inject(method = "tryUnlock",at = @At(value = "INVOKE", target = "Ljava/util/List;isEmpty()Z"), locals = LocalCapture.CAPTURE_FAILHARD)
//     private static void betterEnchanting$replaceEnchantedBooks(ServerWorld world, BlockPos pos, BlockState state, VaultConfig config, VaultServerData serverData, VaultSharedData sharedData, PlayerEntity player, ItemStack stack, CallbackInfo ci, VaultState vaultState, @Local LocalRef<List<ItemStack>> localRef){

//         if(localRef.get().isEmpty())
//             return;

//         List<ItemStack> localRefList = new ArrayList<>(localRef.get());

//         List<ItemStack> tempList = new ArrayList<>();
//         List<ItemStack> removeList = new ArrayList<>();
//         for(ItemStack item : localRef.get()){
//             if(item.getItem().equals(Items.ENCHANTED_BOOK))
//                 removeList.add(item);
//             tempList.addAll(ModEnchantmentHelper.replaceEnchantedBook(null,item));
//         }

//         if(!removeList.isEmpty()){
//             localRefList.removeAll(removeList);
//             localRefList.addAll(tempList);
//             localRef.set(localRefList);
//         }



//     }
// }
