package no.vestlandetmc.vestlibs.api;

import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.PaginatedGui;
import net.kyori.adventure.text.Component;
import org.bukkit.plugin.Plugin;

public class GuiAPI {

	public GuiAPI(Plugin plugin) {

	}

	public PaginatedGui getPaginatedGui(Component title, int rows) {
		return Gui.paginated()
				.title(title)
				.rows(rows)
				.disableAllInteractions()
				.create();
	}

	public PaginatedGui getGui(Component title, int rows) {
		return Gui.paginated()
				.title(title)
				.rows(rows)
				.disableAllInteractions()
				.create();
	}

}
