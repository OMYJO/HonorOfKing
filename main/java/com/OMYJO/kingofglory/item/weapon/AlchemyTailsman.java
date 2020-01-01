package com.OMYJO.kingofglory.item.weapon;

import com.OMYJO.kingofglory.other.Helper;
import com.OMYJO.kingofglory.other.KingOfMaterial;
import com.OMYJO.kingofglory.other.SharedKingAttributes;
import com.google.common.collect.Multimap;
import net.minecraft.client.util.ITooltipFlag;
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

public class AlchemyTailsman extends KingOfWeapon
{
	private float manaPer5Seconds = Helper.maxMana(10);
	private final HashMap<EquipmentSlotType, UUID> manaPer5SecondsModifierMap = new HashMap<>();

	public AlchemyTailsman()
	{
		super(new KingOfMaterial(), Rarity.COMMON);
		manaPer5SecondsModifierMap.put(EquipmentSlotType.MAINHAND, UUID.randomUUID());
		manaPer5SecondsModifierMap.put(EquipmentSlotType.OFFHAND, UUID.randomUUID());
		setRegistryName("alchemy_tailsman");
	}

	@Override
	public float getManaPer5Seconds()
	{
		return manaPer5Seconds;
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
	public Multimap<String, AttributeModifier> getAttributeModifiers(EquipmentSlotType equipmentSlot)
	{
		Multimap<String, AttributeModifier> multimap = super.getAttributeModifiers(equipmentSlot);
		if (equipmentSlot == EquipmentSlotType.MAINHAND || equipmentSlot == EquipmentSlotType.OFFHAND)
		{
			multimap.put(SharedKingAttributes.MANA_PER_5_SECONDS.getName(), new AttributeModifier(manaPer5SecondsModifierMap.get(equipmentSlot), "Weapon modifier", getManaPer5Seconds(), AttributeModifier.Operation.ADDITION));
		}
		return multimap;
	}
}
