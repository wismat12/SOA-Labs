package jpaPackage;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "sklep", schema = "world")
@IdClass(SklepEntityPK.class)
public class SklepEntity {
    private int artykul;
    private String dostawca;
    private double cena;

    @Id
    @Column(name = "artykul", nullable = false)
    public int getArtykul() {
        return artykul;
    }

    public void setArtykul(int artykul) {
        this.artykul = artykul;
    }

    @Id
    @Column(name = "dostawca", nullable = false, length = 20)
    public String getDostawca() {
        return dostawca;
    }

    public void setDostawca(String dostawca) {
        this.dostawca = dostawca;
    }

    @Basic
    @Column(name = "cena", nullable = false, precision = 2)
    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SklepEntity that = (SklepEntity) o;
        return artykul == that.artykul &&
                Double.compare(that.cena, cena) == 0 &&
                Objects.equals(dostawca, that.dostawca);
    }

    @Override
    public int hashCode() {

        return Objects.hash(artykul, dostawca, cena);
    }

    @Override
    public String toString(){
        return "artykul: " + this.artykul + " dostawca: " + this.dostawca + " cena: " +this.cena;
    }
}
