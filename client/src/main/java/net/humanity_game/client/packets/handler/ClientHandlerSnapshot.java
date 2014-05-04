package net.humanity_game.client.packets.handler;

import com.google.common.base.Preconditions;
import net.humanity_game.server.handlers.Listenable;
import net.humanity_game.server.packets.Packet;

import java.lang.reflect.Method;

public class ClientHandlerSnapshot {

    private final Listenable listenable;
    private final Method method;
    private final Class<? extends Packet> type;
    private final ClientHandler annotation;

    public ClientHandlerSnapshot(Listenable instance, Method method, Class<? extends Packet> packetType, ClientHandler annotation) {
        this.listenable = Preconditions.checkNotNull(instance, "instance");
        this.method = Preconditions.checkNotNull(method, "method");
        this.type = packetType;
        this.annotation = Preconditions.checkNotNull(annotation, "annotation");
    }

    public Listenable getInstance() {
        return listenable;
    }

    public Method getMethod() {
        return method;
    }

    public Class<? extends Packet> getType() {
        return type;
    }

    public ClientHandler getAnnotation() {
        return annotation;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ClientHandlerSnapshot{");
        builder.append("type=" + this.type.getSimpleName())
               .append(", method=" + this.method.getName());
        builder.append("}");
        return builder.toString();
    }
}
