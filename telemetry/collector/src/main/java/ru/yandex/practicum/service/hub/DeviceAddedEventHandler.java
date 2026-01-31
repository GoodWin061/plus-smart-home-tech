package ru.yandex.practicum.service.hub;

import org.springframework.stereotype.Service;
import ru.yandex.practicum.kafka.producer.KafkaEventProducer;
import ru.yandex.practicum.model.hub.HubEvent;
import ru.yandex.practicum.model.hub.HubEventType;
import ru.yandex.practicum.model.hub.device.DeviceAddedEvent;
import ru.yandex.practicum.model.hub.device.DeviceType;
import ru.yandex.practicum.kafka.telemetry.event.DeviceAddedEventAvro;
import ru.yandex.practicum.kafka.telemetry.event.DeviceTypeAvro;

@Service
public class DeviceAddedEventHandler extends BaseHubEventHandler<DeviceAddedEventAvro> {
    public DeviceAddedEventHandler(KafkaEventProducer producer) {
        super(producer);
    }

    @Override
    public HubEventType getMessageType() {
        return HubEventType.DEVICE_ADDED;
    }

    @Override
    protected DeviceAddedEventAvro mapToAvro(HubEvent event) {
        var deviceAddedEvent = (DeviceAddedEvent) event;

        return new DeviceAddedEventAvro(
                deviceAddedEvent.getId(),
                mapDeviceTypeToAvro(deviceAddedEvent.getDeviceType())
        );
    }

    private DeviceTypeAvro mapDeviceTypeToAvro(DeviceType deviceType) {
        return DeviceTypeAvro.valueOf(deviceType.name());
    }
}
