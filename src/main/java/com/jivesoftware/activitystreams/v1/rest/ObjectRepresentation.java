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
import org.codehaus.jackson.annotate.JsonAnyGetter;
import org.codehaus.jackson.annotate.JsonAnySetter;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.IOException;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents an object
 * <p>
 * See: <a href="http://activitystrea.ms/head/activity-api.html#anchor10">Activity Streams API </a>
 */
@XmlRootElement(name = "")
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class ObjectRepresentation implements Serializable {

    /**
     * A URI formatted identifier, uniquely defining this object
     */
    private String id;
    private String title;
    private String summary;
    private List<ActionLink> actionLinks;
    private MediaLink mediaLink;
    private Map<String, Object> unmappedValues = new HashMap<String, Object>();

    @JsonProperty("id")
    public String getID() {
        return id;
    }

    @JsonProperty("id")
    public void setID(String id) {
        this.id = id;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("summary")
    public String getSummary() {
        return summary;
    }

    @JsonProperty("summary")
    public void setSummary(String summary) {
        this.summary = summary;
    }

    @JsonProperty
    public List<ActionLink> getActionLinks() {
        return actionLinks;
    }

    @JsonProperty
    public void setActionLinks(List<ActionLink> actionLinks) {
        this.actionLinks = actionLinks;
    }

    @JsonProperty
    public MediaLink getMediaLink() {
        return mediaLink;
    }

    @JsonProperty
    public void setMediaLink(MediaLink mediaLink) {
        this.mediaLink = mediaLink;
    }

    // not a getter because of http://jira.codehaus.org/browse/JACKSON-384
    @JsonAnyGetter
    public Map<String, Object> unmappedValues() {
        return unmappedValues;
    }

    @JsonAnySetter
    public void setUnmappedValue(String key, Object value) {
        unmappedValues.put(key, value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ObjectRepresentation that = (ObjectRepresentation) o;

        if (actionLinks != null ? !actionLinks.equals(that.actionLinks) : that.actionLinks != null) {
            return false;
        }
        if (id != null ? !id.equals(that.id) : that.id != null) {
            return false;
        }
        if (mediaLink != null ? !mediaLink.equals(that.mediaLink) : that.mediaLink != null) {
            return false;
        }
        if (summary != null ? !summary.equals(that.summary) : that.summary != null) {
            return false;
        }
        if (title != null ? !title.equals(that.title) : that.title != null) {
            return false;
        }
        if (unmappedValues != null ? !unmappedValues.equals(that.unmappedValues) : that.unmappedValues != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (summary != null ? summary.hashCode() : 0);
        result = 31 * result + (actionLinks != null ? actionLinks.hashCode() : 0);
        result = 31 * result + (mediaLink != null ? mediaLink.hashCode() : 0);
        result = 31 * result + (unmappedValues != null ? unmappedValues.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        ObjectMapper mapper = new ObjectMapper();
        StringWriter sw = new StringWriter();
        try {
            JsonGenerator jsonGenerator = mapper.getJsonFactory().createJsonGenerator(sw);
            jsonGenerator.useDefaultPrettyPrinter();
            mapper.writeValue(jsonGenerator, this);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return sw.toString();
    }
}
