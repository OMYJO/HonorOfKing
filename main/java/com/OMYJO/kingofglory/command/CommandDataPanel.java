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
import net.minecraft.entity.Entity;
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
		return Commands.literal("panel").requires(cs -> cs.hasPermissionLevel(0)).executes((context) -> {
			return panel(context.getSource().asPlayer(), context.getSource());
		}).then(Commands.literal("lastattacked").requires(cs -> cs.hasPermissionLevel(0)).executes((context) -> {
			return panel(context.getSource().asPlayer().getLastAttackedEntity(), context.getSource());
		})).then(Commands.literal("revenge").requires(cs -> cs.hasPermissionLevel(0)).executes((context) -> {
			return panel(context.getSource().asPlayer().getRevengeTarget(), context.getSource());
		}));
	}


	public static int panel(LivingEntity livingEntity, CommandSource source) throws CommandSyntaxException
	{
		if (source.getEntity() == null)
		{
			return 0;
		}
		if (source.asPlayer() != null)
			source.asPlayer().sendMessage(new StringTextComponent(Helper.panel(livingEntity)));
		return 1;
	}
}
