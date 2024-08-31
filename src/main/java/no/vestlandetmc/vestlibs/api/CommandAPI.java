package no.vestlandetmc.vestlibs.api;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.PaperCommandManager;
import org.bukkit.plugin.Plugin;

public class CommandAPI {

	private final PaperCommandManager commandManager;

	public CommandAPI(Plugin plugin) {
		this.commandManager = new PaperCommandManager(plugin);
	}

	public void registerCommand(BaseCommand command) {
		commandManager.registerCommand(command);
	}
}