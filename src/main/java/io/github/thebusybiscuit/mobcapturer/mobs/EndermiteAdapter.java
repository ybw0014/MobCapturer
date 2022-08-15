package io.github.thebusybiscuit.mobcapturer.mobs;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import com.google.gson.JsonObject;

import org.bukkit.entity.Endermite;

import io.github.thebusybiscuit.mobcapturer.MobAdapter;

public class EndermiteAdapter implements MobAdapter<Endermite> {

    @ParametersAreNonnullByDefault
    @Override
    public void apply(Endermite entity, JsonObject json) {
        MobAdapter.super.apply(entity, json);

        entity.setPlayerSpawned(json.get("playerSpawned").getAsBoolean());
    }

    @Nonnull
    @Override
    public JsonObject saveData(@Nonnull Endermite entity) {
        JsonObject json = MobAdapter.super.saveData(entity);

        json.addProperty("playerSpawned", entity.isPlayerSpawned());

        return json;
    }

    @Nonnull
    @Override
    public Class<Endermite> getEntityClass() {
        return Endermite.class;
    }

}
