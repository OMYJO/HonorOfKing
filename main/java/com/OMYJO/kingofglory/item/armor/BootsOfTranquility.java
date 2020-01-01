package com.OMYJO.kingofglory.item.armor;

import com.OMYJO.kingofglory.item.KingOfItem;
import com.OMYJO.kingofglory.other.Helper;
import com.OMYJO.kingofglory.other.KingOfMaterial;
import com.OMYJO.kingofglory.other.SharedKingAttributes;
import com.google.common.collect.Multimap;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class BootsOfTranquility extends KingOfArmor implements KingOfItem
{
	private float coolDownReduction = 0.15F;
	private float movementSpeed = Helper.movementSpeed(60);
	private final UUID MOVEMENT_SPEED_MODIFIER = BootsOfDivineSpeed.MOVEMENT_SPEED_MODIFIER;
	private final UUID COOL_DOWN_REDUCTION_MODIFIER = UUID.randomUUID();

	public BootsOfTranquility()
	{
		super(new KingOfMaterial(), EquipmentSlotType.FEET, Rarity.UNCOMMON);
		setRegistryName("boots_of_tranquility");
	}

	@Override
	public float getMovementSpeed()
	{
		return movementSpeed;
	}

	@Override
	public float getCoolDownReduction()
	{
		return coolDownReduction;
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
			multimap.put(SharedMonsterAttributes.MOVEMENT_SPEED.getName(),new AttributeModifier(MOVEMENT_SPEED_MODIFIER,"Armor modifier", getMovementSpeed(), AttributeModifier.Operation.ADDITION));
			multimap.put(SharedKingAttributes.COOL_DOWN_REDUCTION.getName(),new AttributeModifier(COOL_DOWN_REDUCTION_MODIFIER,"Armor modifier",getCoolDownReduction(), AttributeModifier.Operation.ADDITION));
		}
		return multimap;
	}
}
