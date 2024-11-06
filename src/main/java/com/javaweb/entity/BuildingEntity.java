package com.javaweb.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "building")
@Getter
@Setter
public class BuildingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "street")
    private String street;

    @Column(name = "ward")
    private String ward;

    @Column(name = "district")
    private String district;

    @Column(name = "structure")
    private String structure;

    @Column(name = "numberofbasement")
    private Integer numberOfBasement;

    @Column(name = "floorarea")
    private Integer floorArea;

    @Column(name = "direction")
    private String direction;

    @Column(name = "level")
    private String level;

    @Column(name = "rentprice")
    private Integer rentPrice;

    @Column(name = "servicefee")
    private Integer serviceFee;

    @Column(name = "carfee")
    private Integer carFee;

    @Column(name = "motofee")
    private Integer motoFee;

    @Column(name = "overtimefee")
    private Integer overtimeFee;

    @Column(name = "waterfee")
    private Integer waterFee;

    @Column(name = "electricityfee")
    private Integer electricityFee;

    @Column(name = "deposit")
    private Integer deposit;

    @Column(name = "payment")
    private Integer payment;

    @Column(name = "managername")
    private String managerName;

    @Column(name = "managerphone")
    private String managerPhone;

    @Column(name = "brokeragefee")
    private Integer brokerageFee;

    @Column(name = "note")
    private String note;

    @Column(name = "linkofbuilding")
    private String linkOfBuilding;

    @Column(name = "map")
    private String map;

    @Column(name = "avatar")
    private String image;

    @Column(name = "type")
    private String typeCode;

    @Column(name = "rentpricedescription")
    private String rentPriceDescription;

    @OneToMany(mappedBy = "building", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    private List<RentAreaEntity> rentAreas = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "assignmentbuilding",
              joinColumns = @JoinColumn(name = "buildingid"),
              inverseJoinColumns = @JoinColumn(name = "staffid")
    )
    private List<UserEntity> staffs = new ArrayList<>();
}
