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

	default float getMagicAttack()
	{
		return 0F;
	}

	default float getArmor()
	{
		return 0F;
	}

	default float getMagicDefence()
	{
		return 0F;
	}

	default float getAttackSpeed() { return 0F; }

	default float getCriticalChance()
	{
		return 0F;
	}

	default float getCriticalDamage()
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

	default float getMaxHealth()
	{
		return 0F;
	}

	default float getCooldownReduction()
	{
		return 0F;
	}

	default float getArmorPierce()
	{
		return 0F;
	}

	default float getMagicPierce()
	{
		return 0F;
	}

	default float getMana() { return 0F; }

	default float getMovementSpeed()
	{
		return 0F;
	}

	default float getResistance()
	{
		return 0F;
	}

	default float getManaPer5Second()
	{
		return 0F;
	}

	default float getHPPer5Second()
	{
		return 0F;
	}

	boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker);

	/**
	 * Gets a map of item attribute modifiers, used by ItemSword to increase hit damage.
	 */
	Multimap<String, AttributeModifier> getAttributeModifiers(EquipmentSlotType equipmentSlot);
}
