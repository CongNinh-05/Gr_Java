package co.smokefree.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "badges")
public class Badge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String criteria;

    @Column(name = "image_url")
    private String imageUrl;

    public Badge(Long id, String name, String description, String criteria, String imageUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.criteria = criteria;
        this.imageUrl = imageUrl;
    }
}