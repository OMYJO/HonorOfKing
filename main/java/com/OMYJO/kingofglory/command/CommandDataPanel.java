package com.OMYJO.kingofglory.command;

import com.OMYJO.kingofglory.other.Helper;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.tree.LiteralCommandNode;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextComponent;

import javax.annotation.Nullable;
import java.util.Collection;

public class CommandDataPanel
{
	public static ArgumentBuilder<CommandSource, ?> register(CommandDispatcher<CommandSource> dispatcher)
	{
		LiteralArgumentBuilder<CommandSource> lastAttacked = Commands.literal("lastattacked").requires(cs -> cs.hasPermissionLevel(0)).executes((context) -> {
			return panel(context.getSource().asPlayer().getLastAttackedEntity(), context.getSource());
		});
		LiteralArgumentBuilder<CommandSource> revenge = Commands.literal("revenge").requires(cs -> cs.hasPermissionLevel(0)).executes((context) -> {
			return panel(context.getSource().asPlayer().getRevengeTarget(), context.getSource());
		});
		LiteralArgumentBuilder<CommandSource> base = Commands.literal("panel").requires(cs -> cs.hasPermissionLevel(0)).executes((context) -> {
		return panel(context.getSource().asPlayer(), context.getSource());
		})
			.then(Commands.argument("target", EntityArgument.players()).executes((context) -> {
			return panel(EntityArgument.getPlayers(context, "target"),context.getSource());
			})
				.then(lastAttacked)
				.then(revenge)
			)
			.then(lastAttacked)
			.then(revenge);
		return base;
	}

	public static int panel(Collection<ServerPlayerEntity> targets, CommandSource source) throws CommandSyntaxException
	{
		for(ServerPlayerEntity serverplayerentity : targets)
		{
			panel(serverplayerentity,source);
		}
		return targets.size();
	}

	public static int panel(LivingEntity livingEntity, CommandSource source) throws CommandSyntaxException
	{
		source.asPlayer().sendMessage(Helper.ipanel(livingEntity));
		return 1;
	}
}
