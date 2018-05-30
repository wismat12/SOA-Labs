package jpaPackage;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "zawodnik", schema = "world")
public class ZawodnikEntity {

    private int id;
    private String imie;
    private String nazwisko;
    private int punkty;
    private String narodowosc;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "imie", nullable = false, length = 20)
    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    @Basic
    @Column(name = "nazwisko", nullable = false, length = 30)
    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    @Basic
    @Column(name = "punkty", nullable = false)
    public int getPunkty() {
        return punkty;
    }

    public void setPunkty(int punkty) {
        this.punkty = punkty;
    }

    @Basic
    @Column(name = "narodowosc", nullable = false, length = 3)
    public String getNarodowosc() {
        return narodowosc;
    }

    public void setNarodowosc(String narodowosc) {
        this.narodowosc = narodowosc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ZawodnikEntity that = (ZawodnikEntity) o;
        return id == that.id &&
                punkty == that.punkty &&
                Objects.equals(imie, that.imie) &&
                Objects.equals(nazwisko, that.nazwisko) &&
                Objects.equals(narodowosc, that.narodowosc);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, imie, nazwisko, punkty, narodowosc);
    }

    @Override
    public String toString(){
        return "ID: " + this.id + " imie: " + this.imie + " nazwisko: " +this.nazwisko + " punkty: " + this.punkty + " narodowosc: " + this.narodowosc;
    }
}
