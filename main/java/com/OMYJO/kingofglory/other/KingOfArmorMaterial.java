package com.OMYJO.kingofglory.other;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class KingOfArmorMaterial implements IArmorMaterial
{
	private int enchantability = 25;
	private Ingredient repairMaterial = Ingredient.fromItems(Items.DIAMOND);

	private int durability = 240;
	private String name = "king";
	private int damageReductionAmount = 0;
	private SoundEvent soundEvent = SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND;
	private float toughness = 0;

	@Override
	public int getEnchantability()
	{
		return enchantability;
	}

	public KingOfArmorMaterial setEnchantability(int enchantability)
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

	private KingOfArmorMaterial setDurability(int durability)
	{
		this.durability = durability;
		return this;
	}

	public KingOfArmorMaterial addDurability(int durability)
	{
		this.durability += durability;
		return this;
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public String getName()
	{
		return "kingofglory:"+name;
	}

	public KingOfArmorMaterial setName(String name)
	{
		this.name = name;
		return this;
	}

	public int getDamageReductionAmount(EquipmentSlotType slotIn)
	{
		return damageReductionAmount;
	}

	public KingOfArmorMaterial setDamageReductionAmount(int damageReductionAmount)
	{
		this.damageReductionAmount = damageReductionAmount;
		return this;
	}

	@Override
	public SoundEvent getSoundEvent()
	{
		return soundEvent;
	}

	public KingOfArmorMaterial setSoundEvent(SoundEvent soundEvent)
	{
		this.soundEvent = soundEvent;
		return this;
	}

	@Override
	public float getToughness()
	{
		return toughness;
	}

	public KingOfArmorMaterial setToughness(float toughness)
	{
		this.toughness = toughness;
		return this;
	}
}
