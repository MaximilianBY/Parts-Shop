package by.tms.partshop.repositories;

import by.tms.partshop.entities.PartType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PartTypeRepository extends JpaRepository<PartType, Long> {

  PartType getById(long id);

  PartType getByType(String partType);

  @Query(value = "SELECT ID FROM PARTS_SHOP.PART_TYPE WHERE PART_TYPE.TYPE>:partType",
      nativeQuery = true)
  long getPartTypeId(String partType);
}
