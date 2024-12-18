package mk.finki.ukim.mk.lab1.repository.jpa;

import mk.finki.ukim.mk.lab1.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepositoryI extends JpaRepository<Location,Long>{
}
