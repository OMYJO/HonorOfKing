package com.OMYJO.kingofglory.command;

import com.OMYJO.kingofglory.KingOfGlory;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.tree.LiteralCommandNode;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;

public class MyCommands
{
	public static void register(CommandDispatcher<CommandSource> dispatcher)
	{
		LiteralCommandNode<CommandSource> cmdTut = dispatcher.register(
				Commands.literal("kingofglory")
						.then(CommandDataPanel.register(dispatcher))
		);
		dispatcher.register(Commands.literal("tut").redirect(cmdTut));
	}
}
