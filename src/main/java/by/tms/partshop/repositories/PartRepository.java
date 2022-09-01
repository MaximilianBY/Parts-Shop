package by.tms.partshop.repositories;

import by.tms.partshop.entities.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartRepository extends JpaRepository<Part, Long> {

  Part getByPartIndex(long partIndex);

  Part getById(long id);

  boolean findByPartIndexAndAvailableToBuyIsTrue(long partIndex);
}
