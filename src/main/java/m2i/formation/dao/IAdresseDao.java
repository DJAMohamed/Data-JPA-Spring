package m2i.formation.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import m2i.formation.model.Adresse;

public interface IAdresseDao extends JpaRepository<Adresse, Long> {
	List<Adresse> findByVille(String ville);

	List<Adresse> findByCodePostal(String codePostal);
	
	List<Adresse> findByVilleOrCodePostal(String ville, String codePostal);

	@Query("select p.adresse from Personne p where p.id = :id")
	Adresse findByPersonne(@Param("id") Long id);
}
