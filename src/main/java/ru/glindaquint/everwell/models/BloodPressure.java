package ru.glindaquint.everwell.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "blood_pressures")
public class BloodPressure {
    @Id
    @Column(name = "blood_pressure_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "blood_pressure_id_seq")
    @SequenceGenerator(name = "blood_pressure_id_seq", sequenceName = "blood_pressure_id_seq", allocationSize = 1)
    private Long bloodPressureId;

    @Column(name = "systolic_pressure", nullable = false)
    private Integer systolicPressure;

    @Column(name = "diastolic_pressure", nullable = false)
    private Integer diastolicPressure;

    @Column(name = "heart_rate", nullable = false)
    private Integer heartRate;

    @Column(name = "measurement_datetime", nullable = false)
    private Date measurementDateTime;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @PrePersist
    private void beforeInsert() {
        this.setMeasurementDateTime(new Date());
    }
}
