package com.OMYJO.kingofglory.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.util.text.StringTextComponent;

public class CommandDataPanel implements Command<CommandSource>
{
	private static final CommandDataPanel CMD = new CommandDataPanel();

	public static ArgumentBuilder<CommandSource, ?> register(CommandDispatcher<CommandSource> dispatcher)
	{
		return Commands.literal("panel")
				.requires(cs -> cs.hasPermissionLevel(0))
				.executes(CMD);
	}

	@Override
	public int run(CommandContext<CommandSource> context) throws CommandSyntaxException
	{
		context.getSource().sendFeedback(new StringTextComponent("Hello world!"), false);
		return 0;
	}
}
