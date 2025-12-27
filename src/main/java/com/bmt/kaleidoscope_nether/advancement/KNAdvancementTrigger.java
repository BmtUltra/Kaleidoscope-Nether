package com.bmt.kaleidoscope_nether.advancement;

import com.bmt.kaleidoscope_nether.KaleidoscopeNether;
import com.google.gson.JsonObject;
import net.minecraft.advancements.critereon.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import org.jetbrains.annotations.NotNull;

public class KNAdvancementTrigger extends SimpleCriterionTrigger<KNAdvancementTrigger.Instance> {
    private final ResourceLocation id;

    public KNAdvancementTrigger(String id) {
        this.id = KaleidoscopeNether.id(id);
    }

    @Override
    protected KNAdvancementTrigger.@NotNull Instance createInstance(@NotNull JsonObject jsonObject, @NotNull ContextAwarePredicate predicate, @NotNull DeserializationContext context) {
        return new Instance(id, jsonObject.get("triggerId").getAsString());
    }

    public void trigger(ServerPlayer serverPlayer, String id) {
        this.trigger(serverPlayer, (instance) -> instance.triggerId.equals(id));
    }

    @Override
    public @NotNull ResourceLocation getId() {
        return id;
    }

    public static class Instance extends AbstractCriterionTriggerInstance {

        private final String triggerId;

        public Instance(ResourceLocation resourceLocation, String triggerId) {
            super(resourceLocation, ContextAwarePredicate.ANY);
            this.triggerId = triggerId;
        }

        public static Instance id(String triggerId) {
            return new Instance(KNAdvancementTriggerRegistry.SIMPLE_ID.getId(), triggerId);
        }


        @Override
        public @NotNull JsonObject serializeToJson(SerializationContext context) {
            JsonObject jsonObject = super.serializeToJson(context);
            jsonObject.addProperty("triggerId", triggerId);
            return jsonObject;
        }
    }
}
