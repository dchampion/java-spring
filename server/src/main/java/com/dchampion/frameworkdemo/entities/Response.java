package com.dchampion.frameworkdemo.entities;

import java.util.logging.Logger;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Entity
@Table(schema="FRAMEWORK_DEMO", name="SHARED_RESPONSE_CACHE")
public class Response {

    @Id
    @GeneratedValue
    private Long id;

    private String uuid;

    private String headers;

    private String body;

    @Transient
    private static final ObjectMapper mapper = new ObjectMapper();

    @Transient
    private static final Logger log = Logger.getLogger(Response.class.getName());

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUuid() {
        return this.uuid;
    }

    public void setHeaders(String headers) {
        this.headers = headers;
    }

    public String getHeaders() {
        return this.headers;
    }

    public void setBody(Object body) {
        try {
            this.body = mapper.writeValueAsString(body);
        } catch (JsonProcessingException e) {
            log.warning(e.getMessage());
        }
    }

    public Object getBody() {
        if (body != null) {
            try {
                return mapper.readValue(body, Object.class);
            } catch (JsonProcessingException e) {
                log.warning(e.getMessage());
            }
        }
        return body;
    }
}