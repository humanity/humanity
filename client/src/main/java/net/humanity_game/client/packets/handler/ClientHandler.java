package net.humanity_game.client.packets.handler;

import net.humanity_game.server.handlers.HandlerPriority;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ClientHandler {

    HandlerPriority priority() default HandlerPriority.NORMAL;

    boolean handleSelf() default true;

}
