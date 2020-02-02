package com.OMYJO.kingofglory.other;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.item.ArmorStandEntity;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.entity.monster.EndermanEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.ZombiePigmanEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.SnowGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import java.text.NumberFormat;
import java.util.ArrayList;

public class Helper
{
	public static float attackDamage(float attackDamage)
	{
		return attackDamage / 12.5F;
	}

	public static float maxHealth(float maxHealth) { return maxHealth / 200; }

	public static int maxMana(int maxMana) { return maxMana / 5; }

	public static float movementSpeed(float movementSpeed) { return movementSpeed / 3000; }

	public static double side(float d) { return radius(d) * Math.sqrt(Math.PI) / 2; }

	public static double radius(float d) { return movementSpeed(d) * 20; }

	public static int getEscapeTime(PlayerEntity playerEntity)
	{
		if (playerEntity.world.isRemote())
		{
			return 0;
		}
		if (playerEntity.getRevengeTarget() == null)
		{
			if (playerEntity.ticksExisted - playerEntity.getLastAttackedEntityTime() > 140)
			{
				return playerEntity.ticksExisted - Math.max(playerEntity.getRevengeTimer(), playerEntity.getLastAttackedEntityTime() + 140);
			}
			else
			{
				return 0;
			}
		}
		else
		{
			return 0;
		}
	}

	public static ITextComponent ipanel(LivingEntity livingEntity)
	{
		StringBuffer sb = new StringBuffer();
		ArrayList<ITextComponent> list = new ArrayList<>();
		if (livingEntity == null) return new StringTextComponent("null");
		NumberFormat nfp = NumberFormat.getPercentInstance();
		NumberFormat nfi = NumberFormat.getNumberInstance();
		NumberFormat nff = NumberFormat.getNumberInstance();
		nfi.setMaximumFractionDigits(0);
		nfp.setMaximumFractionDigits(0);
		nff.setMaximumFractionDigits(2);
		list.add(new StringTextComponent(livingEntity.getName().getFormattedText()));
		//list.add(new StringTextComponent("\n"));
		list.add(new TranslationTextComponent("attribute.name.generic.attackDamage"));
		//list.add(new StringTextComponent(": "));
		list.add(new StringTextComponent(panelFollowed(nff, livingEntity, SharedKingAttributes.ATTACK_DAMAGE)));
		//list.add(new StringTextComponent("   "));
		list.add(new TranslationTextComponent("attribute.name.kingofglory.magicAttack"));
		//list.add(new StringTextComponent(": "));
		list.add(new StringTextComponent(nfi.format(livingEntity.getAttributes().getAttributeInstance(SharedKingAttributes.MAGIC_ATTACK).getValue())));
		//list.add(new StringTextComponent("\n"));
		list.add(new TranslationTextComponent("attribute.name.generic.maxHealth"));
		//list.add(new StringTextComponent(": "));
		list.add(new StringTextComponent(nff.format(livingEntity.getHealth())));
		//list.add(new StringTextComponent("/"));
		list.add(new StringTextComponent(nff.format(livingEntity.getAttributes().getAttributeInstance(SharedKingAttributes.MAX_HEALTH).getValue())));
		//list.add(new StringTextComponent("   "));
		list.add(new TranslationTextComponent("attribute.name.kingofglory.maxMana"));
		//list.add(new StringTextComponent(": "));
		list.add(new StringTextComponent(nfi.format(livingEntity.getAttributes().getAttributeInstance(SharedKingAttributes.MAX_MANA).getValue())));
		//list.add(new StringTextComponent("\n"));
		list.add(new TranslationTextComponent("attribute.name.kingofglory.armor"));
		//list.add(new StringTextComponent(": "));
		list.add(new StringTextComponent(nfi.format(livingEntity.getAttributes().getAttributeInstance(SharedKingAttributes.ARMOR).getValue())));
		//list.add(new StringTextComponent("   "));
		list.add(new TranslationTextComponent("attribute.name.kingofglory.magicDefence"));
		//list.add(new StringTextComponent(": "));
		list.add(new StringTextComponent(nfi.format(livingEntity.getAttributes().getAttributeInstance(SharedKingAttributes.MAGIC_DEFENCE).getValue())));
		//list.add(new StringTextComponent("\n"));
		list.add(new TranslationTextComponent("attribute.name.generic.attackSpeed"));
		//list.add(new StringTextComponent(": "));
		list.add(new StringTextComponent(panelFollowed(nff, livingEntity, SharedKingAttributes.ATTACK_SPEED)));
		//list.add(new StringTextComponent("   "));
		list.add(new TranslationTextComponent("attribute.name.kingofglory.coolDownReduction"));
		//list.add(new StringTextComponent(": "));
		list.add(new StringTextComponent(nfp.format(livingEntity.getAttributes().getAttributeInstance(SharedKingAttributes.COOL_DOWN_REDUCTION).getValue())));
		//list.add(new StringTextComponent("\n"));
		list.add(new TranslationTextComponent("attribute.name.kingofglory.criticalChance"));
		//list.add(new StringTextComponent(": "));
		list.add(new StringTextComponent(nfp.format(livingEntity.getAttributes().getAttributeInstance(SharedKingAttributes.CRITICAL_CHANCE).getValue())));
		//list.add(new StringTextComponent("   "));
		list.add(new TranslationTextComponent("attribute.name.kingofglory.criticalDamage"));
		//list.add(new StringTextComponent(": "));
		list.add(new StringTextComponent(nfp.format(livingEntity.getAttributes().getAttributeInstance(SharedKingAttributes.CRITICAL_DAMAGE).getValue())));
		//list.add(new StringTextComponent("\n"));
		list.add(new TranslationTextComponent("attribute.name.kingofglory.HPPer5Seconds"));
		//list.add(new StringTextComponent(": "));
		list.add(new StringTextComponent(nff.format(livingEntity.getAttributes().getAttributeInstance(SharedKingAttributes.HP_PER_5_SECONDS).getValue())));
		//list.add(new StringTextComponent("   "));
		list.add(new TranslationTextComponent("attribute.name.kingofglory.manaPer5Seconds"));
		//list.add(new StringTextComponent(": "));
		list.add(new StringTextComponent(nff.format(livingEntity.getAttributes().getAttributeInstance(SharedKingAttributes.MANA_PER_5_SECONDS).getValue())));
		//list.add(new StringTextComponent("\n"));
		list.add(new TranslationTextComponent("attribute.name.kingofglory.armorPierce"));
		//list.add(new StringTextComponent(": "));
		list.add(new StringTextComponent(nfi.format((int) livingEntity.getAttributes().getAttributeInstance(SharedKingAttributes.ARMOR_PIERCE).getValue())));
		//list.add(new StringTextComponent("|"));
		list.add(new StringTextComponent(nfp.format(livingEntity.getAttributes().getAttributeInstance(SharedKingAttributes.ARMOR_PIERCE).getValue() - (int) livingEntity.getAttributes().getAttributeInstance(SharedKingAttributes.ARMOR_PIERCE).getValue())));
		//list.add(new StringTextComponent("   "));
		list.add(new TranslationTextComponent("attribute.name.kingofglory.magicPierce"));
		//list.add(new StringTextComponent(": "));
		list.add(new StringTextComponent(nfi.format((int) livingEntity.getAttributes().getAttributeInstance(SharedKingAttributes.MAGIC_PIERCE).getValue())));
		//list.add(new StringTextComponent("|"));
		list.add(new StringTextComponent(nfp.format(livingEntity.getAttributes().getAttributeInstance(SharedKingAttributes.MAGIC_PIERCE).getValue() - (int) livingEntity.getAttributes().getAttributeInstance(SharedKingAttributes.MAGIC_PIERCE).getValue())));
		//list.add(new StringTextComponent("\n"));
		list.add(new TranslationTextComponent("attribute.name.kingofglory.lifeSteal"));
		//list.add(new StringTextComponent(": "));
		list.add(new StringTextComponent(nfp.format(livingEntity.getAttributes().getAttributeInstance(SharedKingAttributes.LIFE_STEAL).getValue())));
		//list.add(new StringTextComponent("   "));
		list.add(new TranslationTextComponent("attribute.name.kingofglory.magicLifeSteal"));
		//list.add(new StringTextComponent(": "));
		list.add(new StringTextComponent(nfp.format(livingEntity.getAttributes().getAttributeInstance(SharedKingAttributes.MAGIC_LIFE_STEAL).getValue())));
		//list.add(new StringTextComponent("\n"));
		list.add(new TranslationTextComponent("attribute.name.generic.movementSpeed"));
		//list.add(new StringTextComponent(": "));
		list.add(new StringTextComponent(nff.format(livingEntity.getAttributes().getAttributeInstance(SharedKingAttributes.MOVEMENT_SPEED).getValue())));
		//list.add(new StringTextComponent("   "));
		list.add(new TranslationTextComponent("attribute.name.kingofglory.resistance"));
		//list.add(new StringTextComponent(": "));
		list.add(new StringTextComponent(nfp.format(livingEntity.getAttributes().getAttributeInstance(SharedKingAttributes.RESISTANCE).getValue())));
		return new TranslationTextComponent("commands.panel", list.toArray());
	}

