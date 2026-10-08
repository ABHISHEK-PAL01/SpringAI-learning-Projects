package com.ToolCalling.Tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.context.i18n.LocaleContextHolder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static java.time.LocalTime.parse;


public class AI_Tools {

    private Logger logger = LoggerFactory.getLogger(getClass());
     //Information Tool
    @Tool(description = "Get the current date and time in users zone")
    public String getCurrentDateTime(){
        this.logger.info("Tool Calling");
        this.logger.info("Get the current date and time in users zone");
        return LocalDateTime.now()
                .atZone(LocaleContextHolder.getTimeZone().toZoneId())
                .toString();
    }
    //Action Tool
    @Tool(description="Set the Alarm for given time")
    void setAlarm(@ToolParam(description="Time in ISO-8601 format") String time){
        var dateTime = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME);
        this.logger.info("Set the alarm for given time. {}",dateTime);
    }
}
