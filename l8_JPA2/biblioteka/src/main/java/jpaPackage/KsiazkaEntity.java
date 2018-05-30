package jpaPackage;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "ksiazka", schema = "library", catalog = "")
public class KsiazkaEntity {

    private int idKsiazka;
    private String tytul;
    private AutorEntity autor;
    private Set<WypozyczenieEntity> wypozyczenia = new HashSet<>();

    @Id
    @Column(name = "id_ksiazka", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getIdKsiazka() {
        return idKsiazka;
    }

    public void setIdKsiazka(int idKsiazka) {
        this.idKsiazka = idKsiazka;
    }

    @Basic
    @Column(name = "tytul", nullable = false, length = 20)
    public String getTytul() {
        return tytul;
    }

    public void setTytul(String tytul) {
        this.tytul = tytul;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_autor", nullable = false)
    public AutorEntity getAutor() {
        return this.autor;
    }

    public void setAutor(AutorEntity autor) {
        this.autor = autor;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "ksiazka")
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
        KsiazkaEntity that = (KsiazkaEntity) o;
        return idKsiazka == that.idKsiazka &&
                Objects.equals(tytul, that.tytul);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idKsiazka, tytul);
    }

    @Override
    public String toString() {
        return "KsiazkaID:"+idKsiazka+" Tytul:"+tytul;
    }
    public KsiazkaEntity(String tytul, AutorEntity autor) {
        this.tytul = tytul;
        this.autor = autor;
    }

    public KsiazkaEntity() {
    }
}
