package com.ttaylorr.dev.humanity.server.handlers;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)

public @interface Handler {

    HandlerPriority priority() default HandlerPriority.NORMAL;

}
