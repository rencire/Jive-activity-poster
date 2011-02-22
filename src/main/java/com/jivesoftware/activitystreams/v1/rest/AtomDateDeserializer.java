/*
 * $
 * $
 *
 * Copyright (C) 1999-2010 Jive Software. All rights reserved.
 *
 * This software is the proprietary information of Jive Software. Use is subject to license terms.
 */
package com.jivesoftware.activitystreams.v1.rest;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;

import java.io.IOException;
import java.util.Date;

/**
 * Convert a string formatted according to rfc-4287 to a date
 */
public class AtomDateDeserializer extends JsonDeserializer<Date> {

    public AtomDateDeserializer() {
    }

    @Override
    public Date deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        JsonToken t = jp.getCurrentToken();
        if (t == JsonToken.VALUE_STRING) {
            return new AtomDate().setValue(jp.getText()).getDate();
        }

        throw ctxt.mappingException(Date.class);
    }
}