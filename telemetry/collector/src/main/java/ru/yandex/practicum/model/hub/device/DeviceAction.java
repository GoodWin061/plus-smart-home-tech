package ru.yandex.practicum.model.hub.device;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.yandex.practicum.model.hub.ActionType;


@Getter
@Setter
@ToString(callSuper = true)
public class DeviceAction {
    @NotBlank
    private String sensorId;
    private ActionType type;
    private int value;
}
