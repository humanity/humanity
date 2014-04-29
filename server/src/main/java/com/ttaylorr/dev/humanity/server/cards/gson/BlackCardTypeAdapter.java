package com.ttaylorr.dev.humanity.server.cards.gson;

import com.google.gson.*;
import com.ttaylorr.dev.humanity.server.cards.card.BlackCard;
import com.ttaylorr.dev.humanity.server.cards.card.HumanityCard;
import com.ttaylorr.dev.humanity.server.cards.factory.CardFactory;

import java.lang.reflect.Type;
import java.util.LinkedList;

public class BlackCardTypeAdapter implements JsonDeserializer<BlackCard> {

    @Override
    public BlackCard deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
        JsonObject obj = jsonElement.getAsJsonObject();

        String messageRaw = obj.get(CardFactory.TEXT).getAsString();
        HumanityCard.Expansion expansion = HumanityCard.Expansion.forString(obj.get(CardFactory.EXPANSION).getAsString());

        LinkedList<String> messages = new LinkedList<>();

        StringBuffer buffer = new StringBuffer();
        for (char c : messageRaw.toCharArray()) {
            if (c == '_') {
                if (buffer.toString().length() > 0) {
                    messages.add(buffer.toString());
                }

                buffer = new StringBuffer();
                messages.add(null);
            } else {
                buffer.append(c);
            }
        }

        if (buffer.toString().length() > 0) {
            messages.add(buffer.toString());
        }

        return new BlackCard(messages, expansion);
    }
}
