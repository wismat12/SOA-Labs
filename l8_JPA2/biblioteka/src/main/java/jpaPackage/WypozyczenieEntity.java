package jpaPackage;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "wypozyczenie", schema = "library", catalog = "")
public class WypozyczenieEntity {

    private int idWypozyczenie;
    private Date dataWypozyczenia;
    private Date dataZwrotu;
    private CzytelnikEntity czytelnik;
    private KsiazkaEntity ksiazka;

    @Id
    @Column(name = "id_wypozyczenie", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getIdWypozyczenie() {
        return idWypozyczenie;
    }

    public void setIdWypozyczenie(int idWypozyczenie) {
        this.idWypozyczenie = idWypozyczenie;
    }

    @Basic
    @Column(name = "data_wypozyczenia", nullable = false)
    public Date getDataWypozyczenia() {
        return dataWypozyczenia;
    }

    public void setDataWypozyczenia(Date dataWypozyczenia) {
        this.dataWypozyczenia = dataWypozyczenia;
    }

    @Basic
    @Column(name = "data_zwrotu", nullable = true)
    public Date getDataZwrotu() {
        return dataZwrotu;
    }

    public void setDataZwrotu(Date dataZwrotu) {
        this.dataZwrotu = dataZwrotu;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_czytelnik", nullable = false)
    public CzytelnikEntity getCzytelnik() {
        return this.czytelnik;
    }

    public void setCzytelnik(CzytelnikEntity czytelnik) {
        this.czytelnik = czytelnik;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ksiazka", nullable = false)
    public KsiazkaEntity getKsiazka() {
        return this.ksiazka;
    }

    public void setKsiazka(KsiazkaEntity ksiazka) {
        this.ksiazka = ksiazka;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WypozyczenieEntity that = (WypozyczenieEntity) o;
        return idWypozyczenie == that.idWypozyczenie &&
                Objects.equals(dataWypozyczenia, that.dataWypozyczenia) &&
                Objects.equals(dataZwrotu, that.dataZwrotu);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idWypozyczenie, dataWypozyczenia, dataZwrotu);
    }

    public WypozyczenieEntity(Date from, Date to, CzytelnikEntity czytelnikEntity, KsiazkaEntity ksiazkaEntity) {
        this.dataWypozyczenia = from;
        this.dataZwrotu = to;
        this.czytelnik = czytelnikEntity;
        this.ksiazka = ksiazkaEntity;
    }

    public WypozyczenieEntity() {
    }
}
