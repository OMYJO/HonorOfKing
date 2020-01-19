package com.OMYJO.kingofglory.item.weapon;

import com.OMYJO.kingofglory.other.KingOfMaterial;
import com.OMYJO.kingofglory.other.SharedKingAttributes;
import com.google.common.collect.Multimap;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class SagesSanctuary extends KingOfWeapon
{

	private float armor = 140;
	private float magicDefence = 140;
	private final HashMap<EquipmentSlotType, UUID> armorModifierMap = new HashMap<>();
	private final HashMap<EquipmentSlotType, UUID> magicDefenceModifierMap = new HashMap<>();

	public SagesSanctuary()
	{
		super(new KingOfMaterial(), Rarity.RARE);
		armorModifierMap.put(EquipmentSlotType.MAINHAND,UUID.randomUUID());
		armorModifierMap.put(EquipmentSlotType.OFFHAND,UUID.randomUUID());
		magicDefenceModifierMap.put(EquipmentSlotType.MAINHAND,UUID.randomUUID());
		magicDefenceModifierMap.put(EquipmentSlotType.OFFHAND,UUID.randomUUID());
		setRegistryName("sages_sanctuary");
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
		tooltip.add(new TranslationTextComponent("item_effect"+"."+getRegistryName().getNamespace() + "." + getRegistryName().getPath() +".0"));
	}

	@Override
	public float getArmor()
	{
		return armor;
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
		Multimap<String, AttributeModifier> multimap = super.getAttributeModifiers(equipmentSlot);
		if(equipmentSlot == EquipmentSlotType.MAINHAND || equipmentSlot == EquipmentSlotType.OFFHAND)
		{
			multimap.put(SharedKingAttributes.ARMOR.getName(), new AttributeModifier(armorModifierMap.get(equipmentSlot), "Weapon modifier", getArmor(), AttributeModifier.Operation.ADDITION));
			multimap.put(SharedKingAttributes.MAGIC_DEFENCE.getName(), new AttributeModifier(magicDefenceModifierMap.get(equipmentSlot), "Weapon modifier", getMagicDefence(), AttributeModifier.Operation.ADDITION));
		}
		return multimap;
	}
}
