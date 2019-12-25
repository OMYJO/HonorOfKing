package com.OMYJO.KingOfHoror.other;

import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.RangedAttribute;

public class SharedItemAttributes
{
	public static final IAttribute MAX_HEALTH = (new RangedAttribute((IAttribute)null, "king.maxHealthModification", 0.0D, Float.MIN_VALUE, 1024.0D)).setDescription("Max Health Modification").setShouldWatch(true);
	public static final IAttribute ATTACK_DAMAGE = (new RangedAttribute((IAttribute)null, "king.attackDamageModification", 0.0D, Float.MIN_VALUE, 1024.0D)).setDescription("Attack Damage Modification").setShouldWatch(true);
}
