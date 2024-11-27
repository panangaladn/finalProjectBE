package lk.ijse.CropMonitoring.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lk.ijse.CropMonitoring.entity.association.FieldStaffDetailsEntity;
import lk.ijse.CropMonitoring.entity.association.StaffLogDetailsEntity;
import lk.ijse.CropMonitoring.entity.enums.Gender;
import lk.ijse.CropMonitoring.entity.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "staff")
public class StaffEntity {
    @Id
    @Column(name = "staff_member_id")
    private String staffMemberId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "designation")
    private String designation;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "joined_date")
    private Date joinedDate;

    @Column(name = "date_of_birth")
//    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date DOB;

    @Column(name = "address_line_1")
    private String addressLine1;

    @Column(name = "address_line_2")
    private String addressLine2;

    @Column(name = "address_line_3")
    private String addressLine3;

    @Column(name = "address_line_4")
    private String addressLine4;

    @Column(name = "address_line_5")
    private String addressLine5;

    @Column(name = "contact_no")
    private String contactNo;

    @Column(name = "email")
    private String email;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;




    @JsonIgnore
    @OneToOne(mappedBy = "staff")
    private EquipmentEntity equipment;

    @OneToMany(mappedBy = "staff", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//    @OneToMany(mappedBy = "staff")
    @JsonIgnore
    private List<VehicleEntity> vehicleList = new ArrayList<>();


    @OneToOne(mappedBy = "staff", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @OneToOne(mappedBy = "staff")
    private UserEntity user;

    //Associate

    @OneToMany(mappedBy = "staff", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @OneToMany(mappedBy = "staff")
    private List<FieldStaffDetailsEntity> fieldStaffDetailsList = new ArrayList<>();


    @OneToMany(mappedBy = "staff", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @OneToMany(mappedBy = "staff")
    private List<StaffLogDetailsEntity> staffLogDetailsList = new ArrayList<>();
}