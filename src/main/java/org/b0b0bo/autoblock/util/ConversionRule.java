package org.b0b0bo.autoblock.util;
import org.bukkit.Material;
public class ConversionRule {
    private final Material targetMaterial;
    private final Material resultMaterial;
    private final int requiredAmount;
    private final int resultAmount;
    public ConversionRule(Material targetMaterial, Material resultMaterial, int requiredAmount, int resultAmount) {
        this.targetMaterial = targetMaterial;
        this.resultMaterial = resultMaterial;
        this.requiredAmount = requiredAmount;
        this.resultAmount = resultAmount;
    }
    public Material getTargetMaterial() {
        return targetMaterial;
    }
    public Material getResultMaterial() {
        return resultMaterial;
    }
    public int getRequiredAmount() {
        return requiredAmount;
    }
    public int getResultAmount() {
        return resultAmount;
    }
}
