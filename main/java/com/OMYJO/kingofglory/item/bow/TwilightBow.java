package com.OMYJO.kingofglory.item.bow;

import com.OMYJO.kingofglory.other.KingOfMaterial;
import com.OMYJO.kingofglory.other.SharedKingAttributes;
import com.OMYJO.kingofglory.potion.Effects;
import com.google.common.collect.Multimap;
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
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class TwilightBow extends KingOfBow
{
	private float attackSpeed = 0.25F;
	private float criticalChance = 0.15F;
	private final HashMap<EquipmentSlotType, UUID> criticalChanceModifierMap = new HashMap<>();
	private final HashMap<EquipmentSlotType, UUID> attackSpeedModifierMap = new HashMap<>();

	public TwilightBow()
	{
		super(new KingOfMaterial(),Rarity.RARE);
		criticalChanceModifierMap.put(EquipmentSlotType.MAINHAND,UUID.randomUUID());
		criticalChanceModifierMap.put(EquipmentSlotType.OFFHAND,UUID.randomUUID());
		attackSpeedModifierMap.put(EquipmentSlotType.MAINHAND,UUID.randomUUID());
		attackSpeedModifierMap.put(EquipmentSlotType.OFFHAND,UUID.randomUUID());
		setRegistryName("twilight_bow");
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
	public float getAttackSpeed()
	{
		return attackSpeed;
	}

	@Override
	public float getCriticalChance() { return criticalChance; }

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
			multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(attackSpeedModifierMap.get(equipmentSlot), "Weapon modifier", getAttackSpeed(), AttributeModifier.Operation.MULTIPLY_BASE));
			multimap.put(SharedKingAttributes.CRITICAL_CHANCE.getName(), new AttributeModifier(criticalChanceModifierMap.get(equipmentSlot), "Weapon modifier", getCriticalChance(), AttributeModifier.Operation.ADDITION));
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
			playerentity.addPotionEffect(new EffectInstance(Effects.CHASING_SUN,100));
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
