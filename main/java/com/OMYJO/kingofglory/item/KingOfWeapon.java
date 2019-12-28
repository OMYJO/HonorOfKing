package com.OMYJO.kingofglory.item;

import com.OMYJO.kingofglory.KingOfGlory;
import com.OMYJO.kingofglory.other.SharedKingAttributes;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.enchantment.SweepingEnchantment;
import net.minecraft.enchantment.UnbreakingEnchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.world.World;

public abstract class KingOfWeapon extends SwordItem implements KingOfItem
{

	KingOfWeapon(IItemTier tier, Rarity rarity)
	{
		super(tier, 0, 0F, (new Item.Properties()).group(ItemGroup.COMBAT).rarity(rarity));
	}

	@Override
	public Multimap<String, AttributeModifier> getAttributeModifiers(EquipmentSlotType equipmentSlot)
	{
		Multimap<String, AttributeModifier> multimap = HashMultimap.create();;
		return multimap;
	}


	/**
	 * Checks whether an item can be enchanted with a certain enchantment. This
	 * applies specifically to enchanting an item in the enchanting table and is
	 * called when retrieving the list of possible enchantments for an item.
	 * Enchantments may additionally (or exclusively) be doing their own checks in
	 * {@link net.minecraft.enchantment.Enchantment#canApplyAtEnchantingTable(ItemStack)};
	 * check the individual implementation for reference. By default this will check
	 * if the enchantment type is valid for this item type.
	 *
	 * @param stack       the item stack to be enchanted
	 * @param enchantment the enchantment to be applied
	 * @return true if the enchantment can be applied to this item
	 */
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

	/**
	 * Called each tick as long the item is on a player inventory. Uses by maps to check if is on a player hand and
	 * update it's contents.
	 *
	 * @param stack
	 * @param worldIn
	 * @param entityIn
	 * @param itemSlot
	 * @param isSelected
	 */
	@Override
	public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
	{
		super.inventoryTick(stack, worldIn, entityIn, itemSlot, isSelected);
		long time = worldIn.getDayTime();
		if(time % 100 == 0)
		{
			this.setDamage(stack,this.getDamage(stack)-(int)(((PlayerEntity)entityIn).getAttributes().getAttributeInstanceByName(SharedKingAttributes.MANA_PER_5_SECOND.getName()).getValue()));
		}
	}
}
