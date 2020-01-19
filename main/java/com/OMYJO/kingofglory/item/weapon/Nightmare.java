package com.OMYJO.kingofglory.item.weapon;

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

public class Nightmare extends KingOfWeapon
{
	private float attackDamage = Helper.attackDamage(85);
	private float maxHealth = Helper.maxHealth(500);
	private float coolDownReduction = 0.15F;
	private float armorPierce = 170;
	private final HashMap<EquipmentSlotType, UUID> attackDamageModifierMap = new HashMap<>();
	private final HashMap<EquipmentSlotType, UUID> maxHealthModifierMap = new HashMap<>();
	private final HashMap<EquipmentSlotType, UUID> coolDownReductionModifierMap = new HashMap<>();
	public static final UUID DISSECTION_MODIFIER = Meteor.DISSECTION_MODIFIER;

	public Nightmare()
	{
		super(new KingOfMaterial(), Rarity.UNCOMMON);
		attackDamageModifierMap.put(EquipmentSlotType.MAINHAND,UUID.randomUUID());
		attackDamageModifierMap.put(EquipmentSlotType.OFFHAND,UUID.randomUUID());
		maxHealthModifierMap.put(EquipmentSlotType.MAINHAND,UUID.randomUUID());
		maxHealthModifierMap.put(EquipmentSlotType.OFFHAND,UUID.randomUUID());
		coolDownReductionModifierMap.put(EquipmentSlotType.MAINHAND,UUID.randomUUID());
		coolDownReductionModifierMap.put(EquipmentSlotType.OFFHAND,UUID.randomUUID());
		setRegistryName("nightmare");
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
	public float getMaxHealth()
	{
		return maxHealth;
	}

	@Override
	public float getCoolDownReduction()
	{
		return coolDownReduction;
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
			multimap.put(SharedMonsterAttributes.MAX_HEALTH.getName(), new AttributeModifier(maxHealthModifierMap.get(equipmentSlot), "Weapon modifier", getMaxHealth(), AttributeModifier.Operation.ADDITION));
			multimap.put(SharedKingAttributes.COOL_DOWN_REDUCTION.getName(), new AttributeModifier(coolDownReductionModifierMap.get(equipmentSlot), "Weapon modifier", getCoolDownReduction(), AttributeModifier.Operation.ADDITION));
			multimap.put(SharedKingAttributes.ARMOR_PIERCE.getName(), new AttributeModifier(DISSECTION_MODIFIER, "Weapon modifier", this.getArmorPierce(), AttributeModifier.Operation.ADDITION));
		}
		return multimap;
	}
}
