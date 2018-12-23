// Name: Aditya Viswanatham.
// NetID: arv160730.
// CS 3345 Project 3.

package arv160730;

import java.util.*;

// Container Class for Items.
public class Product {
    // Class Attributes to hold information about the product.
    int id;
    int price;
    // A hashSet that stores the list of description.
    HashSet<Integer> desc;

    // Constructor for the creation of a new Object.
    public Product(int id, int price, HashSet<Integer> desc) {
        this.id = id;
        this.price = price;
        this.desc = desc;
    }

    // Getters and Setters.
    public void setId(int id) {
        this.id = id;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public int getPrice() {
        return price;
    }

    // toString function to print the contents of the object.
    public String toString() {
        return "ID: " + id + " Price: " + price + " Desc: " + desc;
    }

    // HashCode function to make every object unique.
    public int hashcode() {
        return id;
    }

    // Equals method to check for duplicates.
    public boolean equals(Object o) {
        if(o == this) {
            return true;
        }
        if(!(o instanceof Product)) {
            return false;
        }
        Product p = (Product)o;
        return p.id == this.id;
    }
}
