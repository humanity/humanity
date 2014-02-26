package com.ttaylorr.dev.humanity.server.handlers;

import com.google.common.base.Preconditions;
import com.ttaylorr.dev.humanity.server.packets.Packet;

import java.lang.reflect.Method;

public class HandlerSnapshot {

    private final Listenable instance;
    private final Method method;
    private final Class<? extends Packet> packetType;
    private final Handler annotation;

    public HandlerSnapshot(Listenable instance, Method method, Class<? extends Packet> packetType, Handler annotation) {
        this.instance = Preconditions.checkNotNull(instance, "instance");
        this.method = Preconditions.checkNotNull(method, "method");
        this.packetType = Preconditions.checkNotNull(packetType, "packet type");
        this.annotation = Preconditions.checkNotNull(annotation);
    }

    public Listenable getInstance() {
        return instance;
    }

    public Method getMethod() {
        return method;
    }

    public Class<? extends Packet> getHandlingType() {
        return this.packetType;
    }

    public Handler getAnnotation() {
        return annotation;
    }
}
