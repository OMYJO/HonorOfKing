package com.OMYJO.kingofglory.other;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyLoadBase;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

import java.util.TreeSet;

public class KingOfMaterial implements IItemTier, IArmorMaterial
{
	private int harvestLevel = 3;
	private int maxUses = 250;
	private float efficiency = 12.0F;
	private float attackDamage = 0F;
	private int enchantability = 25;
	private Ingredient repairMaterial = Ingredient.fromItems(Items.DIAMOND);

	private int durability = 528;
	private String name = "king";
	private int damageReductionAmount = 0;
	private SoundEvent soundEvent = SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND;
	private float toughness = 0;

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

	public KingOfMaterial setHarvestLevel(int harvestLevel)
	{
		this.harvestLevel = harvestLevel;
		return this;
	}

	public KingOfMaterial setMaxUses(int maxUses)
	{
		this.maxUses = maxUses;
		return this;
	}

	public KingOfMaterial addMaxUses(int maxUses)
	{
		maxUses += this.maxUses;
		return this;
	}

	public KingOfMaterial setEfficiency(float efficiency)
	{
		this.efficiency = efficiency;
		return this;
	}

	public KingOfMaterial setAttackDamage(float attackDamage)
	{
		this.attackDamage = attackDamage;
		return this;
	}

	public KingOfMaterial setEnchantability(int enchantability)
	{
		this.enchantability = enchantability;
		return this;
	}

	@Override
	public Ingredient getRepairMaterial()
	{
		return this.repairMaterial;
	}

	public int getDurability(EquipmentSlotType slotIn)
	{
		return durability;
	}

	public void setDurability(int durability)
	{
		this.durability = durability;
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public String getName()
	{
		return "kingofglory:"+name;
	}

	public KingOfMaterial setName(String name)
	{
		this.name = name;
		return this;
	}

	public int getDamageReductionAmount(EquipmentSlotType slotIn)
	{
		return damageReductionAmount;
	}

	public KingOfMaterial setDamageReductionAmount(int damageReductionAmount)
	{
		this.damageReductionAmount = damageReductionAmount;
		return this;
	}

	@Override
	public SoundEvent getSoundEvent()
	{
		return soundEvent;
	}

	public KingOfMaterial setSoundEvent(SoundEvent soundEvent)
	{
		this.soundEvent = soundEvent;
		return this;
	}

	@Override
	public float getToughness()
	{
		return toughness;
	}

	public KingOfMaterial setToughness(float toughness)
	{
		this.toughness = toughness;
		return this;
	}
}
