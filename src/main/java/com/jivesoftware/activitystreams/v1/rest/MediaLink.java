/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.jivesoftware.activitystreams.v1.rest;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.Serializable;
import java.io.StringWriter;

/**
 * Represents a media link
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MediaLink implements Serializable {

    private String mediaType;
    private String url;
	private String thumbnail;

    @JsonProperty
    public String getMediaType() {
        return mediaType;
    }

    @JsonProperty
    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    @JsonProperty
    public String getUrl() {
        return url;
    }

    @JsonProperty
    public void setUrl(String url) {
        this.url = url;
    }

    @JsonProperty
    public String getThumbnail() {
        return thumbnail;
    }

    @JsonProperty
    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MediaLink mediaLink = (MediaLink) o;

        if (mediaType != null ? !mediaType.equals(mediaLink.mediaType) : mediaLink.mediaType != null) {
            return false;
        }
        if (url != null ? !url.equals(mediaLink.url) : mediaLink.url != null) {
            return false;
        }
        if (thumbnail != null ? !thumbnail.equals(mediaLink.thumbnail) : mediaLink.thumbnail != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = mediaType != null ? mediaType.hashCode() : 0;
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (thumbnail != null ? thumbnail.hashCode() : 0);
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
