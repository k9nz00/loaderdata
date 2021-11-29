package com.semka.loaderdata.dao.entity;

import com.semka.loaderdata.dao.type.AuthoritiesType;
import lombok.Data;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;

@TypeDef(
        name = "authorities",
        typeClass = AuthoritiesType.class
)
@Entity
@Table(name = "users")
@Data
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "role_id")
    private Integer roleId;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;
}
