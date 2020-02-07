package com.itmcs.roo.mirisk.web;
import com.itmcs.roo.mirisk.dta.EventStatus;
import com.itmcs.roo.mirisk.service.api.EventStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.ConversionService;
import org.springframework.roo.addon.web.mvc.controller.annotations.config.RooDeserializer;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import io.springlets.web.NotFoundException;
import org.springframework.boot.jackson.JsonComponent;

/**
 * = EventStatusDeserializer
 TODO Auto-generated class documentation
 *
 */
@RooDeserializer(entity = EventStatus.class)
@JsonComponent
public class EventStatusDeserializer extends JsonObjectDeserializer<EventStatus> {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private EventStatusService eventStatusService;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private ConversionService conversionService;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param eventStatusService
     * @param conversionService
     */
    @Autowired
    public EventStatusDeserializer(@Lazy EventStatusService eventStatusService, ConversionService conversionService) {
        this.eventStatusService = eventStatusService;
        this.conversionService = conversionService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return EventStatusService
     */
    public EventStatusService getEventStatusService() {
        return eventStatusService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param eventStatusService
     */
    public void setEventStatusService(EventStatusService eventStatusService) {
        this.eventStatusService = eventStatusService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return ConversionService
     */
    public ConversionService getConversionService() {
        return conversionService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param conversionService
     */
    public void setConversionService(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param jsonParser
     * @param context
     * @param codec
     * @param tree
     * @return EventStatus
     */
    public EventStatus deserializeObject(JsonParser jsonParser, DeserializationContext context, ObjectCodec codec, JsonNode tree) {
        String idText = tree.asText();
        Long id = conversionService.convert(idText, Long.class);
        EventStatus eventStatus = eventStatusService.findOne(id);
        if (eventStatus == null) {
            throw new NotFoundException("EventStatus not found");
        }
        return eventStatus;
    }
}
