package com.OMYJO.kingofglory.command;

import com.OMYJO.kingofglory.other.Helper;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.tree.LiteralCommandNode;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextComponent;

import javax.annotation.Nullable;

public class CommandDataPanel
{
	public static ArgumentBuilder<CommandSource, ?> register(CommandDispatcher<CommandSource> dispatcher)
	{
		return Commands.literal("panel")
				.requires(cs -> cs.hasPermissionLevel(0))
				.executes(
						context ->{
							return panel(context.getSource().asPlayer(),context.getSource().asPlayer());
						}
				)
				.then(
						Commands.literal("lastattacked")
						.requires(cs -> cs.hasPermissionLevel(0))
						.executes(
								context ->{
									return panel(context.getSource().asPlayer().getLastAttackedEntity(),context.getSource().asPlayer());
								}
						)
				)
				.then(
						Commands.literal("revenge")
								.requires(cs -> cs.hasPermissionLevel(0))
								.executes(
										context ->{
											return panel(context.getSource().asPlayer().getRevengeTarget(),context.getSource().asPlayer());
										}
								)
				);
	}


	public static int panel(LivingEntity livingEntity, ServerPlayerEntity playerEntity) throws CommandSyntaxException
	{
		if(livingEntity == null)
		{
			playerEntity.sendMessage(new TextComponent()
			{
				@Override
				public String getUnformattedComponentText()
				{
					return null;
				}

				/**
				 * Creates a copy of this component.  Almost a deep copy, except the style is shallow-copied.
				 */
				@Override
				public ITextComponent shallowCopy()
				{
					return new TextComponent()
					{
						@Override
						public String getUnformattedComponentText()
						{

							return "null";
						}

						@Override
						public ITextComponent shallowCopy()
						{
							return null;
						}
					};
				}
			});
		}
		else
		{
			System.out.println(livingEntity.toString());
			playerEntity.sendMessage(new TextComponent()
			{
				@Override
				public String getUnformattedComponentText()
				{
					return null;
				}

				/**
				 * Creates a copy of this component.  Almost a deep copy, except the style is shallow-copied.
				 */
				@Override
				public ITextComponent shallowCopy()
				{
					return new TextComponent()
					{
						@Override
						public String getUnformattedComponentText()
						{

							return Helper.panel(livingEntity);
						}

						@Override
						public ITextComponent shallowCopy()
						{
							return null;
						}
					};
				}
			});
		}
		return 0;
	}
}
