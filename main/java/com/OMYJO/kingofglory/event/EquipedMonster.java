package com.OMYJO.kingofglory.event;

import com.OMYJO.kingofglory.item.Items;
import com.OMYJO.kingofglory.item.weapon.StormSword;
import com.OMYJO.kingofglory.other.SharedKingAttributes;
import com.OMYJO.kingofglory.potion.Effects;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.AirItem;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ChatType;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EquipedMonster
{
	@SubscribeEvent
	public static void onCheckSpawn(LivingSpawnEvent.CheckSpawn event)
	{
		//所有灾厄村民穿副武器
		//骷髅和僵尸给副武器及装备
		if (event.getEntity() instanceof AbstractIllagerEntity || event.getEntity() instanceof ZombieEntity || event.getEntity() instanceof AbstractSkeletonEntity)
		{
			MonsterEntity entity = (MonsterEntity) event.getEntity();
			ItemStack itemStack;
			double a = Math.random();
			if(a < 0.4) itemStack = ItemStack.EMPTY;
			else if(a < 0.6) itemStack = new ItemStack(Items.METEOR);
			else if(a < 0.8) itemStack = new ItemStack(Items.BROKEN_STAR_MACE);
			else itemStack = new ItemStack(Items.NIGHTMARE);
			if(itemStack != ItemStack.EMPTY)
			{
				EquipmentSlotType type = EquipmentSlotType.OFFHAND;
				if (entity instanceof AbstractSkeletonEntity && !(entity instanceof WitherSkeletonEntity))
				{
					if (entity.getHeldItemOffhand() != ItemStack.EMPTY) type = EquipmentSlotType.MAINHAND;
				}
				entity.setItemStackToSlot(type, itemStack);
				entity.setDropChance(type, 0);
			}
			if(entity instanceof ZombieEntity || entity instanceof AbstractSkeletonEntity)
			{
				double b = Math.random();
				if(b < 0.8) itemStack = ItemStack.EMPTY;
				else if(b < 0.9) itemStack = new ItemStack(Items.OMINOUS_PREMONITION);
				else itemStack = new ItemStack(Items.CUIRASS_OF_SAVAGERY);
				if(itemStack != ItemStack.EMPTY)
				{
					EquipmentSlotType type = EquipmentSlotType.HEAD;
					if (entity.getItemStackFromSlot(type).getItem() == net.minecraft.item.Items.AIR)
					{
						entity.setItemStackToSlot(type, itemStack);
						entity.setDropChance(type, 0);
					}
				}

				b = Math.random();
				if(b < 0.8) itemStack = ItemStack.EMPTY;
				else if(b < 0.9) itemStack = new ItemStack(Items.RED_LOTUS_CAPE);
				else itemStack = new ItemStack(Items.SUCCUBUS_CLOAK);
				if(itemStack != ItemStack.EMPTY)
				{
					EquipmentSlotType type = EquipmentSlotType.CHEST;
					if (entity.getItemStackFromSlot(type).getItem() == net.minecraft.item.Items.AIR)
					{
						entity.setItemStackToSlot(type, itemStack);
						entity.setDropChance(type, 0);
					}
				}

				b = Math.random();
				if(b < 0.7) itemStack = ItemStack.EMPTY;
				else if(b < 0.8) itemStack = new ItemStack(Items.SPIKEMAIL);
				else if(b < 0.9) itemStack = new ItemStack(Items.OVERLORDS_PLATEMAIL);
				else itemStack = new ItemStack(Items.GLACIAL_BUCKLER);
				if(itemStack != ItemStack.EMPTY)
				{
					EquipmentSlotType type = EquipmentSlotType.LEGS;
					if (entity.getItemStackFromSlot(type).getItem() == net.minecraft.item.Items.AIR)
					{
						entity.setItemStackToSlot(type, itemStack);
						entity.setDropChance(type, 0);
					}
				}

				b = Math.random();
				if(b < 0.8) itemStack = ItemStack.EMPTY;
				else itemStack = new ItemStack(Items.BOOTS_OF_FORITUDE);
				if(itemStack != ItemStack.EMPTY)
				{
					EquipmentSlotType type = EquipmentSlotType.FEET;
					if (entity.getItemStackFromSlot(type).getItem() == net.minecraft.item.Items.AIR)
					{
						entity.setItemStackToSlot(type, itemStack);
						entity.setDropChance(type, 0);
					}
				}
			}
		}
	}

	@SubscribeEvent
	public static void onGiveBuff(LivingSpawnEvent.CheckSpawn event)
	{
		if(event.getEntityLiving() instanceof GuardianEntity)
		{
			if(Math.random() < 0.2)
			{
				event.getEntityLiving().addPotionEffect(new EffectInstance(Effects.RED_STATUE_POWER,24000));
			}
		}
		if(event.getEntityLiving() instanceof EvokerEntity)
		{
			if(Math.random() < 0.5)
			{
				event.getEntityLiving().addPotionEffect(new EffectInstance(Effects.BLUE_STATUE_POWER,24000));
			}
		}
	}

}
