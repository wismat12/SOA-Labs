package jpaPackage;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "autor", schema = "library", catalog = "")
public class AutorEntity {

    private int idAutor;
    private String imie;
    private String nazwisko;
    private Set<KsiazkaEntity> ksiazki = new HashSet<>();

    @Id
    @Column(name = "id_autor", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(int idAutor) {
        this.idAutor = idAutor;
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "autor")
    public Set<KsiazkaEntity> getKsiazki() {
        return ksiazki;
    }

    public void setKsiazki(Set<KsiazkaEntity> ksiazki) {
        this.ksiazki = ksiazki;
    }

    @Override
    public String toString() {
        return "Id: "+idAutor +" imie:"+imie +" nazwisko:"+nazwisko;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AutorEntity that = (AutorEntity) o;
        return idAutor == that.idAutor &&
                Objects.equals(imie, that.imie) &&
                Objects.equals(nazwisko, that.nazwisko);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idAutor, imie, nazwisko);
    }

    public AutorEntity(String imie, String nazwisko) {
        this.imie = imie;
        this.nazwisko = nazwisko;
    }

    public AutorEntity() {
    }
}
