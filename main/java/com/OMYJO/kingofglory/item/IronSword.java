package com.OMYJO.kingofglory.item;

import com.OMYJO.kingofglory.KingOfGlory;
import com.OMYJO.kingofglory.other.Convertor;
import com.OMYJO.kingofglory.other.KingOfMaterial;
import com.OMYJO.kingofglory.other.SharedKingAttributes;
import com.google.common.collect.Multimap;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EquipmentSlotType;

import java.util.UUID;

public class IronSword extends KingOfWeapon
{
	private float attackDamage = Convertor.attackDamage(20);

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
		Multimap<String, AttributeModifier> multimap = super.getAttributeModifiers(equipmentSlot);
		if(equipmentSlot == EquipmentSlotType.MAINHAND || equipmentSlot == EquipmentSlotType.OFFHAND)
		{
			multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(UUID.randomUUID(), "Weapon modifier", (double)this.attackDamage, AttributeModifier.Operation.ADDITION));
		}
		return multimap;
	}


}
