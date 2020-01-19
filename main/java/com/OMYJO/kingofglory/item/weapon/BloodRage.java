package com.OMYJO.kingofglory.item.weapon;

import com.OMYJO.kingofglory.other.Helper;
import com.OMYJO.kingofglory.other.KingOfMaterial;
import com.OMYJO.kingofglory.potion.Effects;
import com.google.common.collect.Multimap;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.Rarity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class BloodRage extends KingOfWeapon
{
	private float attackDamage = Helper.attackDamage(20);
	private float maxHealth = Helper.maxHealth(1000);
	private final HashMap<EquipmentSlotType, UUID> attackDamageModifierMap = new HashMap<>();
	private final HashMap<EquipmentSlotType, UUID> maxHealthModifierMap = new HashMap<>();

	public BloodRage()
	{
		super(new KingOfMaterial(), Rarity.UNCOMMON);
		attackDamageModifierMap.put(EquipmentSlotType.MAINHAND,UUID.randomUUID());
		attackDamageModifierMap.put(EquipmentSlotType.OFFHAND,UUID.randomUUID());
		maxHealthModifierMap.put(EquipmentSlotType.MAINHAND,UUID.randomUUID());
		maxHealthModifierMap.put(EquipmentSlotType.OFFHAND,UUID.randomUUID());
		setRegistryName("blood_rage");
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
	public float getAttackDamage()
	{
		return attackDamage;
	}

	@Override
	public float getMaxHealth()
	{
		return maxHealth;
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
			multimap.put(SharedMonsterAttributes.MAX_HEALTH.getName(), new AttributeModifier(maxHealthModifierMap.get(equipmentSlot), "Weapon modifier", getMaxHealth(), AttributeModifier.Operation.ADDITION));
		}
		return multimap;
	}

	/**
	 * Called when this item is used when targetting a Block
	 *
	 * @param context
	 */
	@Override
	public ActionResultType onItemUse(ItemUseContext context)
	{
		ItemStack stack = context.getItem();
		if(stack.getDamage() * 2 < stack.getMaxDamage())
		{
			PlayerEntity playerentity = context.getPlayer();
			playerentity.addPotionEffect(new EffectInstance(Effects.BLOOD_RAGE,80));
			stack.damageItem(stack.getMaxDamage()/2, playerentity, (p_220009_1_) -> {
				p_220009_1_.sendBreakAnimation(playerentity.getActiveHand());
			});
			return ActionResultType.SUCCESS;
		}
		else
		{
			return ActionResultType.FAIL;
		}
	}

}
