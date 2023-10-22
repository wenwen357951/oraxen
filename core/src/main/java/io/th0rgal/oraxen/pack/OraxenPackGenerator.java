package io.th0rgal.oraxen.pack;

import io.th0rgal.oraxen.OraxenPlugin;
import team.unnamed.creative.BuiltResourcePack;
import team.unnamed.creative.ResourcePack;
import team.unnamed.creative.serialize.minecraft.MinecraftResourcePackReader;
import team.unnamed.creative.serialize.minecraft.MinecraftResourcePackWriter;

public class OraxenPackGenerator {

    ResourcePack resourcePack;
    BuiltResourcePack builtPack;

    public OraxenPackGenerator() {
        OraxenPackDownloader.downloadPack();
        resourcePack = ResourcePack.resourcePack();
        OraxenPlugin.get().setResourcePack(resourcePack);
    }

    public void generatePack() {
        MinecraftResourcePackReader.minecraft().readFromDirectory(OraxenPlugin.get().packPath().toFile());
        resourcePack.unknownFiles().remove("token.secret");
        resourcePack.unknownFiles().remove("DefaultPack.zip");
        MinecraftResourcePackWriter.minecraft().writeToZipFile(OraxenPlugin.get().packPath().resolve("pack.zip").toFile(), resourcePack);
        builtPack = MinecraftResourcePackWriter.minecraft().build(resourcePack);
    }

    public BuiltResourcePack getBuiltPack() {
        return builtPack;
    }
}
