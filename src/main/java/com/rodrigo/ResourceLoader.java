package com.rodrigo;

import com.mojang.serialization.Codec;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.resource.JsonDataLoader;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.profiler.Profiler;

public class ResourceLoader extends JsonDataLoader {
    protected ResourceLoader(RegistryWrapper.WrapperLookup registries, Codec codec, RegistryKey registryRef) {
        super(registries, codec, registryRef);
    }

    @Override
    protected void apply(Object prepared, ResourceManager manager, Profiler profiler) {

    }
}
