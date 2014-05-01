package com.ttaylorr.dev.humanity.client.client;

import com.google.common.collect.ImmutableList;
import com.ttaylorr.dev.humanity.server.client.IClientManager;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ClientManager implements IClientManager<HumanityClient> {

    private List<HumanityClient> clients;

    public ClientManager() {
        this.clients = new ArrayList<>();
    }

    @Override
    public void connectClient(HumanityClient humanityClient) {
        this.clients.add(humanityClient);
    }

    @Override
    public void disconnectClient(HumanityClient humanityClient) {
        this.clients.remove(humanityClient);
    }

    @Override
    public HumanityClient getClientById(UUID id) {
        for (HumanityClient client : this.clients) {
            if (client.getDefnition().getUUID().equals(id)) {
                return client;
            }
        }
        return null;
    }

    @Override
    public UUID getUUIDForClient(HumanityClient humanityClient) {
        for (HumanityClient client : this.clients) {
            if (client.equals(humanityClient)) {
                return client.getDefnition().getUUID();
            }
        }
        return null;
    }

    @Override
    public ImmutableList<HumanityClient> getConnectedClients() {
        return ImmutableList.copyOf(this.clients);
    }
}
