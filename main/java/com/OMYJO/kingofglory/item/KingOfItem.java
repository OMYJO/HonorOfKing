package com.OMYJO.kingofglory.item;

import com.google.common.collect.Multimap;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;

public interface KingOfItem
{
	default float getAttackDamage()
	{
		return 0F;
	}

	default float getAbilityPower()
	{
		return 0F;
	}

	default float getArmor()
	{
		return 0F;
	}

	default float getMagicResist()
	{
		return 0F;
	}

	default float getAttackSpeed()
	{
		return 0F;
	}

	default float getCriticalStrikeChance()
	{
		return 0F;
	}

	default float getCriticalStrikeDamage()
	{
		return 0F;
	}

	default float getLifeSteal()
	{
		return 0F;
	}

	default float getMagicLifeSteal()
	{
		return 0F;
	}

	default float getCooldownReduction()
	{
		return 0F;
	}

	default float getArmorPenetration()
	{
		return 0F;
	}

	default float getMagicPenetration()
	{
		return 0F;
	}

	default float getMana()
	{
		return 0F;
	}

	default float getHealth()
	{
		return 0F;
	}

	default float getMovementSpeed()
	{
		return 0F;
	}

	default float getTenacity()
	{
		return 0F;
	}

	default float getManaRegen()
	{
		return 0F;
	}

	default float getHealthRegen()
	{
		return 0F;
	}

	boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker);
	Multimap<String, AttributeModifier> getAttributeModifiers(EquipmentSlotType equipmentSlot);
}
