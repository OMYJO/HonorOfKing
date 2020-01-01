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

public class RevitalizingCrystal extends KingOfWeapon
{
	private float HPPer5Second = Helper.maxHealth(30);
	private final HashMap<EquipmentSlotType, UUID> HPPer5SecondsModifierMap = new HashMap<>();

	public RevitalizingCrystal()
	{
		super(new KingOfMaterial(), Rarity.COMMON);
		HPPer5SecondsModifierMap.put(EquipmentSlotType.MAINHAND, UUID.randomUUID());
		HPPer5SecondsModifierMap.put(EquipmentSlotType.OFFHAND, UUID.randomUUID());
		setRegistryName("revitalizing_crystal");
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
	public float getHPPer5Seconds()
	{
		return HPPer5Second;
	}

	@Override
	public Multimap<String, AttributeModifier> getAttributeModifiers(EquipmentSlotType equipmentSlot)
	{
		Multimap<String, AttributeModifier> multimap = super.getAttributeModifiers(equipmentSlot);
		if (equipmentSlot == EquipmentSlotType.MAINHAND || equipmentSlot == EquipmentSlotType.OFFHAND)
		{
			multimap.put(SharedKingAttributes.HP_PER_5_SECONDS.getName(), new AttributeModifier(HPPer5SecondsModifierMap.get(equipmentSlot), "Weapon modifier", getHPPer5Seconds(), AttributeModifier.Operation.ADDITION));
		}
		return multimap;
	}
}
