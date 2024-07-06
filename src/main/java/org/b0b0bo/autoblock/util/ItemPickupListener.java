package org.b0b0bo.autoblock.util;
import org.b0b0bo.autoblock.AutoBlock;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import java.util.Map;
public class ItemPickupListener implements Listener {
    private final AutoBlock plugin;

    public ItemPickupListener(AutoBlock plugin) {
        this.plugin = plugin;
    }
    @EventHandler
    public void onPlayerPickupItem(PlayerPickupItemEvent event) {
        ItemStack pickedUpItem = event.getItem().getItemStack();
        Material pickedUpMaterial = pickedUpItem.getType();
        Map<Material, ConversionRule> conversionRules = plugin.getConversionRules();

        if (conversionRules.containsKey(pickedUpMaterial)) {
            ConversionRule rule = conversionRules.get(pickedUpMaterial);
            PlayerInventory inventory = event.getPlayer().getInventory();
            int itemCount = pickedUpItem.getAmount();

            for (ItemStack item : inventory.getContents()) {
                if (item != null && item.getType() == rule.getTargetMaterial()) {
                    itemCount += item.getAmount();
                }
            }
            if (itemCount < rule.getRequiredAmount()) {
                return;
            }
            inventory.remove(rule.getTargetMaterial());
            int remainingItems = itemCount % rule.getRequiredAmount();
            if (remainingItems > 0) {
                inventory.addItem(new ItemStack(rule.getTargetMaterial(), remainingItems));
            }
            int blocksToCreate = itemCount / rule.getRequiredAmount();
            inventory.addItem(new ItemStack(rule.getResultMaterial(), blocksToCreate * rule.getResultAmount()));
            event.getItem().remove();
            event.setCancelled(true);
        }
    }
}
