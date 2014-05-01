package net.humanity_game.server.client;

import com.google.common.collect.ImmutableList;

import java.util.UUID;

public interface IClientManager<T> {

    public void connectClient(T t);

    public void disconnectClient(T t);

    public T getClientById(UUID id);

    public UUID getUUIDForClient(T t);

    public ImmutableList<T> getConnectedClients();
}
