package com.OMYJO.KingOfHoror.other;

import net.minecraft.item.IItemTier;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;

public class KingOfMaterial implements IItemTier
{
	private int harvestLevel = 3;
	private int maxUses = 1561;
	private float efficiency = 12.0F;
	private float attackDamage = 0F;
	private int enchantability = 22;

	@Override
	public int getHarvestLevel()
	{
		return harvestLevel;
	}

	@Override
	public int getMaxUses()
	{
		return maxUses;
	}

	@Override
	public float getEfficiency()
	{
		return efficiency;
	}

	@Override
	public float getAttackDamage()
	{
		return attackDamage;
	}

	@Override
	public int getEnchantability()
	{
		return enchantability;
	}

	public void setHarvestLevel(int harvestLevel)
	{
		this.harvestLevel = harvestLevel;
	}

	public void setMaxUses(int maxUses)
	{
		this.maxUses = maxUses;
	}

	public void setEfficiency(float efficiency)
	{
		this.efficiency = efficiency;
	}

	public void setAttackDamage(float attackDamage)
	{
		this.attackDamage = attackDamage;
	}

	public void setEnchantability(int enchantability)
	{
		this.enchantability = enchantability;
	}

	@Override
	public Ingredient getRepairMaterial()
	{
		return Ingredient.fromItems(Items.DIAMOND);
	}
}
