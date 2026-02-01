package ru.yandex.practicum.service.hub;

import ru.yandex.practicum.model.hub.HubEvent;
import ru.yandex.practicum.model.hub.HubEventType;

public interface HubEventHandler<T> {
    HubEventType getMessageType();
    T mapToAvro(HubEvent event);
    void handle(HubEvent event);
}
