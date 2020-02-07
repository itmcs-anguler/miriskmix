package com.itmcs.roo.mirisk.web;
import com.itmcs.roo.mirisk.dta.EventType;
import com.itmcs.roo.mirisk.service.api.EventTypeService;
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
 * = EventTypeDeserializer
 TODO Auto-generated class documentation
 *
 */
@RooDeserializer(entity = EventType.class)
@JsonComponent
public class EventTypeDeserializer extends JsonObjectDeserializer<EventType> {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private EventTypeService eventTypeService;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private ConversionService conversionService;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param eventTypeService
     * @param conversionService
     */
    @Autowired
    public EventTypeDeserializer(@Lazy EventTypeService eventTypeService, ConversionService conversionService) {
        this.eventTypeService = eventTypeService;
        this.conversionService = conversionService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return EventTypeService
     */
    public EventTypeService getEventTypeService() {
        return eventTypeService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param eventTypeService
     */
    public void setEventTypeService(EventTypeService eventTypeService) {
        this.eventTypeService = eventTypeService;
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
     * @return EventType
     */
    public EventType deserializeObject(JsonParser jsonParser, DeserializationContext context, ObjectCodec codec, JsonNode tree) {
        String idText = tree.asText();
        Long id = conversionService.convert(idText, Long.class);
        EventType eventType = eventTypeService.findOne(id);
        if (eventType == null) {
            throw new NotFoundException("EventType not found");
        }
        return eventType;
    }
}
