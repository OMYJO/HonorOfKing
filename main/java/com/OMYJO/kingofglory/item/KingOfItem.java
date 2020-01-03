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
	}//ad

	default float getMagicAttack()
	{
		return 0F;
	}//ap

	default float getArmor()
	{
		return 0F;
	}//armor

	default float getMagicDefence()
	{
		return 0F;
	}//ma de

	default float getAttackSpeed() { return 0F; }//adc

	default float getCriticalChance()
	{
		return 0F;
	}//cc

	default float getCriticalDamage()
	{
		return 0F;
	}//36

	default float getLifeSteal()
	{
		return 0F;
	}//blood

	default float getMagicLifeSteal()
	{
		return 0F;
	}//m blood

	default float getMaxHealth() { return 0F; }//HP

	default float getMana() { return 0F; }//MP

	default float getCoolDownReduction()
	{
		return 0F;
	}//CD

	default float getArmorPierce()
	{
		return 0F;
	}//100

	default float getMagicPierce()
	{
		return 0F;
	}//88

	default float getMovementSpeed()
	{
		return 0F;
	}//speed

	default float getResistance()
	{
		return 0F;
	}//shoe

	default float getManaPer5Seconds()
	{
		return 0F;
	}//mana 5

	default float getHPPer5Seconds()
	{
		return 0F;
	}//health 5

}
