package co.smokefree.repository;

import co.smokefree.entity.Progress;
import co.smokefree.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ProgressRepository extends JpaRepository<Progress, Long> {
    List<Progress> findByUser(User user);

    List<Progress> findByUserAndDate(User user, LocalDate date);
}