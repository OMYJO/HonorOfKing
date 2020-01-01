package com.OMYJO.kingofglory.item.armor;

import com.OMYJO.kingofglory.item.KingOfItem;
import com.OMYJO.kingofglory.other.Helper;
import com.OMYJO.kingofglory.other.KingOfMaterial;
import com.OMYJO.kingofglory.other.SharedKingAttributes;
import com.google.common.collect.Multimap;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.item.ArmorStandEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.util.IndirectEntityDamageSource;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class RedLotusCape extends KingOfArmor implements KingOfItem
{
	private float maxHealth = Helper.maxHealth(1000);
	private float armor = 240;
	private final UUID maxHealthModifier = UUID.randomUUID();
	private final UUID armorModifier = UUID.randomUUID();

	public RedLotusCape(EquipmentSlotType slot, String registryName)
	{
		super(new KingOfMaterial().setName("red_lotus_cape"), slot, Rarity.RARE);
		setRegistryName(registryName);
	}

	@Override
	public float getMaxHealth()
	{
		return maxHealth;
	}

	@Override
	public float getArmor() { return armor; }

	/**
	 * Gets a map of item attribute modifiers, used by ItemSword to increase hit damage.
	 *
	 * @param equipmentSlot
	 */
	@Override
	public Multimap<String, AttributeModifier> getAttributeModifiers(EquipmentSlotType equipmentSlot)
	{
		Multimap multimap = super.getAttributeModifiers(equipmentSlot);
		if(equipmentSlot == getEquipmentSlot())
		{
			multimap.put(SharedKingAttributes.MAX_HEALTH.getName(),new AttributeModifier(maxHealthModifier,"Armor modifier", getMaxHealth(), AttributeModifier.Operation.ADDITION));
			multimap.put(SharedKingAttributes.ARMOR.getName(),new AttributeModifier(armorModifier,"Armor modifier", getArmor(), AttributeModifier.Operation.ADDITION));
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
		tooltip.add(new TextComponent()
		{
			@Override
			public String getUnformattedComponentText()
			{
				return I18n.format("item_effect"+"."+getRegistryName().getNamespace() + "." + getRegistryName().getPath() +".0");
			}

			@Override
			public ITextComponent shallowCopy()
			{
				return null;
			}
		});
	}

	/**
	 * Called to tick armor in the armor slot. Override to do something
	 *
	 * @param stack
	 * @param world
	 * @param player
	 */
	@Override
	public void onArmorTick(ItemStack stack, World world, PlayerEntity player)
	{
		super.onArmorTick(stack, world, player);
		long time = world.getDayTime();
		if(time % 20 == 0)
		{
			for (LivingEntity livingentity : player.world.getEntitiesWithinAABB(LivingEntity.class, player.getBoundingBox().grow(2.0D, 0.5D, 2D)))
			{
				if (livingentity != player && !player.isOnSameTeam(livingentity) && (!(livingentity instanceof ArmorStandEntity) || !((ArmorStandEntity) livingentity).hasMarker()) && !(livingentity instanceof AnimalEntity))
				{
					livingentity.attackEntityFrom(new IndirectEntityDamageSource("sacrifice", player, player).setMagicDamage().setDamageBypassesArmor(), 0.02F * player.getMaxHealth());
				}
			}
		}
	}
}
