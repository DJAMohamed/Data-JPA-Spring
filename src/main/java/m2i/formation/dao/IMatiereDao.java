package m2i.formation.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import m2i.formation.model.Difficulte;
import m2i.formation.model.Matiere;

public interface IMatiereDao extends JpaRepository<Matiere, Long> {
	List<Matiere> findAllByDifficulte(@Param("dif") Difficulte difficulte); // par @NamedQuery
}
