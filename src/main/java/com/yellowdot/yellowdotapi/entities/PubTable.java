package com.yellowdot.yellowdotapi.entities;

import com.yellowdot.yellowdotapi.enums.PubTableStatus;
import jakarta.persistence.*;


@Entity
@Table(name = "tb_tables")
public class PubTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "table_id")
    private Integer id;

    private Integer number;

    private String description;

    @Enumerated(EnumType.STRING)
    private PubTableStatus status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PubTableStatus getStatus() {
        return status;
    }

    public void setStatus(PubTableStatus status) {
        this.status = status;
    }
}
