package com.OMYJO.kingofglory.event;

import com.OMYJO.kingofglory.item.Items;
import com.OMYJO.kingofglory.item.weapon.StormSword;
import com.OMYJO.kingofglory.other.SharedKingAttributes;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.AbstractSkeletonEntity;
import net.minecraft.entity.monster.WitherSkeletonEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
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
	public static void onEntityJoinWorld(EntityJoinWorldEvent event)
	{
		if(event.getEntity() instanceof ZombieEntity)
		{
			ZombieEntity zombieEntity = (ZombieEntity) event.getEntity();
			if(zombieEntity.getHeldItemOffhand() == ItemStack.EMPTY)
			{
				if(Math.random() < 0.5)
				{
					zombieEntity.setItemStackToSlot(EquipmentSlotType.OFFHAND,new ItemStack(Items.METEOR));
				}
				else
				{
					zombieEntity.setItemStackToSlot(EquipmentSlotType.OFFHAND,new ItemStack(Items.STORM_SWORD));
				}
			}
		}
		else if(event.getEntity() instanceof AbstractSkeletonEntity)
		{
			AbstractSkeletonEntity skeletonEntity = (AbstractSkeletonEntity) event.getEntity();
			if(skeletonEntity instanceof WitherSkeletonEntity)
			{
				skeletonEntity.setItemStackToSlot(EquipmentSlotType.MAINHAND,new ItemStack(Items.STORM_SWORD));
			}
			else
			{
				skeletonEntity.setItemStackToSlot(EquipmentSlotType.OFFHAND,new ItemStack(Items.METEOR));
			}
		}
	}
}
