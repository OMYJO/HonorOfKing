package com.OMYJO.kingofglory.item;

import com.OMYJO.kingofglory.other.KingOfMaterial;
import net.minecraft.item.BowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Rarity;
import net.minecraft.util.ResourceLocation;

public class KingOfBow extends BowItem implements KingOfItem
{
	public KingOfBow(Rarity rarity)
	{
		super(new Item.Properties().maxDamage(new KingOfMaterial().getMaxUses()).group(ItemGroup.COMBAT).rarity(rarity));
		this.addPropertyOverride(new ResourceLocation("pull"), (itemStack, world, livingEntity) -> {
			if (livingEntity == null) {
				return 0.0F;
			} else {
				return !(livingEntity.getActiveItemStack().getItem() instanceof BowItem) ? 0.0F : (float)(itemStack.getUseDuration() - livingEntity.getItemInUseCount()) / 20.0F;
			}
		});
		this.addPropertyOverride(new ResourceLocation("pulling"), (itemStack, world, livingEntity) -> {
			return livingEntity != null && livingEntity.isHandActive() && livingEntity.getActiveItemStack() == itemStack ? 1.0F : 0.0F;
		});
	}
}
