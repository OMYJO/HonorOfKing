package com.OMYJO.kingofglory.item.armor;

import com.OMYJO.kingofglory.item.KingOfItem;
import com.OMYJO.kingofglory.other.KingOfArmorMaterial;
import com.OMYJO.kingofglory.other.KingOfItemTier;
import com.OMYJO.kingofglory.other.SharedKingAttributes;
import com.google.common.collect.Multimap;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Rarity;

import java.util.UUID;

public class AntiMageCloak extends KingOfArmor implements KingOfItem
{
	private float magicDefence = 90F;
	private final UUID magicDefenceModifier = UUID.randomUUID();

	public AntiMageCloak(EquipmentSlotType slot, String registryName)
	{
		super(new KingOfArmorMaterial().setName("anti-mage_cloak"), slot, Rarity.COMMON);
		setRegistryName(registryName);
	}

	@Override
	public float getMagicDefence()
	{
		return magicDefence;
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
			multimap.put(SharedKingAttributes.MAGIC_DEFENCE.getName(),new AttributeModifier(magicDefenceModifier,"Armor modifier", getMagicDefence(), AttributeModifier.Operation.ADDITION));
		}
		return multimap;
	}
}
