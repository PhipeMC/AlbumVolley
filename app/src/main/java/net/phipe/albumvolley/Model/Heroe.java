package net.phipe.albumvolley.Model;

public class Heroe {
    private String name;
    private String imgURL;

    public Heroe(){}
    public Heroe(String name, String imgURL){
        this.name = name;
        this.imgURL = imgURL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }
}
