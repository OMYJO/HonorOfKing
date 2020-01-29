package com.OMYJO.kingofglory.other;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.world.chunk.storage.ChunkLoader;
import net.minecraft.world.chunk.storage.ChunkLoaderUtil;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class SharedKingAttributes extends SharedMonsterAttributes
{
	public static final IAttribute MAGIC_ATTACK = (new RangedAttribute((IAttribute) null, "kingofglory.magicAttack", 0D, 0D, 1024.0D)).setDescription("Magic Attack").setShouldWatch(true);
	public static final IAttribute ARMOR = (new RangedAttribute((IAttribute) null, "kingofglory.armor", 183D, 0D, 2048.0D)).setDescription("Armor in King").setShouldWatch(true);
	public static final IAttribute MAGIC_DEFENCE = (new RangedAttribute((IAttribute) null, "kingofglory.magicDefence", 183D, 0D, 2048.0D)).setDescription("Magic Defence").setShouldWatch(true);
	public static final IAttribute CRITICAL_CHANCE = (new RangedAttribute((IAttribute) null, "kingofglory.criticalChance", 0D, 0D, 1D)).setDescription("Critical Chance").setShouldWatch(true);
	public static final IAttribute CRITICAL_DAMAGE = (new RangedAttribute((IAttribute) null, "kingofglory.criticalDamage", 2D, 0D, 2.5D)).setDescription("Critical Damage").setShouldWatch(true);
	public static final IAttribute LIFE_STEAL = (new RangedAttribute((IAttribute) null, "kingofglory.lifeSteal", 0D, 0D, 1D)).setDescription("Life Steal").setShouldWatch(true);
	public static final IAttribute MAGIC_LIFE_STEAL = (new RangedAttribute((IAttribute) null, "kingofglory.magicLifeSteal", 0D, 0D, 1D)).setDescription("Magic Life Steal").setShouldWatch(true);
	public static final IAttribute COOL_DOWN_REDUCTION = (new RangedAttribute((IAttribute) null, "kingofglory.coolDownReduction", 0D, 0D, 0.4D)).setDescription("Cool Down Reduction").setShouldWatch(true);
	public static final IAttribute ARMOR_PIERCE = (new RangedAttribute((IAttribute) null, "kingofglory.armorPierce", 0D, 0D, 1024D)).setDescription("Armor Pierce").setShouldWatch(true);
	public static final IAttribute MAGIC_PIERCE = (new RangedAttribute((IAttribute) null, "kingofglory.magicPierce", 0D, 0D, 1024D)).setDescription("Magic Pierce").setShouldWatch(true);
	public static final IAttribute MAX_MANA = (new RangedAttribute((IAttribute) null, "kingofglory.maxMana", 0D, 0D, 2048D)).setDescription("Max Mana").setShouldWatch(true);
	public static final IAttribute RESISTANCE = (new RangedAttribute((IAttribute) null, "kingofglory.resistance", 0D, 0D, 1D)).setDescription("Resistance").setShouldWatch(true);
	public static final IAttribute MANA_PER_5_SECONDS = (new RangedAttribute((IAttribute) null, "kingofglory.manaPer5Seconds", 6D, 0D, 1024D)).setDescription("Mana/5 seconds").setShouldWatch(true);
	public static final IAttribute HP_PER_5_SECONDS = (new RangedAttribute((IAttribute) null, "kingofglory.HPPer5Seconds", 0.6D, 0D, 1024D)).setDescription("HP/5 seconds").setShouldWatch(true);

	@SubscribeEvent
	public static void onEntityJoinWorld(EntityJoinWorldEvent event)
	{
		if (event.getEntity() instanceof LivingEntity)
		{
			LivingEntity livingEntity = (LivingEntity) event.getEntity();
			registerAttributeSafely(livingEntity, SharedKingAttributes.MAGIC_ATTACK);
			registerAttributeSafely(livingEntity, SharedKingAttributes.ARMOR);
			registerAttributeSafely(livingEntity, SharedKingAttributes.MAGIC_DEFENCE);
			registerAttributeSafely(livingEntity, SharedKingAttributes.CRITICAL_CHANCE);
			registerAttributeSafely(livingEntity, SharedKingAttributes.CRITICAL_DAMAGE);
			registerAttributeSafely(livingEntity, SharedKingAttributes.LIFE_STEAL);
			registerAttributeSafely(livingEntity, SharedKingAttributes.MAGIC_LIFE_STEAL);
			registerAttributeSafely(livingEntity, SharedKingAttributes.COOL_DOWN_REDUCTION);
			registerAttributeSafely(livingEntity, SharedKingAttributes.ARMOR_PIERCE);
			registerAttributeSafely(livingEntity, SharedKingAttributes.MAGIC_PIERCE);
			registerAttributeSafely(livingEntity, SharedKingAttributes.MAX_MANA);
			registerAttributeSafely(livingEntity, SharedKingAttributes.RESISTANCE);
			registerAttributeSafely(livingEntity, SharedKingAttributes.MANA_PER_5_SECONDS);
			registerAttributeSafely(livingEntity, SharedKingAttributes.HP_PER_5_SECONDS);
			if (livingEntity instanceof MobEntity)
			{
				livingEntity.getAttributes().getAttributeInstance(SharedKingAttributes.ARMOR).setBaseValue(183 * Math.log10(livingEntity.getMaxHealth()));
				livingEntity.getAttributes().getAttributeInstance(SharedKingAttributes.MAGIC_DEFENCE).setBaseValue(183 * Math.log10(livingEntity.getMaxHealth()));
			}
		}
	}

	public static void registerAttributeSafely(LivingEntity livingEntity, IAttribute attribute)
	{
		if (livingEntity.getAttributes().getAttributeInstance(attribute) == null)
		{
			livingEntity.getAttributes().registerAttribute(attribute);
		}
	}
}
