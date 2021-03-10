package m2i.formation.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import m2i.formation.model.Formateur;
import m2i.formation.model.Personne;
import m2i.formation.model.Stagiaire;

public interface IPersonneDao extends JpaRepository<Personne, Long> {
	@Query("select p from Personne p where p.adresse.ville = :ville")
	List<Personne> findAllByVille(@Param("ville") String ville);

	@Query("select f from Formateur f left join f.competences c where c.nom = :nom")
	List<Formateur> findAllFormateurByMatiere(@Param("nom") String nom);

	@Query("select f from Formateur f")
	List<Formateur> findFormateur();

	@Query("select s from Stagiaire s")
	List<Stagiaire> findAllStagiaire();

	@Query("select s.formateur from Stagiaire s where s.id = :id")
	Formateur findByStagiaire(@Param("id") Long id);

	@Query("select s from Stagiaire s join s.formateur f where f.id = :id")
	List<Stagiaire> findAllStagiaireByFormateur(@Param("id") Long id);

	@Query("select f from Formateur f where f.experience > :experience")
	List<Formateur> findAllFormateurExperienceGreaterThan(@Param("experience") int experience);
}
