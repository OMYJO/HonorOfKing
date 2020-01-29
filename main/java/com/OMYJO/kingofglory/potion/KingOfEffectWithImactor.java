package com.OMYJO.kingofglory.potion;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import java.util.Objects;

public abstract class KingOfEffectWithImactor extends Effect
{
	private LivingEntity imactor;
	protected KingOfEffectWithImactor(EffectType typeIn, int liquidColorIn, LivingEntity imactor)
	{
		super(typeIn, liquidColorIn);
		this.setImactor(imactor);
	}

	public LivingEntity getImactor()
	{
		return imactor;
	}

	public void setImactor(LivingEntity imactor)
	{
		this.imactor = imactor;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		KingOfEffectWithImactor that = (KingOfEffectWithImactor) o;
		return imactor.equals(that.imactor);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(imactor);
	}
}
