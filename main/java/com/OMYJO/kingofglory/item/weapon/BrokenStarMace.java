package com.OMYJO.kingofglory.item.weapon;

import com.OMYJO.kingofglory.item.bow.CloudPiercingBow;
import com.OMYJO.kingofglory.other.Convertor;
import com.OMYJO.kingofglory.other.KingOfMaterial;
import com.OMYJO.kingofglory.other.SharedKingAttributes;
import com.google.common.collect.Multimap;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class BrokenStarMace extends KingOfWeapon
{
	private float attackDamage = Convertor.attackDamage(80);
	private float coolDownReduction = 0.1F;
	private float armorPierce = 0.4F;
	private final HashMap<EquipmentSlotType, UUID> attackDamageModifierMap = new HashMap<>();
	private final HashMap<EquipmentSlotType, UUID> coolDownReductionModifierMap = new HashMap<>();
	public static final UUID ARMOR_BREAKING_MODIFIER = CloudPiercingBow.ARMOR_BREAKING_MODIFIER;

	public BrokenStarMace()
	{
		super(new KingOfMaterial(), Rarity.UNCOMMON);
		attackDamageModifierMap.put(EquipmentSlotType.MAINHAND,UUID.randomUUID());
		attackDamageModifierMap.put(EquipmentSlotType.OFFHAND,UUID.randomUUID());
		coolDownReductionModifierMap.put(EquipmentSlotType.MAINHAND,UUID.randomUUID());
		coolDownReductionModifierMap.put(EquipmentSlotType.OFFHAND,UUID.randomUUID());
		setRegistryName("broken_star_mace");
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
		tooltip.add(new TextComponent()
		{
			@Override
			public String getUnformattedComponentText()
			{
				return I18n.format("item_effect"+"."+getRegistryName().getNamespace() + "." + getRegistryName().getPath() +".0");
			}

			@Override
			public ITextComponent shallowCopy()
			{
				return null;
			}
		});
	}

	@Override
	public float getAttackDamage()
	{
		return attackDamage;
	}

	@Override
	public float getCoolDownReduction()
	{
		return coolDownReduction;
	}

	@Override
	public float getCriticalDamage()
	{
		return armorPierce;
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
			multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(attackDamageModifierMap.get(equipmentSlot), "Weapon modifier", getAttackDamage(), AttributeModifier.Operation.ADDITION));
			multimap.put(SharedKingAttributes.COOL_DOWN_REDUCTION.getName(), new AttributeModifier(coolDownReductionModifierMap.get(equipmentSlot), "Weapon modifier", getCoolDownReduction(), AttributeModifier.Operation.ADDITION));
			multimap.put(SharedKingAttributes.ARMOR_PIERCE.getName(), new AttributeModifier(ARMOR_BREAKING_MODIFIER, "Weapon modifier", this.getCriticalDamage(), AttributeModifier.Operation.ADDITION));
		}
		return multimap;
	}
}
