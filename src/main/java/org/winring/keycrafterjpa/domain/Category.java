package org.winring.keycrafterjpa.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@ToString
@Setter
@Getter
@Entity
public class Category extends BaseEntity {
    private String name;

    @ManyToMany
    @JoinTable(name = "category_product",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> children = new ArrayList<>();

    public void setParent(Category parent) {
        Collection<Category> thisParentChildren = this.parent == null ? null : this.parent.getChildren();
        super.setRelatedEntity(this, this.parent, thisParentChildren, parent.getChildren());
        this.parent = parent;
    }

    public void addChild(Category child) {
        this.children.add(child);
        child.setParent(this);
    }
}
