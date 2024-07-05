package org.b0b0bo.autoblock;
import org.b0b0bo.autoblock.commands.ReloadCommand;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
public final class AutoBlock extends JavaPlugin {
    @Override
    public void onEnable() {
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new ItemPickupListener(this), this);
        this.getCommand("abk").setExecutor(new ReloadCommand(this));
        this.getCommand("abk").setTabCompleter(new ReloadCommand(this));
        getLogger().info(("AutoBlock has been enabled!"));
    }
    @Override
    public void onDisable() {
        getLogger().info(("AutoBlock has been disabled!"));
    }
    public FileConfiguration getCustomConfig() {
        return this.getConfig();
    }
    public String getPrefix() {
        return "§b[§aABK§b]§r ";
    }

}