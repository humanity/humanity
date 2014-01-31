package com.ttaylorr.dev.humanity.server.packets;

public @interface Handler {

	public HandlerPriority priority() default HandlerPriority.NORMAL;

}
