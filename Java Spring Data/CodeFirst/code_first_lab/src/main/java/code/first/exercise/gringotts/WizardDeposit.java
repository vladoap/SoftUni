package code.first.exercise.gringotts;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "wizard_deposits")
@PersistenceUnit(name = "gringotts")
public class WizardDeposit {

    @Id
    private Integer id;

    @Column(name = "first_name", length = 50)
    private String firstName;

    @Column(name = "last_name", length = 60, nullable = false)
    private String lastName;

    @Column(length = 1000)
    private String notes;

    @Column(nullable = false)
    private Short age;

    @Column(name = "magic_wand_creator", length = 100)
    private String magicWandCreator;

    @Column(name = "magic_wand_size")
    private Short magicWandSize;

    @Column(name = "deposit_group", length = 20)
    private String depositGroup;

    @Column(name = "deposit_start_date")
    private LocalDateTime depositStartDate;

    @Column(name = "deposit_amount", precision = 19, scale = 2)
    private Double depositAmount;

    @Column(name = "deposit_interest", precision = 19, scale = 2)
    private Double depositInterest;

    @Column(name = "deposit_charge", precision = 19, scale = 2)
    private Double depositCharge;

    @Column(name = "deposit_expiration_date")
    private LocalDateTime depositExpirationDate;

    @Column(name = "is_deposit_expired")
    private Boolean isDepositExpired;


}
