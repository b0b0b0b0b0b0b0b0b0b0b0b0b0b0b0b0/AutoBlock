package org.b0b0bo.autoblock.commands;

import org.b0b0bo.autoblock.AutoBlock;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ReloadCommand implements CommandExecutor, TabCompleter {

    private final AutoBlock plugin;

    public ReloadCommand(AutoBlock plugin) {
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String prefix = plugin.getPrefix();

        if (args.length > 0 && args[0].equalsIgnoreCase("reload")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (!player.hasPermission("autoblock.reload")) {
                    player.sendMessage(formatMessage(prefix + "§cYou don't have permission to use this command."));
                    return true;
                }
            }
            plugin.reloadConfig();
            sender.sendMessage(formatMessage(prefix + "§aConfiguration reloaded successfully."));
            return true;
        }
        sender.sendMessage(formatMessage(prefix + "§cInvalid command. Use /abk reload."));
        return false;
    }
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> completions = new ArrayList<>();
        if (args.length == 1) {
            completions.add("reload");
        }
        return completions;
    }
    private String formatMessage(String message) {
        return "\n§b+--------------------------------------+\n"
                + message + "\n"
                + "§b+--------------------------------------+\n";
    }
}