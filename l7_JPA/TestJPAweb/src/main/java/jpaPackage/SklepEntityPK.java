package jpaPackage;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class SklepEntityPK implements Serializable {
    private int artykul;
    private String dostawca;

    @Column(name = "artykul", nullable = false)
    @Id
    public int getArtykul() {
        return artykul;
    }

    public void setArtykul(int artykul) {
        this.artykul = artykul;
    }

    @Column(name = "dostawca", nullable = false, length = 20)
    @Id
    public String getDostawca() {
        return dostawca;
    }

    public void setDostawca(String dostawca) {
        this.dostawca = dostawca;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SklepEntityPK that = (SklepEntityPK) o;
        return artykul == that.artykul &&
                Objects.equals(dostawca, that.dostawca);
    }

    @Override
    public int hashCode() {

        return Objects.hash(artykul, dostawca);
    }
}
