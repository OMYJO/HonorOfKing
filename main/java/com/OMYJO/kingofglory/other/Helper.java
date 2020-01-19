package com.OMYJO.kingofglory.other;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.TranslationTextComponent;

import java.text.NumberFormat;

public class Helper
{
	public static float attackDamage(float attackDamage)
	{
		return attackDamage/12;
	}
	public static float maxHealth(float maxHealth) { return maxHealth/200; }
	public static int maxMana(int maxMana) { return maxMana/5; }
	public static float movementSpeed(float movementSpeed){return movementSpeed/3000; }
	public static double distance(float d){return movementSpeed(d)*20*Math.sqrt(Math.PI);}
	public static int getEscapeTime(PlayerEntity playerEntity)
	{
		if(playerEntity.world.isRemote())
		{
			return 0;
		}
		if(playerEntity.getRevengeTarget() == null)
		{
			if(playerEntity.ticksExisted - playerEntity.getLastAttackedEntityTime() >140)
			{
				return playerEntity.ticksExisted - Math.max(playerEntity.getRevengeTimer(),playerEntity.getLastAttackedEntityTime()+140);
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
	public static String panel(LivingEntity livingEntity)
	{
		if(livingEntity == null) return "null";
		NumberFormat nfp = NumberFormat.getPercentInstance();
		NumberFormat nfi = NumberFormat.getNumberInstance();
		NumberFormat nff = NumberFormat.getNumberInstance();
		nfi.setMaximumFractionDigits(0);
		nfp.setMaximumFractionDigits(0);
		nff.setMaximumFractionDigits(2);
		StringBuffer stringBuffer = new StringBuffer(livingEntity.getName().getFormattedText());
		stringBuffer.append("\n");
		stringBuffer.append(new TranslationTextComponent("attribute.name.generic.attackDamage").getUnformattedComponentText());
		stringBuffer.append(": ");
		stringBuffer.append(panelFollowed(nfi,livingEntity,SharedKingAttributes.ATTACK_DAMAGE));
		stringBuffer.append("   ");
		stringBuffer.append(new TranslationTextComponent("attribute.name.kingofglory.magicAttack").getUnformattedComponentText());
		stringBuffer.append(": ");
		stringBuffer.append(nfi.format(livingEntity.getAttributes().getAttributeInstance(SharedKingAttributes.MAGIC_ATTACK).getValue()));
		stringBuffer.append("\n");
		stringBuffer.append(new TranslationTextComponent("attribute.name.generic.maxHealth").getUnformattedComponentText());
		stringBuffer.append(": ");
		stringBuffer.append(nfi.format(livingEntity.getAttributes().getAttributeInstance(SharedKingAttributes.MAX_HEALTH).getValue()));
		stringBuffer.append("   ");
		stringBuffer.append(new TranslationTextComponent("attribute.name.kingofglory.maxMana").getUnformattedComponentText());
		stringBuffer.append(": ");
		stringBuffer.append(nfi.format(livingEntity.getAttributes().getAttributeInstance(SharedKingAttributes.MAX_MANA).getValue()));
		stringBuffer.append("\n");
		stringBuffer.append(new TranslationTextComponent("attribute.name.kingofglory.armor").getUnformattedComponentText());
		stringBuffer.append(": ");
		stringBuffer.append(nfi.format(livingEntity.getAttributes().getAttributeInstance(SharedKingAttributes.ARMOR).getValue()));
		stringBuffer.append("   ");
		stringBuffer.append(new TranslationTextComponent("attribute.name.kingofglory.magicDefence").getUnformattedComponentText());
		stringBuffer.append(": ");
		stringBuffer.append(nfi.format(livingEntity.getAttributes().getAttributeInstance(SharedKingAttributes.MAGIC_DEFENCE).getValue()));
		stringBuffer.append("\n");
		stringBuffer.append(new TranslationTextComponent("attribute.name.generic.attackSpeed").getUnformattedComponentText());
		stringBuffer.append(": ");
		stringBuffer.append(panelFollowed(nff,livingEntity,SharedKingAttributes.ATTACK_SPEED));
		stringBuffer.append("   ");
		stringBuffer.append(new TranslationTextComponent("attribute.name.kingofglory.coolDownReduction").getUnformattedComponentText());
		stringBuffer.append(": ");
		stringBuffer.append(nfp.format(livingEntity.getAttributes().getAttributeInstance(SharedKingAttributes.COOL_DOWN_REDUCTION).getValue()));
		stringBuffer.append("\n");
		stringBuffer.append(new TranslationTextComponent("attribute.name.kingofglory.criticalChance").getUnformattedComponentText());
		stringBuffer.append(": ");
		stringBuffer.append(nfp.format(livingEntity.getAttributes().getAttributeInstance(SharedKingAttributes.CRITICAL_CHANCE).getValue()));
		stringBuffer.append("   ");
		stringBuffer.append(new TranslationTextComponent("attribute.name.kingofglory.criticalDamage").getUnformattedComponentText());
		stringBuffer.append(": ");
		stringBuffer.append(nfp.format(livingEntity.getAttributes().getAttributeInstance(SharedKingAttributes.CRITICAL_DAMAGE).getValue()));
		stringBuffer.append("\n");
		stringBuffer.append(new TranslationTextComponent("attribute.name.kingofglory.HPPer5Seconds").getUnformattedComponentText());
		stringBuffer.append(": ");
		stringBuffer.append(nff.format(livingEntity.getAttributes().getAttributeInstance(SharedKingAttributes.HP_PER_5_SECONDS).getValue()));
		stringBuffer.append("   ");
		stringBuffer.append(new TranslationTextComponent("attribute.name.kingofglory.manaPer5Seconds").getUnformattedComponentText());
		stringBuffer.append(": ");
		stringBuffer.append(nff.format(livingEntity.getAttributes().getAttributeInstance(SharedKingAttributes.MANA_PER_5_SECONDS).getValue()));
		stringBuffer.append("\n");
		stringBuffer.append(new TranslationTextComponent("attribute.name.kingofglory.armorPierce").getUnformattedComponentText());
		stringBuffer.append(": ");
		stringBuffer.append(nfi.format((int)livingEntity.getAttributes().getAttributeInstance(SharedKingAttributes.ARMOR_PIERCE).getValue()));
		stringBuffer.append("|");
		stringBuffer.append(nfp.format(livingEntity.getAttributes().getAttributeInstance(SharedKingAttributes.ARMOR_PIERCE).getValue() - (int)livingEntity.getAttributes().getAttributeInstance(SharedKingAttributes.ARMOR_PIERCE).getValue()));
		stringBuffer.append("   ");
		stringBuffer.append(new TranslationTextComponent("attribute.name.kingofglory.magicPierce").getUnformattedComponentText());
		stringBuffer.append(": ");
		stringBuffer.append(nfi.format((int)livingEntity.getAttributes().getAttributeInstance(SharedKingAttributes.MAGIC_PIERCE).getValue()));
		stringBuffer.append("|");
		stringBuffer.append(nfp.format(livingEntity.getAttributes().getAttributeInstance(SharedKingAttributes.MAGIC_PIERCE).getValue() - (int)livingEntity.getAttributes().getAttributeInstance(SharedKingAttributes.MAGIC_PIERCE).getValue()));
		stringBuffer.append("\n");
		stringBuffer.append(new TranslationTextComponent("attribute.name.kingofglory.lifeSteal").getUnformattedComponentText());
		stringBuffer.append(": ");
		stringBuffer.append(nfp.format(livingEntity.getAttributes().getAttributeInstance(SharedKingAttributes.LIFE_STEAL).getValue()));
		stringBuffer.append("   ");
		stringBuffer.append(new TranslationTextComponent("attribute.name.kingofglory.magicLifeSteal").getUnformattedComponentText());
		stringBuffer.append(": ");
		stringBuffer.append(nfp.format(livingEntity.getAttributes().getAttributeInstance(SharedKingAttributes.MAGIC_LIFE_STEAL).getValue()));
		stringBuffer.append("\n");
		stringBuffer.append(new TranslationTextComponent("attribute.name.generic.movementSpeed").getUnformattedComponentText());
		stringBuffer.append(": ");
		stringBuffer.append(nff.format(livingEntity.getAttributes().getAttributeInstance(SharedKingAttributes.MOVEMENT_SPEED).getValue()));
		stringBuffer.append("   ");
		stringBuffer.append(new TranslationTextComponent("attribute.name.kingofglory.resistance").getUnformattedComponentText());
		stringBuffer.append(": ");
		stringBuffer.append(nfp.format(livingEntity.getAttributes().getAttributeInstance(SharedKingAttributes.RESISTANCE).getValue()));
		return stringBuffer.toString();
	}

	private static String panelFollowed(NumberFormat nf, LivingEntity livingEntity, IAttribute attribute)
	{
		if(livingEntity.getAttributes().getAttributeInstance(attribute) == null)
		{
			return "null";
		}
		else
		{
			return nf.format(livingEntity.getAttributes().getAttributeInstance(attribute).getValue());
		}
	}
}
