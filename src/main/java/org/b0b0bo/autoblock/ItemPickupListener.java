package org.b0b0bo.autoblock;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
public class ItemPickupListener implements Listener {
    private final AutoBlock plugin;
    public ItemPickupListener(AutoBlock plugin) {
        this.plugin = plugin;
    }
    @EventHandler
    public void onPlayerPickupItem(PlayerPickupItemEvent event) {
        ItemStack pickedUpItem = event.getItem().getItemStack();
        Material pickedUpMaterial = pickedUpItem.getType();
        FileConfiguration config = plugin.getCustomConfig();
        config.getKeys(false).forEach(key -> {
            String[] values = config.getString(key).split(":");
            Material targetMaterial = Material.matchMaterial(values[0]);
            Material resultMaterial = Material.matchMaterial(values[1]);
            int requiredAmount = Integer.parseInt(values[2]);
            int resultAmount = Integer.parseInt(values[3]);
            if (pickedUpMaterial == targetMaterial) {
                PlayerInventory inventory = event.getPlayer().getInventory();
                int itemCount = pickedUpItem.getAmount();
                for (ItemStack item : inventory.getContents()) {
                    if (item != null && item.getType() == targetMaterial) {
                        itemCount += item.getAmount();
                    }
                }
                if (itemCount == requiredAmount) {
                    int blocksToCreate = itemCount / requiredAmount;
                    int remainingItems = itemCount % requiredAmount;
                    inventory.remove(targetMaterial);
                    if (remainingItems > 0) {
                        inventory.addItem(new ItemStack(targetMaterial, remainingItems));
                    }
                    inventory.addItem(new ItemStack(resultMaterial, blocksToCreate * resultAmount));
                    event.getItem().remove();
                    event.setCancelled(true);
                }
            }
        });
    }
}
