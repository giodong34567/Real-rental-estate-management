//package com.javaweb.entity;
//
//
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import javax.persistence.*;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Table;
//
//@Entity
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@Table(name = "user_role")
//public class UserRoleEntity{
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private UserEntity users;
//
//	@ManyToOne
//    @JoinColumn(name = "role_id")
//    private RoleEntity roles;
//}