	private static String panelFollowed(NumberFormat nf, LivingEntity livingEntity, IAttribute attribute)
	{
		if (livingEntity.getAttributes().getAttributeInstance(attribute) == null)
		{
			return "null";
		}
		else
		{
			return nf.format(livingEntity.getAttributes().getAttributeInstance(attribute).getValue());
		}
	}

	public static boolean isEnemy(LivingEntity livingentity, LivingEntity attacker, LivingEntity target)
	{
		if(livingentity != attacker && livingentity != target && !attacker.isOnSameTeam(livingentity) && (!(livingentity instanceof ArmorStandEntity) || !((ArmorStandEntity) livingentity).hasMarker()) && !(livingentity instanceof AnimalEntity))
		{
			if(attacker instanceof PlayerEntity)
			{
				if(livingentity instanceof AbstractVillagerEntity)
				{
					return false;
				}
				else if(livingentity instanceof IronGolemEntity)
				{
					return false;
				}
				else if(livingentity instanceof SnowGolemEntity)
				{
					return false;
				}
				else if(livingentity instanceof ZombiePigmanEntity || livingentity instanceof EndermanEntity)
				{
					if(((MonsterEntity) livingentity).getAttackTarget() != attacker)
					{
						return false;
					}
				}
			}
			else if(attacker instanceof MonsterEntity)
			{
				if(livingentity instanceof MonsterEntity)
				{
					return false;
				}
			}
			return true;
		}
		return false;
	}
}
