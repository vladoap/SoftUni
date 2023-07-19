package softuni.exam.models.dto;

import javax.xml.bind.annotation.XmlElement;
import java.util.Objects;

public class ApartmentWithTownAndArea {

    private Double area;
    private String townName;

    public ApartmentWithTownAndArea(Double area, String townName) {
        this.area = area;
        this.townName = townName;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApartmentWithTownAndArea that = (ApartmentWithTownAndArea) o;
        return Objects.equals(area, that.area) && Objects.equals(townName, that.townName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(area, townName);
    }
}
