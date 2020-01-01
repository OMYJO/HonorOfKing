package com.OMYJO.kingofglory.item.armor;

import com.OMYJO.kingofglory.item.KingOfItem;
import com.OMYJO.kingofglory.other.Convertor;
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

public class OverloadsPlatemail extends KingOfArmor implements KingOfItem
{
	private float maxHealth = Convertor.maxHealth(2000);
	private float HPPer5Seconds = Convertor.maxHealth(100);
	private final UUID maxHealthModifier = UUID.randomUUID();
	private final UUID HPPer5SecondsModifier = UUID.randomUUID();

	public OverloadsPlatemail(EquipmentSlotType slot, String registryName)
	{
		super(new KingOfMaterial().setName("overloads_platemail"), slot, Rarity.RARE);
		setRegistryName(registryName);
	}

	@Override
	public float getMaxHealth()
	{
		return maxHealth;
	}

	@Override
	public float getHPPer5Seconds()
	{
		return HPPer5Seconds;
	}

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
			multimap.put(SharedKingAttributes.HP_PER_5_SECONDS.getName(),new AttributeModifier(HPPer5SecondsModifier,"Armor modifier", getHPPer5Seconds(), AttributeModifier.Operation.ADDITION));
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
		if(time % 20 == 0 && player.getRevengeTarget() == null)
		{
			player.heal(player.getMaxHealth() * 0.03F);
		}
	}
}
