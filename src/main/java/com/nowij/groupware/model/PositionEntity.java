package com.nowij.groupware.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "position")
public class PositionEntity {
    @Id
    private String positionCode;
    private String positionName;
}
