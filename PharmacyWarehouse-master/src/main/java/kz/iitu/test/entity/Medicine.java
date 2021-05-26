package kz.iitu.test.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Medicine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String manufacturer;
    private double price;

    @OneToOne(mappedBy = "medicine", fetch = FetchType.LAZY)
    private Request request;
}
