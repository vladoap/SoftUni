package code.first.exercise.billsPaymentSystem;

import code.first.exercise.sales.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "billing_details")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class BillingDetail extends BaseEntity {

    @Column(unique = true)
    private String number;

    @ManyToOne
    private User owner;

    public BillingDetail() {
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }


}
