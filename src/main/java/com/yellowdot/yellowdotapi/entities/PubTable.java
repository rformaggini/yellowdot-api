package com.yellowdot.yellowdotapi.entities;

import jakarta.persistence.*;


@Entity
@Table(name = "tb_tables")
public class PubTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "table_id")
    private Integer Id;

    @Column(name = "number", unique = true)
    private Integer number;

    private String description;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
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
}
