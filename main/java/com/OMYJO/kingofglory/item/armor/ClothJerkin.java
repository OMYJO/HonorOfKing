package com.OMYJO.kingofglory.item.armor;

import com.OMYJO.kingofglory.item.KingOfItem;
import com.OMYJO.kingofglory.other.KingOfMaterial;
import com.OMYJO.kingofglory.other.SharedKingAttributes;
import com.google.common.collect.Multimap;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Rarity;

import java.util.UUID;

public class ClothJerkin extends KingOfArmor implements KingOfItem
{
	private float armor = 90F;
	private final UUID ArmorModifier = UUID.randomUUID();

	public ClothJerkin(EquipmentSlotType slot,String registryName)
	{
		super(new KingOfMaterial().setName("cloth_jerkin"), slot, Rarity.COMMON);
		setRegistryName(registryName);
	}

	@Override
	public float getArmor()
	{
		return armor;
	}

	/**
	 * Gets a map of item attribute modifiers, used by ItemSword to increase hit damage.
	 *
	 * @param equipmentSlot
	 */
	@Override
	public Multimap<String, AttributeModifier> getAttributeModifiers(EquipmentSlotType equipmentSlot)
	{
		Multimap multimap = super.getAttributeModifiers(equipmentSlot);
		if(equipmentSlot == getEquipmentSlot())
		{
			multimap.put(SharedKingAttributes.ARMOR.getName(),new AttributeModifier(ArmorModifier,"Armor modifier",getArmor(), AttributeModifier.Operation.ADDITION));
		}
		return multimap;
	}
}
