import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.ArrayList;

@ManagedBean(eager = true)
@ApplicationScoped
public class AdsDatabase{

    private ArrayList<Ad> ads;

    private Ad randomAd;

    public AdsDatabase() {
        this.ads = new ArrayList<Ad>();
        this.ads.add(new Ad("relax1", "https://st2.depositphotos.com/3837271/6710/i/950/depositphotos_67109897-stock-photo-time-to-relax-card.jpg"));
        this.ads.add(new Ad("relax2", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR8Wi_YxjiVtFz8JxRYR4lIOZBuTF9eQxXq7dWfEW_6GF09WI1PGg"));
        this.ads.add(new Ad("relax3", "http://gracespace.org.uk/wp-content/uploads/2014/05/relax.jpg"));
        this.ads.add(new Ad("relax4", "http://s2.thingpic.com/images/NX/PPkAvwfHj8wXGDEC3GvbsRiJ.jpeg"));
    }

    public String getRandomAd(){
        this.randomAd = this.ads.get((int)(Math.random() * this.ads.size()));
        return this.randomAd.url;
    }

    public void incCounterAd(){
        System.out.println("Baner " + this.randomAd.name + " klikniety razy " + (++this.randomAd.counter));
    }

    class Ad{

        String url;
        String name;
        int counter = 0;

        public Ad(String name, String url) {
            this.name = name;
            this.url = url;
        }
    }
}