package by.tms.partshop.repositories;

import by.tms.partshop.entities.carSpecification.Brand;
import by.tms.partshop.entities.carSpecification.Model;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelRepository extends JpaRepository<Model, Long> {
    Model getModelByModel(String model);
    List<Model> findAllByBrand(Brand brand);
}
