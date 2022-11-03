package pl.edu.pw.infstos.szsdsr.tolls;

import org.checkerframework.common.reflection.qual.GetClass;

import java.util.Date;

public class TollDTO {
    private Long id;

    private Boolean isPaid;

    private Date date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getIsPaid() {
        return isPaid;
    }

    public void setIsPaid(Boolean isPaid) {this.isPaid = isPaid;}

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {this.date = date;}
}
