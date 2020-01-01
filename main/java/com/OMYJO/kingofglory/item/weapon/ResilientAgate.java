package com.OMYJO.kingofglory.item.weapon;

import com.OMYJO.kingofglory.other.Helper;
import com.OMYJO.kingofglory.other.KingOfMaterial;
import com.google.common.collect.Multimap;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class ResilientAgate extends KingOfWeapon
{
	private float maxHealth = Helper.maxHealth(300);
	private final HashMap<EquipmentSlotType, UUID> maxHealthModifierMap = new HashMap<>();

	public ResilientAgate()
	{
		super(new KingOfMaterial(), Rarity.COMMON);
		maxHealthModifierMap.put(EquipmentSlotType.MAINHAND, UUID.randomUUID());
		maxHealthModifierMap.put(EquipmentSlotType.OFFHAND, UUID.randomUUID());
		setRegistryName("resilient_agate");
	}

	/**
	 * allows items to add custom lines of information to the mouseover description
	 *
	 * @param stack
	 * @param worldIn
	 * @param tooltip
	 * @param flagIn
	 */
	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
	{
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}

	@Override
	public float getMaxHealth()
	{
		return maxHealth;
	}

	@Override
	public Multimap<String, AttributeModifier> getAttributeModifiers(EquipmentSlotType equipmentSlot)
	{
		Multimap<String, AttributeModifier> multimap = super.getAttributeModifiers(equipmentSlot);
		if (equipmentSlot == EquipmentSlotType.MAINHAND || equipmentSlot == EquipmentSlotType.OFFHAND)
		{
			multimap.put(SharedMonsterAttributes.MAX_HEALTH.getName(), new AttributeModifier(maxHealthModifierMap.get(equipmentSlot), "Weapon modifier", getMaxHealth(), AttributeModifier.Operation.ADDITION));
		}
		return multimap;
	}
}
