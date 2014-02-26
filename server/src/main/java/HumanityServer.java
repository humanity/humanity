import com.google.common.collect.ImmutableList;
import com.ttaylorr.dev.humanity.server.client.Client;
import com.ttaylorr.dev.humanity.server.packets.core.Packet03Disconnect;
import com.ttaylorr.dev.humanity.server.queue.core.InboundPacketQueue;
import com.ttaylorr.dev.humanity.server.queue.core.OutboundPacketQueue;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class HumanityServer {

    private List<Client> connectedClients;
    private OutboundPacketQueue outboundPacketQueue;
    private InboundPacketQueue inboundPacketQueue;
    private Socket clientConnectionSocket;

    public HumanityServer(String hostname, int port) {
        try {
            this.clientConnectionSocket = new Socket(hostname, port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setup() {
        this.connectedClients = new ArrayList<>();
        this.outboundPacketQueue = new OutboundPacketQueue();
        this.inboundPacketQueue = new InboundPacketQueue();
    }

    private void teardown() throws IOException {
        this.disconnectAll();
        this.clientConnectionSocket.close();
    }

    private void disconnectAll() {
        this.outboundPacketQueue.addPacket(new Packet03Disconnect(), this.getConnectedClients());
    }

    public ImmutableList<Client> getConnectedClients() {
        return ImmutableList.copyOf(this.connectedClients);
    }
}
