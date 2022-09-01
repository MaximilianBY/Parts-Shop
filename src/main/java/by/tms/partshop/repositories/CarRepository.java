package by.tms.partshop.repositories;

import by.tms.partshop.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

  Car findById(long id);

  Car getByCarIndex(String carIndex);

  @Query(value = "SELECT ID FROM PARTS_SHOP.CARS WHERE CARS.CAR_INDEX > :carIndex",
      nativeQuery = true)
  long getCarId(String carIndex);
}
