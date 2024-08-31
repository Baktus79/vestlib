package no.vestlandetmc.vestlibs.api;

import fr.mrmicky.fastboard.FastBoard;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ScoreboardAPI {

	private final Plugin plugin;
	private final Map<UUID, FastBoard> boards = new HashMap<>();

	public ScoreboardAPI(Plugin plugin) {
		this.plugin = plugin;
	}

	public void createBoard(Player player, String title, String... lines) {
		FastBoard board = new FastBoard(player);
		board.updateTitle(title);
		board.updateLines(lines);
		boards.put(player.getUniqueId(), board);
	}

	public void updateBoard(Player player, String... lines) {
		FastBoard board = boards.get(player.getUniqueId());
		if (board != null) {
			board.updateLines(lines);
		}
	}

	public void removeBoard(Player player) {
		FastBoard board = boards.remove(player.getUniqueId());
		if (board != null) {
			board.delete();
		}
	}

	public void removeAllBoards() {
		for (FastBoard board : boards.values()) {
			board.delete();
		}
		boards.clear();
	}
}
