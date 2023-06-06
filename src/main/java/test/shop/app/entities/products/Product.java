package test.shop.app.entities.products;

import test.shop.app.entities.products.Color;
import test.shop.app.entities.products.Feature;
import test.shop.app.entities.products.ProductCategory;
import test.shop.app.entities.products.Size;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Entity
public class Product {
    @Id
    @GeneratedValue
    private long id;
    private String title;
    @Column(length = 4000)
    private String description;
    private long price;
    private String image;
    private long visitCount;
    private boolean enable;
    private boolean exists;
    private Date addDate;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private ProductCategory category;

    @ManyToMany
    private List<Color> colors;

    @ManyToMany
    private List<Feature> features;

    @ManyToMany
    private List<Size> sizes;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public long getVisitCount() {
        return visitCount;
    }

    public void setVisitCount(long visitCount) {
        this.visitCount = visitCount;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public List<Color> getColors() {
        if(colors == null)
            colors = new ArrayList<>();
        return colors;
    }

    public void setColors(List<Color> colors) {
        this.colors = colors;
    }

    public List<Feature> getFeatures() {
        if(features == null)
            features = new ArrayList<>();
        return features;
    }

    public void setFeatures(List<Feature> features) {
        this.features = features;
    }

    public List<Size> getSizes() {
        if(sizes == null)
            sizes = new ArrayList<>();
        return sizes;
    }

    public void setSizes(List<Size> sizes) {
        this.sizes = sizes;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public boolean isExists() {
        return exists;
    }

    public void setExists(boolean exists) {
        this.exists = exists;
    }

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    public void removeColor(long id) {
        Color color = getColors().stream().filter(x -> x.getId() == id).findFirst().get();
        getColors().remove(color);
    }

    public void addColor(Color color) {
        if (color != null)
            getColors().add(color);
    }

    public void removeFeature(long id) {
        Feature feature = getFeatures().stream().filter(x -> x.getId() == id).findFirst().get();
        getFeatures().remove(feature);
    }

    public void addFeature(Feature feature) {
        if (feature != null)
            getFeatures().add(feature);
    }

    public void removeSize(long id) {
        Size Size = getSizes().stream().filter(x -> x.getId() == id).findFirst().get();
        getSizes().remove(Size);
    }

    public void addSize(Size size) {
        if (size != null)
            getSizes().add(size);
    }


    public String getAddDateStr() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(addDate);
    }

}
