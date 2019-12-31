package com.OMYJO.kingofglory.item.weapon;

import com.OMYJO.kingofglory.other.KingOfMaterial;
import com.OMYJO.kingofglory.other.SharedKingAttributes;
import com.google.common.collect.Multimap;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Rarity;

import java.util.HashMap;
import java.util.UUID;

public class PugilistsGauntlet extends KingOfWeapon
{
	private float criticalChance = 0.08F;
	private final HashMap<EquipmentSlotType, UUID> criticalChanceModifierMap = new HashMap<>();

	public PugilistsGauntlet()
	{
		super(new KingOfMaterial(), Rarity.COMMON);
		criticalChanceModifierMap.put(EquipmentSlotType.MAINHAND,UUID.randomUUID());
		criticalChanceModifierMap.put(EquipmentSlotType.OFFHAND,UUID.randomUUID());
		setRegistryName("pugilists_gauntlet");
	}

	@Override
	public float getCriticalChance()
	{
		return criticalChance;
	}

	@Override
	public Multimap<String, AttributeModifier> getAttributeModifiers(EquipmentSlotType equipmentSlot)
	{
		Multimap<String, AttributeModifier> multimap = super.getAttributeModifiers(equipmentSlot);
		if(equipmentSlot == EquipmentSlotType.MAINHAND || equipmentSlot == EquipmentSlotType.OFFHAND)
		{
			multimap.put(SharedKingAttributes.CRITICAL_CHANCE.getName(), new AttributeModifier(criticalChanceModifierMap.get(equipmentSlot), "Weapon modifier", getCriticalChance(), AttributeModifier.Operation.ADDITION));
		}
		return multimap;
	}
}
