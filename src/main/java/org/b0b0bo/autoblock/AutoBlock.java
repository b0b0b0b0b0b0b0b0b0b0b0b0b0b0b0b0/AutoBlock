package org.b0b0bo.autoblock;
import org.b0b0bo.autoblock.commands.ReloadCommand;
import org.b0b0bo.autoblock.util.ConversionRule;
import org.b0b0bo.autoblock.util.ItemPickupListener;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Material;
import java.util.HashMap;
import java.util.Map;
public final class AutoBlock extends JavaPlugin {
    private final Map<Material, ConversionRule> conversionRules = new HashMap<>();
    @Override
    public void onEnable() {
        saveDefaultConfig();
        loadConversionRules();
        getServer().getPluginManager().registerEvents(new ItemPickupListener(this), this);
        ReloadCommand reloadCommand = new ReloadCommand(this);
        this.getCommand("abk").setExecutor(reloadCommand);
        this.getCommand("abk").setTabCompleter(reloadCommand);
        getLogger().info("AutoBlock has been enabled!");
    }
    @Override
    public void onDisable() {
        getLogger().info("AutoBlock has been disabled!");
    }
    public FileConfiguration getCustomConfig() {
        return this.getConfig();
    }
    public String getPrefix() {
        return "§b[§aABK§b]§r ";
    }
    public void loadConversionRules() {
        FileConfiguration config = getCustomConfig();
        config.getKeys(false).forEach(key -> {
            String[] values = config.getString(key).split(":");
            Material targetMaterial = Material.matchMaterial(values[0]);
            Material resultMaterial = Material.matchMaterial(values[1]);
            int requiredAmount = Integer.parseInt(values[2]);
            int resultAmount = Integer.parseInt(values[3]);
            ConversionRule rule = new ConversionRule(targetMaterial, resultMaterial, requiredAmount, resultAmount);
            conversionRules.put(targetMaterial, rule);
        });
    }
    public Map<Material, ConversionRule> getConversionRules() {
        return conversionRules;
    }
}
