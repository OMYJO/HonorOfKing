package com.OMYJO.KingOfHoror.item;

import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SwordItem;

public abstract class KingOfWeapon extends SwordItem implements KingOfItem
{
	KingOfWeapon(IItemTier tier, int attackDamageIn, float attackSpeedIn)
	{
		super(tier, attackDamageIn, attackSpeedIn, (new Item.Properties()).group(ItemGroup.COMBAT));
	}
}
