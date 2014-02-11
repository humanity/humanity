package com.ttaylorr.dev.humanity.server;

import com.ttaylorr.dev.humanity.server.handlers.Handler;
import com.ttaylorr.dev.humanity.server.handlers.Listener;
import com.ttaylorr.dev.humanity.server.packets.Packet;
import com.ttaylorr.dev.humanity.server.packets.SimplePacketManager;
import com.ttaylorr.dev.humanity.server.queue.PacketQueueRunnable;
import com.ttaylorr.dev.logger.Logger;
import com.ttaylorr.dev.logger.LoggerProvider;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class HumanityServer implements Runnable {

    private final ServerSocket serverSocket;
    private static final SimplePacketManager manager;
    private boolean closeRequested;
    private final PacketQueueRunnable runner;
    private final Logger logger;
    ArrayList<Client> clients;

    static {
        manager = new SimplePacketManager();
    }

    public HumanityServer(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
        this.closeRequested = false;
        this.runner = new PacketQueueRunnable();
        this.logger = LoggerProvider.putLogger(this.getClass());
        this.clients = new ArrayList<>();
    }

    @Override
    public void run() {
        // TODO because most time will be spent before socket.accept, a call to requestClose won't do anything.
        // TODO put it on a thread that continuously check, instead.
        while (!closeRequested) {
            Socket clientSocket = null;

            try {
                clientSocket = serverSocket.accept();

                System.out.println("Received [" + serverSocket.getLocalPort() + "]: ");

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public SimplePacketManager getPacketManager() {
        return this.manager;
    }

    public void sendPacket(Packet packet) {
        manager.queuePacket(packet);
    }

    public static void registerPacketHandler(Listener inst) {
        Method[] methods = inst.getClass().getMethods();
        for (Method method : methods) {
            if (method.getAnnotation(Handler.class) != null) {
                Class<?>[] params = method.getParameterTypes();
                if (params.length == 1) {
                    Class<? extends Packet> clazz = null;
                    try {
                        clazz = (Class<? extends Packet>) params[0];
                    } catch (ClassCastException e) {
                        throw new IllegalArgumentException(params[0].getSimpleName() + " is not a valid packet class.");
                    }
                    manager.registerHandler(clazz, inst, method);
                }
            }
        }
    }

    public void requestClose() {
        this.closeRequested = true;
    }

    public Logger getLogger() {
        return this.logger;
    }

}
