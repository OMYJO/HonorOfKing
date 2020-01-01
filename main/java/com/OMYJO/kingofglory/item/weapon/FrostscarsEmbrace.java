package com.OMYJO.kingofglory.item.weapon;

import com.OMYJO.kingofglory.other.Convertor;
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

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class FrostscarsEmbrace extends KingOfWeapon
{
	private float coolDownReduction = 0.1F;
	private float armor = 240;
	private float maxHealth = Convertor.maxHealth(800);
	private float mana = Convertor.maxMana(500);
	private final HashMap<EquipmentSlotType, UUID> coolDownReductionModifierMap = new HashMap<>();
	private final HashMap<EquipmentSlotType, UUID> armorModifierMap = new HashMap<>();
	private final HashMap<EquipmentSlotType, UUID> maxHealthModifierMap = new HashMap<>();

	public FrostscarsEmbrace()
	{
		super(new KingOfMaterial().addMaxUses(Convertor.maxMana(500)), Rarity.RARE);
		coolDownReductionModifierMap.put(EquipmentSlotType.MAINHAND,UUID.randomUUID());
		coolDownReductionModifierMap.put(EquipmentSlotType.OFFHAND,UUID.randomUUID());
		armorModifierMap.put(EquipmentSlotType.MAINHAND,UUID.randomUUID());
		armorModifierMap.put(EquipmentSlotType.OFFHAND,UUID.randomUUID());
		maxHealthModifierMap.put(EquipmentSlotType.MAINHAND,UUID.randomUUID());
		maxHealthModifierMap.put(EquipmentSlotType.OFFHAND,UUID.randomUUID());
		setRegistryName("frostscars_embrace");
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

	@Override
	public float getCoolDownReduction()
	{
		return coolDownReduction;
	}

	@Override
	public float getArmor()
	{
		return armor;
	}

	@Override
	public float getMaxHealth()
	{
		return maxHealth;
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
		Multimap<String, AttributeModifier> multimap = super.getAttributeModifiers(equipmentSlot);
		if(equipmentSlot == EquipmentSlotType.MAINHAND || equipmentSlot == EquipmentSlotType.OFFHAND)
		{
			multimap.put(SharedKingAttributes.COOL_DOWN_REDUCTION.getName(), new AttributeModifier(coolDownReductionModifierMap.get(equipmentSlot), "Weapon modifier", getCoolDownReduction(), AttributeModifier.Operation.ADDITION));
			multimap.put(SharedMonsterAttributes.MAX_HEALTH.getName(), new AttributeModifier(maxHealthModifierMap.get(equipmentSlot), "Weapon modifier", getMaxHealth(), AttributeModifier.Operation.ADDITION));
			multimap.put(SharedKingAttributes.ARMOR.getName(), new AttributeModifier(armorModifierMap.get(equipmentSlot), "Weapon modifier", getArmor(), AttributeModifier.Operation.ADDITION));
		}
		return multimap;
	}
}
