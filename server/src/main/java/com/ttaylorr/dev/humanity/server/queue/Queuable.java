package com.ttaylorr.dev.humanity.server.queue;

import java.util.Queue;

public interface Queuable {

    public Queue getQueue();

    public void handleFirst();
}
