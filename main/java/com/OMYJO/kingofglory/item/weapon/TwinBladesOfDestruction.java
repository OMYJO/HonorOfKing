package com.OMYJO.kingofglory.item.weapon;

import com.OMYJO.kingofglory.other.KingOfMaterial;
import com.OMYJO.kingofglory.other.SharedKingAttributes;
import com.google.common.collect.Multimap;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Rarity;

import java.util.HashMap;
import java.util.UUID;

public class TwinBladesOfDestruction extends KingOfWeapon
{
	private float attackSpeed = 0.15F;
	private float criticalChance = 0.1F;
	private float movementSpeed = 0.05F;
	private final HashMap<EquipmentSlotType, UUID> attackSpeedModifierMap = new HashMap<>();
	private final HashMap<EquipmentSlotType, UUID> criticalChanceModifierMap = new HashMap<>();
	private final HashMap<EquipmentSlotType, UUID> movementSpeedModifierMap = new HashMap<>();

	public TwinBladesOfDestruction()
	{
		super(new KingOfMaterial(), Rarity.UNCOMMON);
		attackSpeedModifierMap.put(EquipmentSlotType.MAINHAND,UUID.randomUUID());
		attackSpeedModifierMap.put(EquipmentSlotType.OFFHAND,UUID.randomUUID());
		criticalChanceModifierMap.put(EquipmentSlotType.MAINHAND,UUID.randomUUID());
		criticalChanceModifierMap.put(EquipmentSlotType.OFFHAND,UUID.randomUUID());
		movementSpeedModifierMap.put(EquipmentSlotType.MAINHAND,UUID.randomUUID());
		movementSpeedModifierMap.put(EquipmentSlotType.OFFHAND,UUID.randomUUID());
		setRegistryName("twin_blades_of_destruction");
	}

	@Override
	public float getAttackSpeed()
	{
		return attackSpeed;
	}

	@Override
	public float getCriticalChance()
	{
		return criticalChance;
	}

	@Override
	public float getMovementSpeed()
	{
		return movementSpeed;
	}

	@Override
	public Multimap<String, AttributeModifier> getAttributeModifiers(EquipmentSlotType equipmentSlot)
	{
		Multimap<String, AttributeModifier> multimap = super.getAttributeModifiers(equipmentSlot);
		if(equipmentSlot == EquipmentSlotType.MAINHAND || equipmentSlot == EquipmentSlotType.OFFHAND)
		{
			multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(attackSpeedModifierMap.get(equipmentSlot), "Weapon modifier", getAttackSpeed(), AttributeModifier.Operation.MULTIPLY_BASE));
			multimap.put(SharedKingAttributes.CRITICAL_CHANCE.getName(), new AttributeModifier(criticalChanceModifierMap.get(equipmentSlot), "Weapon modifier", getCriticalChance(), AttributeModifier.Operation.ADDITION));
			multimap.put(SharedMonsterAttributes.MOVEMENT_SPEED.getName(), new AttributeModifier(movementSpeedModifierMap.get(equipmentSlot), "Weapon modifier",getMovementSpeed(), AttributeModifier.Operation.MULTIPLY_BASE));
		}
		return multimap;
	}
}
