package by.tms.partshop.repositories;

import by.tms.partshop.entities.Images;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImagesRepository extends JpaRepository<Images, Long> {
  Images getByCar_IdOrPart_PartIndex(long id);
}
