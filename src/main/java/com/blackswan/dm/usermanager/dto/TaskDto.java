package com.blackswan.dm.usermanager.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
public class TaskDto {
    private Long id;
    @NotNull
    private String name;
    @JsonProperty("description")
    @NotNull
    private String description;
    @JsonProperty("date_time")
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" )
    private Date dateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public TaskDto() {
    }

    public TaskDto(String name, String description, Date dateTime) {
        this.name = name;
        this.description = description;
        this.dateTime = dateTime;
    }
}
