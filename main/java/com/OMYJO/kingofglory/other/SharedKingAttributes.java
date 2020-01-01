package com.OMYJO.kingofglory.other;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class SharedKingAttributes extends SharedMonsterAttributes
{
	public static final IAttribute MAGIC_ATTACK = (new RangedAttribute((IAttribute)null, "kingofglory.magicAttack", 0D,0D, 1024.0D)).setDescription("Magic Attack").setShouldWatch(true);
	public static final IAttribute ARMOR = (new RangedAttribute((IAttribute)null, "kingofglory.armor", 200D, 0D, 2048.0D)).setDescription("Armor in King").setShouldWatch(true);
	public static final IAttribute MAGIC_DEFENCE = (new RangedAttribute((IAttribute)null, "kingofglory.magicDefence", 100D, 0D, 2048.0D)).setDescription("Magic Defence").setShouldWatch(true);
	public static final IAttribute CRITICAL_CHANCE = (new RangedAttribute((IAttribute)null, "kingofglory.criticalChance", 0D, 0D, 1D)).setDescription("Critical Chance").setShouldWatch(true);
	public static final IAttribute CRITICAL_DAMAGE = (new RangedAttribute((IAttribute)null, "kingofglory.criticalDamage", 2D, 0D, 2.5D)).setDescription("Critical Damage").setShouldWatch(true);
	public static final IAttribute LIFE_STEAL = (new RangedAttribute((IAttribute)null, "kingofglory.lifeSteal", 0D, 0D, 1D)).setDescription("Life Steal").setShouldWatch(true);
	public static final IAttribute MAGIC_LIFE_STEAL = (new RangedAttribute((IAttribute)null, "kingofglory.magicLifeSteal", 0D, 0D, 1D)).setDescription("Magic Life Steal").setShouldWatch(true);
	public static final IAttribute COOL_DOWN_REDUCTION = (new RangedAttribute((IAttribute)null, "kingofglory.coolDownReduction", 0D, 0D, 0.4D)).setDescription("Cool Down Reduction").setShouldWatch(true);
	public static final IAttribute ARMOR_PIERCE = (new RangedAttribute((IAttribute)null, "kingofglory.armorPierce", 0D, 0D, 1024D)).setDescription("Armor Pierce").setShouldWatch(true);
	public static final IAttribute MAGIC_PIERCE = (new RangedAttribute((IAttribute)null, "kingofglory.magicPierce", 0D, 0D, 1024D)).setDescription("Magic Pierce").setShouldWatch(true);
	public static final IAttribute MAX_MANA = (new RangedAttribute((IAttribute)null, "kingofglory.maxMana", 0D, 0D, 2048D)).setDescription("Max Mana").setShouldWatch(true);
	public static final IAttribute RESISTANCE = (new RangedAttribute((IAttribute)null, "kingofglory.resistance", 0D, 0D, 1D)).setDescription("Resistance").setShouldWatch(true);
	public static final IAttribute MANA_PER_5_SECONDS = (new RangedAttribute((IAttribute)null, "kingofglory.manaPer5Seconds", 1D, 0D, 1024D)).setDescription("Mana/5 seconds").setShouldWatch(true);
	public static final IAttribute HP_PER_5_SECONDS = (new RangedAttribute((IAttribute)null, "kingofglory.HPPer5Seconds", 1D, 0D, 1024D)).setDescription("HP/5 seconds").setShouldWatch(true);

	@SubscribeEvent
	public static void onEntityJoinWorld(EntityJoinWorldEvent event)
	{
		if(event.getEntity() instanceof LivingEntity)
		{
			LivingEntity livingEntity = (LivingEntity) event.getEntity();
			livingEntity.getAttributes().registerAttribute(SharedKingAttributes.MAGIC_ATTACK);
			livingEntity.getAttributes().registerAttribute(SharedKingAttributes.ARMOR);
			livingEntity.getAttributes().registerAttribute(SharedKingAttributes.MAGIC_DEFENCE);
			livingEntity.getAttributes().registerAttribute(SharedKingAttributes.CRITICAL_CHANCE);
			livingEntity.getAttributes().registerAttribute(SharedKingAttributes.CRITICAL_DAMAGE);
			livingEntity.getAttributes().registerAttribute(SharedKingAttributes.LIFE_STEAL);
			livingEntity.getAttributes().registerAttribute(SharedKingAttributes.MAGIC_LIFE_STEAL);
			livingEntity.getAttributes().registerAttribute(SharedKingAttributes.COOL_DOWN_REDUCTION);
			livingEntity.getAttributes().registerAttribute(SharedKingAttributes.ARMOR_PIERCE);
			livingEntity.getAttributes().registerAttribute(SharedKingAttributes.MAGIC_PIERCE);
			livingEntity.getAttributes().registerAttribute(SharedKingAttributes.MAX_MANA);
			livingEntity.getAttributes().registerAttribute(SharedKingAttributes.RESISTANCE);
			livingEntity.getAttributes().registerAttribute(SharedKingAttributes.MANA_PER_5_SECONDS);
			livingEntity.getAttributes().registerAttribute(SharedKingAttributes.HP_PER_5_SECONDS);
		}
	}
}
