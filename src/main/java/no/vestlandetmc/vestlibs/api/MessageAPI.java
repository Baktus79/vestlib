package no.vestlandetmc.vestlibs.api;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.kyori.adventure.title.Title;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.time.Duration;

public class MessageAPI {

	private final Plugin plugin;

	public MessageAPI(Plugin plugin) {
		this.plugin = plugin;
	}

	public void sendAction(Player player, String message) {
		final Component text = translateColorCodes(message);
		player.sendActionBar(text);
	}

	public void sendTitle(Player player, String maintitle, String subtitle) {
		final Component main = translateColorCodes(maintitle);
		final Component sub = translateColorCodes(subtitle);
		Title title = Title.title(main, sub);
		player.showTitle(title);
	}

	public void sendTitle(Player player, String maintitle, String subtitle, int fadein, int stay, int fadeout) {
		final Component main = translateColorCodes(maintitle);
		final Component sub = translateColorCodes(subtitle);
		final Title.Times times = Title.Times.times(Duration.ofSeconds(fadein), Duration.ofSeconds(stay), Duration.ofSeconds(fadeout));
		final Title title = Title.title(main, sub, times);
		player.showTitle(title);
	}

	public void sendMessage(Player player, String... message) {
		for (String s : message) {
			sendMessage(player, s);
		}
	}

	public void sendMessage(Player player, String message) {
		final Component text = translateColorCodes(message);
		player.sendMessage(text);
	}

	public void sendAnnounce(String message) {
		final Component text = translateColorCodes(message);

		for (Player player : Bukkit.getOnlinePlayers()) {
			player.sendMessage(text);
		}

	}

	public void sendConsole(String... message) {
		for (String m : message) {
			sendConsole(m);
		}
	}

	public void sendConsole(String message) {
		final Component text = translateColorCodes(message);
		this.plugin.getServer().getConsoleSender().sendMessage(text);
	}

	public Component translateColorCodes(String... messages) {
		TextComponent finalMessage = null;
		int c = 1;

		for (String m : messages) {
			if (finalMessage == null) {
				finalMessage = LegacyComponentSerializer.legacy('&').deserialize(m).append(Component.newline());
			} else if (c == messages.length) {
				Component more = LegacyComponentSerializer.legacy('&').deserialize(m);
				finalMessage = finalMessage.append(more);
			} else {
				Component more = LegacyComponentSerializer.legacy('&').deserialize(m).append(Component.newline());
				finalMessage = finalMessage.append(more);
			}

			++c;
		}

		return finalMessage;
	}

	public Component translateColorCodes(String message) {
		return LegacyComponentSerializer.legacy('&').deserialize(message);
	}

	public void clickableMessage(Player player, String message, String command) {
		final Component clickMessage = translateColorCodes(message).clickEvent(ClickEvent.runCommand(command));
		player.sendMessage(clickMessage);
	}
}
