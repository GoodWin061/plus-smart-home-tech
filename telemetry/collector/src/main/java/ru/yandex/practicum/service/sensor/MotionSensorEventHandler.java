package ru.yandex.practicum.service.sensor;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.kafka.telemetry.event.MotionSensorAvro;
import ru.yandex.practicum.kafka.KafkaClientProducer;
import ru.yandex.practicum.model.sensor.MotionSensorEvent;
import ru.yandex.practicum.model.sensor.SensorEvent;
import ru.yandex.practicum.model.sensor.SensorEventType;

@Component
public class MotionSensorEventHandler extends BaseSensorEventHandler<MotionSensorAvro> {
    public MotionSensorEventHandler(KafkaClientProducer producer) {
        super(producer);
    }

    @Override
    protected MotionSensorAvro mapToAvro(SensorEvent event) {
        MotionSensorEvent motionSensorEvent = (MotionSensorEvent) event;
        return MotionSensorAvro.newBuilder()
                .setLinkQuality(motionSensorEvent.getLinkQuality())
                .setVoltage(motionSensorEvent.getVoltage())
                .setMotion(motionSensorEvent.getMotion())
                .build();
    }

    @Override
    public SensorEventType getMessageType() {
        return SensorEventType.MOTION_SENSOR_EVENT;
    }
}
