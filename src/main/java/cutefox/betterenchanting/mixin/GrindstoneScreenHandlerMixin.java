package cutefox.betterenchanting.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.terraformersmc.modmenu.util.mod.Mod;
import cutefox.betterenchanting.config.GlobalConfig;
import cutefox.betterenchanting.registry.ModItems;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.GrindstoneScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.slot.Slot;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

@Mixin(GrindstoneScreenHandler.class)
public abstract class GrindstoneScreenHandlerMixin extends ScreenHandlerMixin{

    @Shadow
    private Inventory result;
    @Shadow
    Inventory input;


    @Inject(method = "<init>(ILnet/minecraft/entity/player/PlayerInventory;Lnet/minecraft/screen/ScreenHandlerContext;)V", at = @At("TAIL"))
    public void betterEnchanting$acceptCatalyst(int syncId, PlayerInventory playerInventory, ScreenHandlerContext context, CallbackInfo ci){

        GrindstoneScreenHandler instance = (GrindstoneScreenHandler)(Object)this;

        instance.slots.clear();
        instance.trackedStacks.clear();
        instance.previousTrackedStacks.clear();

        instance.addSlot(new Slot( input, 0, 49, 19) {
            public boolean canInsert(ItemStack stack) {
                boolean vanillaCheck = stack.isDamageable() || !EnchantmentHelper.get(stack).isEmpty();
                return vanillaCheck || (stack.getItem().equals(ModItems.ENCHANTMENT_CATALYST) && !EnchantmentHelper.get(stack).isEmpty());
            }
        });
        instance.addSlot(new Slot( input, 1, 49, 40) {
            public boolean canInsert(ItemStack stack) {
                boolean vanillaCheck = stack.isDamageable() || !EnchantmentHelper.get(stack).isEmpty();
                return vanillaCheck || (stack.getItem().equals(ModItems.ENCHANTMENT_CATALYST) && EnchantmentHelper.get(stack).isEmpty());
            }
        });
        instance.addSlot(new Slot(this.result, 2, 129, 34) {
            public boolean canInsert(ItemStack stack) {
                return false;
            }

            public void onTakeItem(PlayerEntity player, ItemStack stack) {
                context.run((world, pos) -> {
                    if (world instanceof ServerWorld) {
                        ExperienceOrbEntity.spawn((ServerWorld)world, Vec3d.ofCenter(pos), this.getExperience(world));
                    }

                    world.syncWorldEvent(1042, pos, 0);
                });
                input.setStack(0, ItemStack.EMPTY);
                if(input.getStack(1).getItem().equals(ModItems.ENCHANTMENT_CATALYST))
                    input.getStack(1).decrement(1);
                else
                    input.setStack(1, ItemStack.EMPTY);
            }

            private int getExperience(World world) {
                int i = 0;
                i += this.getExperience(input.getStack(0));
                i += this.getExperience(input.getStack(1));
                if (i > 0) {
                    int j = (int)Math.ceil((double)i / 2.0);
                    return j + world.random.nextInt(j);
                } else {
                    return 0;
                }
            }

            private int getExperience(ItemStack stack) {
                // TODO Make sure this actually works and I didn't break the code
                int i = 0;
                Iterator<Entry<Enchantment,Integer>> var4 = EnchantmentHelper.get(stack).entrySet().iterator();

                while(var4.hasNext()) {
                    Entry<Enchantment,Integer> entry = var4.next();
                    Enchantment enchantmentEntry = entry.getKey();
                    int j = entry.getValue();
                    if (!enchantmentEntry.isCursed()) {
                        i += enchantmentEntry.getMinPower(j);
                    }
                }

                return i;
            }
        });

        int i;
        for(i = 0; i < 3; ++i) {
            for(int j = 0; j < 9; ++j) {
                instance.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for(i = 0; i < 9; ++i) {
            instance.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }

    }

    @Inject(method = "getOutputStack", at = @At(value = "HEAD"), cancellable = true)
    public void betterEnchanting$createCatalyst(ItemStack firstInput, ItemStack secondInput, CallbackInfoReturnable<ItemStack> cir){
        boolean firstItemIsCatalyst = firstInput.isEmpty()?false:firstInput.getItem().equals(ModItems.ENCHANTMENT_CATALYST);
        boolean secondItemIsCatalyst = secondInput.isEmpty()?false:secondInput.getItem().equals(ModItems.ENCHANTMENT_CATALYST);

        if(firstItemIsCatalyst && secondInput.isEmpty())
            cir.setReturnValue(new ItemStack(ModItems.ENCHANTMENT_CATALYST));

        if(secondItemIsCatalyst && EnchantmentHelper.get(secondInput).isEmpty()){
            if(!firstInput.isEmpty() && !EnchantmentHelper.get(firstInput).isEmpty()){

                ItemStack output = new ItemStack(ModItems.ENCHANTMENT_CATALYST);

                Map<Enchantment, Integer> enchantmentsComponent = EnchantmentHelper.get(firstInput);

                // TODO make sure this works
                enchantmentsComponent.forEach((enchant, level) -> output.addEnchantment(enchant, level));
                // output.set(DataComponentTypes.ENCHANTMENT_GLINT_OVERRIDE,true);
                // output.set(DataComponentTypes.MAX_STACK_SIZE,1);

                cir.setReturnValue(output);
            }
        }
    }

}
