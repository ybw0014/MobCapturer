package io.github.thebusybiscuit.mobcapturer.mobs;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import com.google.gson.JsonObject;

import org.bukkit.ChatColor;
import org.bukkit.entity.Axolotl;
import org.bukkit.entity.Axolotl.Variant;

import net.guizhanss.guizhanlib.minecraft.helper.entity.AxolotlHelper;

public class AxolotlAdapter extends AnimalsAdapter<Axolotl> {

    public AxolotlAdapter() {
        super(Axolotl.class);
    }

    @Nonnull
    @Override
    public List<String> getLore(@Nonnull JsonObject json) {
        List<String> lore = super.getLore(json);

        lore.add(ChatColor.GRAY + "种类: " + ChatColor.WHITE + AxolotlHelper.getType(json.get("variant").getAsString()));

        return lore;
    }

    @Override
    @ParametersAreNonnullByDefault
    public void apply(Axolotl entity, JsonObject json) {
        super.apply(entity, json);

        entity.setVariant(Variant.valueOf(json.get("variant").getAsString()));
        entity.setPlayingDead(json.get("isPlayingDead").getAsBoolean());
    }

    @Nonnull
    @Override
    public JsonObject saveData(@Nonnull Axolotl entity) {
        JsonObject json = super.saveData(entity);

        json.addProperty("variant", entity.getVariant().name());
        json.addProperty("isPlayingDead", entity.isPlayingDead());

        return json;
    }

}
