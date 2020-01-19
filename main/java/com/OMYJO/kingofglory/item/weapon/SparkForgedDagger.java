package com.OMYJO.kingofglory.item.weapon;

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
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class SparkForgedDagger extends KingOfWeapon
{
	private float attackSpeed = 0.35F;
	private float criticalChance = 0.15F;
	private float movementSpeed = 0.08F;
	private final HashMap<EquipmentSlotType, UUID> attackSpeedModifierMap = new HashMap<>();
	private final HashMap<EquipmentSlotType, UUID> criticalChanceModifierMap = new HashMap<>();
	private final HashMap<EquipmentSlotType, UUID> movementSpeedModifierMap = new HashMap<>();

	public SparkForgedDagger()
	{
		super(new KingOfMaterial(), Rarity.RARE);
		attackSpeedModifierMap.put(EquipmentSlotType.MAINHAND,UUID.randomUUID());
		attackSpeedModifierMap.put(EquipmentSlotType.OFFHAND,UUID.randomUUID());
		criticalChanceModifierMap.put(EquipmentSlotType.MAINHAND,UUID.randomUUID());
		criticalChanceModifierMap.put(EquipmentSlotType.OFFHAND,UUID.randomUUID());
		movementSpeedModifierMap.put(EquipmentSlotType.MAINHAND,UUID.randomUUID());
		movementSpeedModifierMap.put(EquipmentSlotType.OFFHAND,UUID.randomUUID());
		setRegistryName("spark_forged_dagger");
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
	public float getMovementSpeed()
	{
		return movementSpeed;
	}

	@Override
	public Multimap<String, AttributeModifier> getAttributeModifiers(EquipmentSlotType equipmentSlot)
	{
		Multimap<String, AttributeModifier> multimap = super.getAttributeModifiers(equipmentSlot);
		if(equipmentSlot == EquipmentSlotType.MAINHAND || equipmentSlot == EquipmentSlotType.OFFHAND)
		{
			multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(attackSpeedModifierMap.get(equipmentSlot), "Weapon modifier", getAttackSpeed(), AttributeModifier.Operation.MULTIPLY_BASE));
			multimap.put(SharedKingAttributes.CRITICAL_CHANCE.getName(), new AttributeModifier(criticalChanceModifierMap.get(equipmentSlot), "Weapon modifier", getCriticalChance(), AttributeModifier.Operation.ADDITION));
			multimap.put(SharedMonsterAttributes.MOVEMENT_SPEED.getName(), new AttributeModifier(movementSpeedModifierMap.get(equipmentSlot), "Weapon modifier",getMovementSpeed(), AttributeModifier.Operation.MULTIPLY_BASE));
		}
		return multimap;
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
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
	{
		super.addInformation(stack, worldIn, tooltip, flagIn);
		tooltip.add(new TranslationTextComponent("item_effect"+"."+getRegistryName().getNamespace() + "." + getRegistryName().getPath() +".0"));
	}
}
