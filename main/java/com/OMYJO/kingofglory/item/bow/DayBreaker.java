package com.OMYJO.kingofglory.item.bow;

import com.OMYJO.kingofglory.other.Helper;
import com.OMYJO.kingofglory.other.KingOfMaterial;
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

public class DayBreaker extends KingOfBow
{
	private float attackDamage = Helper.attackDamage(50);
	private float attackSpeed = 0.35F;
	private float criticalChance = 0.1F;
	private float armorPierce = 0.2F;
	private final HashMap<EquipmentSlotType, UUID> attackDamageModifierMap = new HashMap<>();
	private final HashMap<EquipmentSlotType, UUID> attackSpeedModifierMap = new HashMap<>();
	private final HashMap<EquipmentSlotType, UUID> criticalChanceModifierMap = new HashMap<>();
	public static final UUID ARMOR_BREAKING_MODIFIER = CloudPiercingBow.ARMOR_BREAKING_MODIFIER;

	public DayBreaker()
	{
		super(new KingOfMaterial(), Rarity.RARE);
		attackDamageModifierMap.put(EquipmentSlotType.MAINHAND,UUID.randomUUID());
		attackDamageModifierMap.put(EquipmentSlotType.OFFHAND,UUID.randomUUID());
		attackSpeedModifierMap.put(EquipmentSlotType.MAINHAND,UUID.randomUUID());
		attackSpeedModifierMap.put(EquipmentSlotType.OFFHAND,UUID.randomUUID());
		criticalChanceModifierMap.put(EquipmentSlotType.MAINHAND,UUID.randomUUID());
		criticalChanceModifierMap.put(EquipmentSlotType.OFFHAND,UUID.randomUUID());
		setRegistryName("day_breaker");
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
		tooltip.add(new TranslationTextComponent("item_effect"+"."+getRegistryName().getNamespace() + "." + getRegistryName().getPath() +".1"));
	}

	@Override
	public float getAttackDamage()
	{
		return attackDamage;
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
	public float getArmorPierce() { return armorPierce; }

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
			multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(attackDamageModifierMap.get(equipmentSlot), "Weapon modifier", getAttackDamage(), AttributeModifier.Operation.ADDITION));
			multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(attackSpeedModifierMap.get(equipmentSlot), "Weapon modifier", getAttackSpeed(), AttributeModifier.Operation.MULTIPLY_BASE));
			multimap.put(SharedKingAttributes.CRITICAL_CHANCE.getName(), new AttributeModifier(criticalChanceModifierMap.get(equipmentSlot), "Weapon modifier", getCriticalChance(), AttributeModifier.Operation.ADDITION));
		}
		if(equipmentSlot == EquipmentSlotType.MAINHAND)
		{
			multimap.put(SharedKingAttributes.ARMOR_PIERCE.getName(), new AttributeModifier(ARMOR_BREAKING_MODIFIER, "Weapon modifier", getArmorPierce()*2, AttributeModifier.Operation.ADDITION));
		}
		else if(equipmentSlot == EquipmentSlotType.OFFHAND)
		{
			multimap.put(SharedKingAttributes.ARMOR_PIERCE.getName(), new AttributeModifier(ARMOR_BREAKING_MODIFIER, "Weapon modifier", getArmorPierce(), AttributeModifier.Operation.ADDITION));
		}
		return multimap;
	}

}
