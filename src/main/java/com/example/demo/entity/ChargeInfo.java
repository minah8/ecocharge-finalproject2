package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import kotlinx.datetime.LocalDateTime;
import lombok.*;

@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor
@EqualsAndHashCode @Builder
@Entity
@Table(name = "tbl_charge_spot")
public class ChargeInfo {


    @Id
    private Long statId; // 충전소 Id

    private String statNm; // 충전소 명

    private Long chgerId;

    private Long chgerType;

    private String addr;

    private double lat;

    private double lng;

    private String useTime;

    private String busiId;

    private String busiNm;

    private Long stat;

    private String powerType;

    private Long zcode;

    private String parkingFree;

    private String note;

    private String limitYn;

    private String limitDetail;

    private Long output;

    private String method;



}