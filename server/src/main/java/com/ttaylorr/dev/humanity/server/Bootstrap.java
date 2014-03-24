package com.ttaylorr.dev.humanity.server;

import com.ttaylorr.dev.humanity.server.cards.factory.BlackCardFactory;

import java.io.File;

public class Bootstrap {

    static HumanityServer server = new HumanityServer(8000);
    public static final int LOOP_DELAY = 30;

    public static void main(String[] args) {
        new BlackCardFactory(new File("./server/src/main/resources/cards-all.json")).parse();
//        server.open();
//        while(true);
    }
}
