package com.ttaylorr.dev.humanity.server.cards.gson;

import com.google.gson.*;
import com.ttaylorr.dev.humanity.server.cards.card.HumanityCard;
import com.ttaylorr.dev.humanity.server.cards.card.WhiteCard;
import com.ttaylorr.dev.humanity.server.cards.factory.CardFactory;

import java.lang.reflect.Type;

public class WhiteCardTypeAdapter implements JsonDeserializer<WhiteCard> {

    @Override
    public WhiteCard deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
        JsonObject obj = jsonElement.getAsJsonObject();

        String message = obj.getAsJsonPrimitive(CardFactory.TEXT).getAsString();
        HumanityCard.Expansion expansion = HumanityCard.Expansion.forString(obj.getAsJsonPrimitive(CardFactory.EXPANSION).getAsString());

        return new WhiteCard(message, expansion);
    }
}
