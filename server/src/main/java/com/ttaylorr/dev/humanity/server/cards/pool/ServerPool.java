package com.ttaylorr.dev.humanity.server.cards.pool;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.ttaylorr.dev.humanity.server.cards.card.BlackCard;
import com.ttaylorr.dev.humanity.server.cards.card.WhiteCard;
import com.ttaylorr.dev.humanity.server.client.ClientConnection;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ServerPool implements IPool, Serializable {

    private final BlackCard choice;
    private Map<ClientConnection, List<WhiteCard>> submitted;

    public ServerPool(final BlackCard card) {
        this.choice = Preconditions.checkNotNull(card, "card");
        this.submitted = new ConcurrentHashMap<>();
    }

    @Override
    public BlackCard getChoiceCard() {
        return this.choice;
    }

    @Override
    public List<WhiteCard> getSubmitted() {
        List<WhiteCard> result = new ArrayList<>();
        for (List<WhiteCard> set : new ArrayList<>(this.submitted.values())) {
            result.addAll(set);
        }

        return ImmutableList.copyOf(result);
    }

    public void submitCard(ClientConnection client, WhiteCard card) {
        if(this.submitted.containsKey(client)) {
            this.submitted.get(client).add(card);
        } else {
            this.submitted.put(client, Lists.newArrayList(card));
        }
    }

    @Override
    public boolean isComplete() {
        for (List<WhiteCard> cards : this.submitted.values()) {
            if(cards.size() < choice.getNumberToFill()) {
                return false;
            }
        }

        return true;
    }
}
