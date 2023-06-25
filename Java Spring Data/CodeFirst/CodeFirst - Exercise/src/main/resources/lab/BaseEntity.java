package lab;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PersistenceUnit;
import java.math.BigInteger;

@MappedSuperclass
@PersistenceUnit(name = "soft_uni")
public class BaseEntity {

    @Id
    private BigInteger id;

    public BaseEntity(BigInteger id) {
        this.id = id;
    }

    public BaseEntity() {
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }
}
