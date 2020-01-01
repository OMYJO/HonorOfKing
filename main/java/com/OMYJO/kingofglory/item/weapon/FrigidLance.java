package com.OMYJO.kingofglory.item.weapon;

import com.OMYJO.kingofglory.other.Helper;
import com.OMYJO.kingofglory.other.KingOfMaterial;
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

public class FrigidLance extends KingOfWeapon
{
	private float attackDamage = Helper.attackDamage(80);
	private float maxHealth = Helper.maxHealth(600);
	private float attackSpeed = 0.15F;
	private final HashMap<EquipmentSlotType, UUID> attackDamageModifierMap = new HashMap<>();
	private final HashMap<EquipmentSlotType, UUID> maxHealthModifierMap = new HashMap<>();
	private final HashMap<EquipmentSlotType, UUID> attackSpeedModifierMap = new HashMap<>();

	public FrigidLance()
	{
		super(new KingOfMaterial(), Rarity.UNCOMMON);
		attackDamageModifierMap.put(EquipmentSlotType.MAINHAND,UUID.randomUUID());
		attackDamageModifierMap.put(EquipmentSlotType.OFFHAND,UUID.randomUUID());
		maxHealthModifierMap.put(EquipmentSlotType.MAINHAND,UUID.randomUUID());
		maxHealthModifierMap.put(EquipmentSlotType.OFFHAND,UUID.randomUUID());
		attackSpeedModifierMap.put(EquipmentSlotType.MAINHAND,UUID.randomUUID());
		attackSpeedModifierMap.put(EquipmentSlotType.OFFHAND,UUID.randomUUID());
		setRegistryName("frigid_lance");
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
	public float getAttackDamage()
	{
		return attackDamage;
	}

	@Override
	public float getMaxHealth()
	{
		return maxHealth;
	}

	@Override
	public float getAttackSpeed()
	{
		return attackSpeed;
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
			multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(attackDamageModifierMap.get(equipmentSlot), "Weapon modifier", getAttackDamage(), AttributeModifier.Operation.ADDITION));
			multimap.put(SharedMonsterAttributes.MAX_HEALTH.getName(), new AttributeModifier(maxHealthModifierMap.get(equipmentSlot), "Weapon modifier", getMaxHealth(), AttributeModifier.Operation.ADDITION));
			multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(attackSpeedModifierMap.get(equipmentSlot), "Weapon modifier", getAttackSpeed(), AttributeModifier.Operation.MULTIPLY_BASE));
		}
		return multimap;
	}
}
