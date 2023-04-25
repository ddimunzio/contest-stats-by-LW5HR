/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.lw5hr.contest.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author ddimunzio
 */

@Entity
@Table(name = "COUNTRY")
public class Country implements CountryInterface, Serializable {
    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    private Long id;

    private String name;

    private List<String> prefixes = new ArrayList<>();
    
    private List<String> KnownCalls = new ArrayList<>();

    private String CQZone;

    private String ITUZone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<String> getPrefixes() {
        return prefixes;
    }


    public void setPrefixes(String str) {
        str = str.substring(0, str.length() - 1);// remove the ;
        String[] arrayPrefixes = str.split(" ");
        prefixes = Arrays.asList(arrayPrefixes);
    }

    public void setPrefixes(List<String> prefixes) {
        this.prefixes = prefixes;
    }

    public String getCQZone() {
        return CQZone;
    }

    public void setCQZone(String zone) {
        CQZone = zone;
    }

    public String getITUZone() {
        return ITUZone;
    }

    public void setITUZone(String zone) {
        ITUZone = zone;
    }

    public Collection<String> getKnownCalls() {
        return KnownCalls;
    }

    public void setKnownCalls(List<String> KnownCalls) {
        this.KnownCalls = KnownCalls;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String countrytoString() {
        return "Name: " + this.name + " Cq Zone: " + getCQZone();
    }
}
