package com.shopping.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="product")
public class Product {
    private int id;
    private String name;
    private String type;
    private String picture;
    private String description;
    private String area;
    private int state;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "Type", nullable = true, length = 255)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "picture", nullable = true, length = 1024)
    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Basic
    @Column(name = "description", nullable = true, length = 255)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "area", nullable = true, length = 255)
    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    @Basic
    @Column(name="state",nullable = true,length = 11)
    public int getState(){
        return state;
    }

    public void setState(int state){
        this.state=state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id &&
                Objects.equals(name, product.name) &&
                Objects.equals(type, product.type) &&
                Objects.equals(picture, product.picture) &&
                Objects.equals(description, product.description) &&
                Objects.equals(area, product.area);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, type, picture, description, area);
    }
}
