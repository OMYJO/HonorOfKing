package com.OMYJO.kingofglory.item.weapon;

import com.OMYJO.kingofglory.other.Helper;
import com.OMYJO.kingofglory.other.KingOfItemTier;
import com.OMYJO.kingofglory.other.SharedKingAttributes;
import com.google.common.collect.Multimap;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class SwordOfGlory extends KingOfWeapon
{
	private float maxHealth = Helper.maxHealth(400);
	private float mana = Helper.maxMana(400);
	private final HashMap<EquipmentSlotType, UUID> maxHealthModifierMap = new HashMap<>();
	private final HashMap<EquipmentSlotType, UUID> manaModifierMap = new HashMap<>();

	public SwordOfGlory()
	{
		super(new KingOfItemTier().addMaxUses(Helper.maxMana(400)), Rarity.UNCOMMON);
		maxHealthModifierMap.put(EquipmentSlotType.MAINHAND,UUID.randomUUID());
		maxHealthModifierMap.put(EquipmentSlotType.OFFHAND,UUID.randomUUID());
		manaModifierMap.put(EquipmentSlotType.MAINHAND,UUID.randomUUID());
		manaModifierMap.put(EquipmentSlotType.OFFHAND,UUID.randomUUID());
		setRegistryName("sword_of_glory");
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
	public float getMaxHealth()
	{
		return maxHealth;
	}

	@Override
	public float getMana()
	{
		return mana;
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
			multimap.put(SharedMonsterAttributes.MAX_HEALTH.getName(), new AttributeModifier(maxHealthModifierMap.get(equipmentSlot), "Weapon modifier", getMaxHealth(), AttributeModifier.Operation.ADDITION));
			multimap.put(SharedKingAttributes.MAX_MANA.getName(), new AttributeModifier(manaModifierMap.get(equipmentSlot), "Weapon modifier", getMana(), AttributeModifier.Operation.ADDITION));
		}
		return multimap;
	}
}
