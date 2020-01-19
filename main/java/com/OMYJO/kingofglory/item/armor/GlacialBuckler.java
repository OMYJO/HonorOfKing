package com.OMYJO.kingofglory.item.armor;

import com.OMYJO.kingofglory.item.KingOfItem;
import com.OMYJO.kingofglory.other.Helper;
import com.OMYJO.kingofglory.other.KingOfMaterial;
import com.OMYJO.kingofglory.other.SharedKingAttributes;
import com.google.common.collect.Multimap;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class GlacialBuckler extends KingOfArmor implements KingOfItem
{
	private float armor = 360F;
	private float coolDownReduction = 0.2F;
	private float mana = Helper.maxMana(500);
	private final UUID ArmorModifier = UUID.randomUUID();
	private final UUID coolDownReductionModifier = UUID.randomUUID();
	private final UUID manaModifier = UUID.randomUUID();

	public GlacialBuckler(EquipmentSlotType slot, String registryName)
	{
		super(new KingOfMaterial().setName("glacial_buckler").addDurability(Helper.maxMana(500)), slot, Rarity.RARE);
		setRegistryName(registryName);
	}

	@Override
	public float getArmor()
	{
		return armor;
	}

	@Override
	public float getCoolDownReduction()
	{
		return coolDownReduction;
	}

	@Override
	public float getMana()
	{
		return mana;
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
			multimap.put(SharedKingAttributes.ARMOR.getName(),new AttributeModifier(ArmorModifier,"Armor modifier",getArmor(), AttributeModifier.Operation.ADDITION));
			multimap.put(SharedKingAttributes.COOL_DOWN_REDUCTION.getName(),new AttributeModifier(coolDownReductionModifier,"Armor modifier",getCoolDownReduction(), AttributeModifier.Operation.ADDITION));
			multimap.put(SharedKingAttributes.MAX_MANA.getName(),new AttributeModifier(manaModifier,"Armor modifier",getMana(), AttributeModifier.Operation.ADDITION));
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
		tooltip.add(new TranslationTextComponent("item_effect"+"."+getRegistryName().getNamespace() + "." + getRegistryName().getPath() +".0"));
	}
}
