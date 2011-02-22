/*
 * $
 * $
 *
 * Copyright (C) 1999-2010 Jive Software. All rights reserved.
 *
 * This software is the proprietary information of Jive Software. Use is subject to license terms.
 */
package com.jivesoftware.activitystreams.v1.rest;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.*;
import org.codehaus.jackson.node.JsonNodeFactory;
import org.codehaus.jackson.node.ObjectNode;
import org.codehaus.jackson.schema.SchemaAware;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Date;

/**
 * Convert a date to an rfc-4287 formatted string 
 */
public class AtomDateSerializer extends JsonSerializer<Date> implements SchemaAware {

    public AtomDateSerializer() {
    }

    @Override
    public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException
    {
        if (date == null) {
            return;
        }

        jsonGenerator.writeString(AtomDate.format(date));
    }

    @Override
    public JsonNode getSchema(SerializerProvider provider, Type typeHint) throws JsonMappingException {
        ObjectNode objectNode = JsonNodeFactory.instance.objectNode();
        objectNode.put("type", "string");
        objectNode.put("optional", true);
        return objectNode;
    }
}
