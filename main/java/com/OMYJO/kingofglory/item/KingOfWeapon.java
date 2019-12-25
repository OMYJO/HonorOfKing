package com.OMYJO.kingofglory.item;

import com.google.common.collect.Multimap;
import net.minecraft.enchantment.SweepingEnchantment;
import net.minecraft.enchantment.UnbreakingEnchantment;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;

public abstract class KingOfWeapon extends SwordItem implements KingOfItem
{
	KingOfWeapon(IItemTier tier)
	{
		super(tier, 0, 0F, (new Item.Properties()).group(ItemGroup.COMBAT));
	}

	@Override
	public Multimap<String, AttributeModifier> getAttributeModifiers(EquipmentSlotType equipmentSlot)
	{
		Multimap<String, AttributeModifier> multimap = super.getAttributeModifiers(equipmentSlot);
		return multimap;
	}

	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack, net.minecraft.enchantment.Enchantment enchantment)
	{
		if(enchantment instanceof UnbreakingEnchantment)
		{
			return enchantment.type.canEnchantItem(stack.getItem());
		}
		else if(enchantment instanceof SweepingEnchantment)
		{
			return enchantment.type.canEnchantItem(stack.getItem());
		}
		else
		{
			return false;
		}
	}
}
