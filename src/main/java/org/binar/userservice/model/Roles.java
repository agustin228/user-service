package org.binar.userservice.model;

import lombok.Data;
import org.binar.userservice.model.enumerations.ERoles;

import javax.persistence.*;

/**
 * Kelas untuk memodelkan entity/table roles ke database
 * @author Agustinus
 */
@Entity
@Data
@Table(name = "roles")
public class Roles {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERoles name;
}


