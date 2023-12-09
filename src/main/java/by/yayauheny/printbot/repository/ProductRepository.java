package by.yayauheny.printbot.repository;

import by.yayauheny.printbot.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    Optional<ProductEntity> findByName(String name);
    List<ProductEntity> findAllByAvailableIsTrue();
}
