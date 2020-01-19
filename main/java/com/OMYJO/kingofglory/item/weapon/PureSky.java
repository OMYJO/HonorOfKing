package com.OMYJO.kingofglory.item.weapon;

import com.OMYJO.kingofglory.other.KingOfMaterial;
import com.OMYJO.kingofglory.other.SharedKingAttributes;
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
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
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

public class PureSky extends KingOfWeapon
{
	private float attackSpeed = 0.40F;
	private float criticalChance = 0.20F;
	private final HashMap<EquipmentSlotType, UUID> attackSpeedModifierMap = new HashMap<>();
	private final HashMap<EquipmentSlotType, UUID> criticalChanceModifierMap = new HashMap<>();

	public PureSky()
	{
		super(new KingOfMaterial(), Rarity.RARE);
		attackSpeedModifierMap.put(EquipmentSlotType.MAINHAND,UUID.randomUUID());
		attackSpeedModifierMap.put(EquipmentSlotType.OFFHAND,UUID.randomUUID());
		criticalChanceModifierMap.put(EquipmentSlotType.MAINHAND,UUID.randomUUID());
		criticalChanceModifierMap.put(EquipmentSlotType.OFFHAND,UUID.randomUUID());
		setRegistryName("pure_sky");
	}

	@Override
	public float getAttackSpeed() { return attackSpeed; }

	@Override
	public float getCriticalChance() { return criticalChance; }

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
		tooltip.add(new TranslationTextComponent("item_effect"+"."+getRegistryName().getNamespace() + "." + getRegistryName().getPath() +".1"));
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
		if(stack.getDamage() * 4 < stack.getMaxDamage())
		{
			PlayerEntity playerentity = context.getPlayer();
			playerentity.addPotionEffect(new EffectInstance(Effects.DISPELLING,30));
			stack.damageItem(stack.getMaxDamage()*3/4, playerentity, (p_220009_1_) -> {
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
