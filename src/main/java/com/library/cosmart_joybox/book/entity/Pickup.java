package com.library.cosmart_joybox.book.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
public class Pickup {

    private static Pickup INSTANCE;
    private static List<PickupEntry> pickupSchedules;

    public static Pickup getInstance() {
        initialize();
        return INSTANCE;
    }

    public static List<PickupEntry> getPickupSchedules() {
        initialize();
        return pickupSchedules;
    }

    public static void addPickupSchedule(PickupEntry entry) {
        initialize();
        pickupSchedules.add(entry);
    }

    private static void initialize() {
        if (INSTANCE == null) {
            INSTANCE = new Pickup();
        }

        if (Objects.isNull(pickupSchedules)) {
            pickupSchedules = new ArrayList<>();
        }
    }
}
