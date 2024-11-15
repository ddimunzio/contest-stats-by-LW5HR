package org.lw5hr.contest.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by ddimunzio on 21.06.2017.
 */

@Entity
@Table(name = "CONTEST")
public class Contest implements Serializable {

    public Contest() {}
    public Contest(final Boolean live) {
        this.live = live;
    }

    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    private Long id;
    @Column(name = "contestDescription")
    private String contestDescription;
    @Column(name = "live")
    private Boolean live;
    @Column(name = "sfi")
    private Double sfi;
    @Column(name = "kIndex")
    private Integer kIndex;
    @Column(name = "aIndex")
    private Integer aIndex;
    @Column(name = "dateFrom")
    private Date dateFrom;
    @Column(name = "dateTo")
    private Date dateTo;

    public Boolean getLive() {
        return live;
    }

    public void setLive(Boolean live) {
        this.live = live;
    }

    @OneToMany (mappedBy = "contest", cascade = {CascadeType.ALL}, orphanRemoval = true, fetch = FetchType.EAGER)
    List<Qso> qsos;

    @ManyToOne(fetch = FetchType.EAGER)
    private ContestProperties contestProperties;

    @ManyToOne(fetch = FetchType.EAGER)
    private ContestCategory contestCategory;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContestName() {
        return contestProperties.getEventName();
    }


    public String getContestDescription() {
        return contestDescription;
    }

    public void setContestDescription(String contestDescription) {
        this.contestDescription = contestDescription;
    }

    public List<Qso> getQsos() {
        return qsos;
    }

    public void setQsos(List<Qso> qsos) {
        this.qsos = qsos;
    }

    public Double getSfi() {
        return sfi;
    }

    public void setSfi(Double sfi) {
        this.sfi = sfi;
    }

    public Integer getkIndex() {
        return kIndex;
    }

    public void setkIndex(Integer kIndex) {
        this.kIndex = kIndex;
    }

    public Integer getaIndex() {
        return aIndex;
    }

    public void setaIndex(Integer aIndex) {
        this.aIndex = aIndex;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTO) {
        this.dateTo = dateTO;
    }

    public ContestProperties getContestProperties() {
        return contestProperties;
    }

    public void setContestProperties(ContestProperties contestProperties) {
        this.contestProperties = contestProperties;
    }

    public ContestCategory getContestCategory() {
        return contestCategory;
    }

    public void setContestCategory(ContestCategory contestCategory) {
        this.contestCategory = contestCategory;
    }

    @Override
    public String toString() {
        return contestProperties.getEventName();
    }
}
