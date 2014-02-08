package com.ttaylorr.dev.humanity.server.handlers;

public @interface Handler {

	public HandlerPriority priority() default HandlerPriority.NORMAL;

}
