package jpaPackage;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "czytelnik", schema = "library", catalog = "")
public class CzytelnikEntity {

    private int idCzytelnik;
    private String imie;
    private String nazwisko;
    private Set<WypozyczenieEntity> wypozyczenia;

    @Id
    @Column(name = "id_czytelnik", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getIdCzytelnik() {
        return idCzytelnik;
    }

    public void setIdCzytelnik(int idCzytelnik) {
        this.idCzytelnik = idCzytelnik;
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "czytelnik")
    public Set<WypozyczenieEntity> getWypozyczenia() {
        return this.wypozyczenia;
    }

    public void setWypozyczenia(Set<WypozyczenieEntity> wypozyczenia) {
        this.wypozyczenia = wypozyczenia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CzytelnikEntity that = (CzytelnikEntity) o;
        return idCzytelnik == that.idCzytelnik &&
                Objects.equals(imie, that.imie) &&
                Objects.equals(nazwisko, that.nazwisko);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idCzytelnik, imie, nazwisko);
    }

    @Override
    public String toString() {
        return "CzytelnikID:"+idCzytelnik+" Imie:"+imie + " Nazwisko: "+nazwisko;
    }

    public CzytelnikEntity(String imie, String nazwisko) {
        this.imie = imie;
        this.nazwisko = nazwisko;
    }

    public CzytelnikEntity() {
    }
}
