package kz.management.adminpanel.repo;


import kz.management.adminpanel.model.Respondent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RespondentRepository extends JpaRepository<Respondent,Long> {

}
