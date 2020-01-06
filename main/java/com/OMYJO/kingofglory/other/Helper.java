package com.OMYJO.kingofglory.other;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.player.PlayerEntity;
import sun.nio.cs.ext.MacHebrew;

import java.text.NumberFormat;

public class Helper
{
	public static float attackDamage(float attackDamage)
	{
		return attackDamage/10;
	}
	public static float maxHealth(float maxHealth) { return maxHealth/200; }
	public static int maxMana(int maxMana) { return maxMana/5; }
	public static float movementSpeed(float movementSpeed){return movementSpeed/720; }
	public static double distance(float d){return movementSpeed(d)*20*Math.sqrt(Math.PI);}
	public static int getEscapeTime(PlayerEntity playerEntity)
	{
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
		NumberFormat nfp = NumberFormat.getPercentInstance();
		NumberFormat nfi = NumberFormat.getNumberInstance();
		NumberFormat nff = NumberFormat.getNumberInstance();
		nfi.setMaximumFractionDigits(0);
		nfp.setMaximumFractionDigits(0);
		nff.setMaximumFractionDigits(2);
		StringBuffer stringBuffer = new StringBuffer(livingEntity.getName().getFormattedText());
		stringBuffer.append("\n");
		stringBuffer.append(I18n.format("attribute.name.generic.attackDamage"));
		stringBuffer.append(": ");
		stringBuffer.append(panelFollowed(nfi,livingEntity,SharedKingAttributes.ATTACK_DAMAGE));
		stringBuffer.append("   ");
		stringBuffer.append(I18n.format("attribute.name.kingofglory.magicAttack"));
		stringBuffer.append(": ");
		stringBuffer.append(nfi.format(livingEntity.getAttributes().getAttributeInstanceByName(SharedKingAttributes.MAGIC_ATTACK.getName()).getValue()));
		stringBuffer.append("\n");
		stringBuffer.append(I18n.format("attribute.name.generic.maxHealth"));
		stringBuffer.append(": ");
		stringBuffer.append(nfi.format(livingEntity.getAttributes().getAttributeInstanceByName(SharedKingAttributes.MAX_HEALTH.getName()).getValue()));
		stringBuffer.append("   ");
		stringBuffer.append(I18n.format("attribute.name.kingofglory.maxMana"));
		stringBuffer.append(": ");
		stringBuffer.append(nfi.format(livingEntity.getAttributes().getAttributeInstanceByName(SharedKingAttributes.MAX_MANA.getName()).getValue()));
		stringBuffer.append("\n");
		stringBuffer.append(I18n.format("attribute.name.kingofglory.armor"));
		stringBuffer.append(": ");
		stringBuffer.append(nfi.format(livingEntity.getAttributes().getAttributeInstanceByName(SharedKingAttributes.ARMOR.getName()).getValue()));
		stringBuffer.append("   ");
		stringBuffer.append(I18n.format("attribute.name.kingofglory.magicDefence"));
		stringBuffer.append(": ");
		stringBuffer.append(nfi.format(livingEntity.getAttributes().getAttributeInstanceByName(SharedKingAttributes.MAGIC_DEFENCE.getName()).getValue()));
		stringBuffer.append("\n");
		stringBuffer.append(I18n.format("attribute.name.generic.attackSpeed"));
		stringBuffer.append(": ");
		stringBuffer.append(panelFollowed(nff,livingEntity,SharedKingAttributes.ATTACK_SPEED));
		stringBuffer.append("   ");
		stringBuffer.append(I18n.format("attribute.name.kingofglory.coolDownReduction"));
		stringBuffer.append(": ");
		stringBuffer.append(nfp.format(livingEntity.getAttributes().getAttributeInstanceByName(SharedKingAttributes.COOL_DOWN_REDUCTION.getName()).getValue()));
		stringBuffer.append("\n");
		stringBuffer.append(I18n.format("attribute.name.kingofglory.criticalChance"));
		stringBuffer.append(": ");
		stringBuffer.append(nfp.format(livingEntity.getAttributes().getAttributeInstanceByName(SharedKingAttributes.CRITICAL_CHANCE.getName()).getValue()));
		stringBuffer.append("   ");
		stringBuffer.append(I18n.format("attribute.name.kingofglory.criticalDamage"));
		stringBuffer.append(": ");
		stringBuffer.append(nfp.format(livingEntity.getAttributes().getAttributeInstanceByName(SharedKingAttributes.CRITICAL_DAMAGE.getName()).getValue()));
		stringBuffer.append("\n");
		stringBuffer.append(I18n.format("attribute.name.kingofglory.HPPer5Seconds"));
		stringBuffer.append(": ");
		stringBuffer.append(nff.format(livingEntity.getAttributes().getAttributeInstanceByName(SharedKingAttributes.HP_PER_5_SECONDS.getName()).getValue()));
		stringBuffer.append("   ");
		stringBuffer.append(I18n.format("attribute.name.kingofglory.manaPer5Seconds"));
		stringBuffer.append(": ");
		stringBuffer.append(nff.format(livingEntity.getAttributes().getAttributeInstanceByName(SharedKingAttributes.MANA_PER_5_SECONDS.getName()).getValue()));
		stringBuffer.append("\n");
		stringBuffer.append(I18n.format("attribute.name.kingofglory.armorPierce"));
		stringBuffer.append(": ");
		stringBuffer.append(nfi.format((int)livingEntity.getAttributes().getAttributeInstanceByName(SharedKingAttributes.ARMOR_PIERCE.getName()).getValue()));
		stringBuffer.append("|");
		stringBuffer.append(nfp.format(livingEntity.getAttributes().getAttributeInstanceByName(SharedKingAttributes.ARMOR_PIERCE.getName()).getValue() - (int)livingEntity.getAttributes().getAttributeInstanceByName(SharedKingAttributes.ARMOR_PIERCE.getName()).getValue()));
		stringBuffer.append("   ");
		stringBuffer.append(I18n.format("attribute.name.kingofglory.magicPierce"));
		stringBuffer.append(": ");
		stringBuffer.append(nfi.format((int)livingEntity.getAttributes().getAttributeInstanceByName(SharedKingAttributes.MAGIC_PIERCE.getName()).getValue()));
		stringBuffer.append("|");
		stringBuffer.append(nfp.format(livingEntity.getAttributes().getAttributeInstanceByName(SharedKingAttributes.MAGIC_PIERCE.getName()).getValue() - (int)livingEntity.getAttributes().getAttributeInstanceByName(SharedKingAttributes.MAGIC_PIERCE.getName()).getValue()));
		stringBuffer.append("\n");
		stringBuffer.append(I18n.format("attribute.name.kingofglory.lifeSteal"));
		stringBuffer.append(": ");
		stringBuffer.append(nfp.format(livingEntity.getAttributes().getAttributeInstanceByName(SharedKingAttributes.LIFE_STEAL.getName()).getValue()));
		stringBuffer.append("   ");
		stringBuffer.append(I18n.format("attribute.name.kingofglory.magicLifeSteal"));
		stringBuffer.append(": ");
		stringBuffer.append(nfp.format(livingEntity.getAttributes().getAttributeInstanceByName(SharedKingAttributes.MAGIC_LIFE_STEAL.getName()).getValue()));
		stringBuffer.append("\n");
		stringBuffer.append(I18n.format("attribute.name.generic.movementSpeed"));
		stringBuffer.append(": ");
		stringBuffer.append(nff.format(livingEntity.getAttributes().getAttributeInstanceByName(SharedKingAttributes.MOVEMENT_SPEED.getName()).getValue()));
		stringBuffer.append("   ");
		stringBuffer.append(I18n.format("attribute.name.kingofglory.resistance"));
		stringBuffer.append(": ");
		stringBuffer.append(nfp.format(livingEntity.getAttributes().getAttributeInstanceByName(SharedKingAttributes.RESISTANCE.getName()).getValue()));
		return stringBuffer.toString();
	}

	private static String panelFollowed(NumberFormat nf, LivingEntity livingEntity, IAttribute attribute)
	{
		if(livingEntity.getAttributes().getAttributeInstanceByName(attribute.getName()) == null)
		{
			return "null";
		}
		else
		{
			return nf.format(livingEntity.getAttributes().getAttributeInstanceByName(attribute.getName()).getValue());
		}
	}
}
