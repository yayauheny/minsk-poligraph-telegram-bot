package by.yayauheny.printbot.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "products")
public class ProductEntity extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String description;
    private String discountCondition;

    @Column(nullable = false)
    private double pricePerUnit;

    @ColumnDefault("true")
    @Column(nullable = false)
    @Builder.Default
    private boolean available = true;

    @ColumnDefault("false")
    @Column(nullable = false)
    @Builder.Default
    private boolean hasDiscount = false;
}
