package com.OMYJO.kingofglory.item;

import com.OMYJO.kingofglory.other.KingOfMaterial;
import com.google.common.collect.Multimap;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EquipmentSlotType;

public class IronSword extends KingOfWeapon
{
	private float attackDamage = 20F / 10;

	@Override
	public float getAttackDamage()
	{
		return attackDamage;
	}

	public IronSword()
	{
		super(new KingOfMaterial());
		setRegistryName("iron_sword");
	}

	@Override
	public Multimap<String, AttributeModifier> getAttributeModifiers(EquipmentSlotType equipmentSlot)
	{
		return super.getAttributeModifiers(equipmentSlot);
	}


}
