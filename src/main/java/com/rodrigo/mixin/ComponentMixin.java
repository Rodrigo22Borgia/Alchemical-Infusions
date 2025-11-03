package com.rodrigo.mixin;

import net.minecraft.component.ComponentMap;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Item.class)
public interface ComponentMixin {

    @Accessor("components")
    void setComponents(ComponentMap map);
}