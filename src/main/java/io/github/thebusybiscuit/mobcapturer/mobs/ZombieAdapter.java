package io.github.thebusybiscuit.mobcapturer.mobs;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Zombie;

import com.google.gson.JsonObject;

import net.guizhanss.minecraft.mobcapturer.utils.HumanizeUtil;

public class ZombieAdapter<T extends Zombie> extends AbstractHumanoidAdapter<T> {

    public ZombieAdapter(Class<T> entityClass) {
        super(entityClass);
    }

    @Override
    public List<String> getLore(JsonObject json) {
        List<String> lore = super.getLore(json);

        lore.add(ChatColor.GRAY + "幼年: " + ChatColor.WHITE + HumanizeUtil.getBoolean(json.get("baby").getAsBoolean()));

        return lore;
    }

    @Override
    public JsonObject saveData(T entity) {
        JsonObject json = super.saveData(entity);

        json.addProperty("baby", !entity.isAdult());

        if (entity.isConverting()) {
            json.addProperty("conversionTime", entity.getConversionTime());
        }

        return json;
    }

    @Override
    public void apply(T entity, JsonObject json) {
        super.apply(entity, json);

        if(json.get("baby").getAsBoolean())
            entity.setBaby();
        else
            entity.setAdult();

        if (json.has("conversionTime")) {
            entity.setConversionTime(json.get("conversionTime").getAsInt());
        }
    }

}
