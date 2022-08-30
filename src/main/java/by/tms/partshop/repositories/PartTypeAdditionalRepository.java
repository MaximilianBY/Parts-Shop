package by.tms.partshop.repositories;

import by.tms.partshop.entities.PartTypeAdditional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartTypeAdditionalRepository extends JpaRepository<PartTypeAdditional, Long> {

  PartTypeAdditional getByPartType_IdAndId(long partTypeId, long partTypeAdditionalId);
  PartTypeAdditional getByTypeDescription(String typeDescription);
}
