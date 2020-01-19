package com.OMYJO.kingofglory.other;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

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
	public static ITextComponent ipanel(LivingEntity livingEntity)
	{
		StringBuffer sb = new StringBuffer();
		ArrayList<ITextComponent> list = new ArrayList<>();
		if(livingEntity == null) return new StringTextComponent("null");
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
		list.add(new StringTextComponent(panelFollowed(nff,livingEntity,SharedKingAttributes.ATTACK_DAMAGE)));
		//list.add(new StringTextComponent("   "));
		list.add(new TranslationTextComponent("attribute.name.kingofglory.magicAttack"));
		//list.add(new StringTextComponent(": "));
		list.add(new StringTextComponent(nfi.format(livingEntity.getAttributes().getAttributeInstance(SharedKingAttributes.MAGIC_ATTACK).getValue())));
		//list.add(new StringTextComponent("\n"));
		list.add(new TranslationTextComponent("attribute.name.generic.maxHealth"));
		//list.add(new StringTextComponent(": "));
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
		list.add(new StringTextComponent(panelFollowed(nff,livingEntity,SharedKingAttributes.ATTACK_SPEED)));
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
		list.add(new StringTextComponent(nfi.format((int)livingEntity.getAttributes().getAttributeInstance(SharedKingAttributes.ARMOR_PIERCE).getValue())));
		//list.add(new StringTextComponent("|"));
		list.add(new StringTextComponent(nfp.format(livingEntity.getAttributes().getAttributeInstance(SharedKingAttributes.ARMOR_PIERCE).getValue() - (int)livingEntity.getAttributes().getAttributeInstance(SharedKingAttributes.ARMOR_PIERCE).getValue())));
		//list.add(new StringTextComponent("   "));
		list.add(new TranslationTextComponent("attribute.name.kingofglory.magicPierce"));
		//list.add(new StringTextComponent(": "));
		list.add(new StringTextComponent(nfi.format((int)livingEntity.getAttributes().getAttributeInstance(SharedKingAttributes.MAGIC_PIERCE).getValue())));
		//list.add(new StringTextComponent("|"));
		list.add(new StringTextComponent(nfp.format(livingEntity.getAttributes().getAttributeInstance(SharedKingAttributes.MAGIC_PIERCE).getValue() - (int)livingEntity.getAttributes().getAttributeInstance(SharedKingAttributes.MAGIC_PIERCE).getValue())));
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
		return new TranslationTextComponent("commands.panel",list.toArray());
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
