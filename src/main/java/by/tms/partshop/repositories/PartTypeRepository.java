package by.tms.partshop.repositories;

import by.tms.partshop.entities.PartType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartTypeRepository extends JpaRepository<PartType, Long> {
  PartType getById(long id);
  PartType getByPartType(String partType);

  PartType findByPartTypeIs(String partType);
}
