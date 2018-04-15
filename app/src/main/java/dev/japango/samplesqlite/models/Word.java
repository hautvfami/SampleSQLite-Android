package dev.japango.samplesqlite.models;

public class Word {
    private int id;
    private String word;
    private String mean;
    private String descriptions;
    private byte[] image;

    public Word() {
    }

    public Word(int id_, String word_, String mean_, String descriptions_, byte[] image_) {
        this.id = id_;
        this.word = word_;
        this.mean = mean_;
        this.descriptions = descriptions_;
        this.image = image_;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getMean() {
        return mean;
    }

    public void setMean(String mean) {
        this.mean = mean;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
