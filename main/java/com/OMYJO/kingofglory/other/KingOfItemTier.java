package com.OMYJO.kingofglory.other;

import net.minecraft.item.IItemTier;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;

public class KingOfItemTier implements IItemTier
{
	private int harvestLevel = 3;
	private int maxUses = 250;
	private float efficiency = 12.0F;
	private float attackDamage = 0F;
	private int enchantability = 22;
	private Ingredient repairMaterial = Ingredient.fromItems(Items.DIAMOND);

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

	public KingOfItemTier setHarvestLevel(int harvestLevel)
	{
		this.harvestLevel = harvestLevel;
		return this;
	}

	private KingOfItemTier setMaxUses(int maxUses)
	{
		this.maxUses = maxUses;
		return this;
	}

	public KingOfItemTier addMaxUses(int maxUses)
	{
		maxUses += this.maxUses;
		return this;
	}

	public KingOfItemTier setEfficiency(float efficiency)
	{
		this.efficiency = efficiency;
		return this;
	}

	public KingOfItemTier setAttackDamage(float attackDamage)
	{
		this.attackDamage = attackDamage;
		return this;
	}

	public KingOfItemTier setEnchantability(int enchantability)
	{
		this.enchantability = enchantability;
		return this;
	}

	@Override
	public Ingredient getRepairMaterial()
	{
		return this.repairMaterial;
	}
}
