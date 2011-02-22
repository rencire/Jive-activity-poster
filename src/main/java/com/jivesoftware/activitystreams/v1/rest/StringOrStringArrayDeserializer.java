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
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.util.ObjectBuffer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The http://activitystrea.ms/head/activity-api.html spec allows for singular strings as well as string arrays for
 * object type and verb, so we need a custom deserializer to handle that.
 */
public class StringOrStringArrayDeserializer extends JsonDeserializer<List<String>> {

    public StringOrStringArrayDeserializer() {
    }

    @Override
    public List<String> deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException, JsonProcessingException
    {

        if (jp.getCurrentToken() != JsonToken.START_ARRAY) {
            List<String> l = new ArrayList<String>();
            JsonToken curr = jp.getCurrentToken();
            // Usually should just get string value:
            if (curr == JsonToken.VALUE_STRING) {
                l.add(jp.getText());

            }
            // Can deserialize any scaler value, but not markers
            else if (curr.isScalarValue()) {
                l.add(jp.getText());
            }

            return l;
        }

        final ObjectBuffer buffer = ctxt.leaseObjectBuffer();
        Object[] chunk = buffer.resetAndStart();
        int ix = 0;
        JsonToken t;

        while ((t = jp.nextToken()) != JsonToken.END_ARRAY) {
            // Ok: no need to convert Strings, but must recognize nulls
            String value = (t == JsonToken.VALUE_NULL) ? null : jp.getText();
            if (ix >= chunk.length) {
                chunk = buffer.appendCompletedChunk(chunk);
                ix = 0;
            }
            chunk[ix++] = value;
        }

        String[] result = buffer.completeAndClearBuffer(chunk, ix, String.class);
        ctxt.returnObjectBuffer(buffer);
        return Arrays.asList(result);
    }
}
