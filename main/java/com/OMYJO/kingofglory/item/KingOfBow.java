package com.OMYJO.kingofglory.item;

import com.OMYJO.kingofglory.other.KingOfMaterial;
import net.minecraft.item.BowItem;
import net.minecraft.item.Item;
import net.minecraft.item.Rarity;

public class KingOfBow extends BowItem implements KingOfItem
{
	public KingOfBow(Rarity rarity)
	{
		super((new Item.Properties()).maxDamage(new KingOfMaterial().getMaxUses()).rarity(rarity).group());
	}
}
