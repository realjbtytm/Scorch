package main.java.net.warvale.vanquish.guilds.commands;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import main.java.net.warvale.vanquish.commands.AbstractCommand;
import main.java.net.warvale.vanquish.exceptions.CommandException;

public class GuildCommand extends AbstractCommand {

	/* Created by Tricks */

	public GuildCommand() {
		super("guild", "<create|info|rename|promote|demote>");
	}

	@Override
	public boolean execute(CommandSender sender, String[] args) throws CommandException {
		if (!(sender instanceof Player)) {
			throw new CommandException("Only players can use this command!");
		}

		Player player = (Player) sender;
		Object guildsPrefix = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("GuildsPrefix"));
		if(args.length == 0){
			player.sendMessage(ChatColor.DARK_RED + "Guild Commands");
			player.sendMessage(ChatColor.RED + "/Guild create");
			player.sendMessage(ChatColor.RED + "/Guild info");
			player.sendMessage(ChatColor.RED + "/Guild rename");
			player.sendMessage(ChatColor.RED + "/Guild promote");
			player.sendMessage(ChatColor.RED + "/Guild demote");
			return true;
		}
		switch (args[0]){
			case "create": //I just copied the code from the previous create class
				if (args.length != 2){
					player.sendMessage(ChatColor.RED + "/guild create <name>");
					break;
				}
				/* Check to see if the Player is already in a Guild */
				if (!(plugin.getConfig().get("Player-Data." + player.getUniqueId().toString() + ".InGuild") == null)) {
					player.sendMessage(guildsPrefix + "You're already in a Guild.");
					return true;
				}

				/* String for the GuildName the player wants */
				String guildName = args[1];

				/* Check to see if anyone else has that Guild Name */
				if (plugin.getConfig().contains("Guild-Data." + guildName)) {
					player.sendMessage(guildsPrefix + "That Guild name is already in use.");
					return true;
				}

				/* Check to make sure the Guild name is less than 10 Characters (Prevents spam) */
				if(guildName.length() > 10) {
					player.sendMessage(guildsPrefix + "That Guild name is too long.");
					return true;
				}

				/* Check to make sure the Guild name only contains alphabet letters, again to prevent spam */
				if(!guildName.matches("[a-zA-Z_]*")){
					player.sendMessage(guildsPrefix + "Guild names can only contain A-Z");
					return true;
				}

				/* Making Guild details */
				plugin.getConfig().createSection("Guild-Data." + guildName);
				plugin.getConfig().set("Guild-Data." + guildName + ".GuildOwner", player.getUniqueId().toString());

				plugin.getConfig().set("Guild-Data." + guildName + ".AmountOfMembers", 1);
				plugin.getConfig().set("Guild-Data." + guildName + ".DateCreated", new SimpleDateFormat("dd/MM/yy").format(new Date()));
				plugin.getConfig().set("Guild-Data." + guildName + ".GuildLevel", 0);
				plugin.saveConfig();

				// * Making player details */
				plugin.getConfig().set("Player-Data." + player.getUniqueId().toString() + ".InGuild", true);
				plugin.getConfig().set("Player-Data." + player.getUniqueId().toString() + ".GuildName", guildName);
				plugin.saveConfig();

				player.sendMessage(guildsPrefix + "You have just created the Guild " + ChatColor.YELLOW + guildName);
				break;
			case "info":
				//todo: Put code here
				break;
			case "rename":
				//todo: Put code here
				break;
			case "promote":
				//todo: Put code here
				break;
			case "demote":
				//todo: Put code here
				break;
			default:
				player.sendMessage(ChatColor.RED + "�6Guild Commands");
				player.sendMessage(ChatColor.RED + "�e/guild create");
				player.sendMessage(ChatColor.RED + "�e/guild info");
				player.sendMessage(ChatColor.RED + "�e/guild rename");
				player.sendMessage(ChatColor.RED + "�e/guild promote");
				player.sendMessage(ChatColor.RED + "�e/guild demote");
				break;
		}
		return true;
	}

	@Override
	public List<String> tabComplete(CommandSender sender, String[] args) {
		return new ArrayList<>();
	}

}