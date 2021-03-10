package m2i.formation.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import m2i.formation.config.ApplicationConfig;
import m2i.formation.dao.IAdresseDao;
import m2i.formation.dao.IMatiereDao;
import m2i.formation.dao.IPersonneDao;
import m2i.formation.model.Adresse;
import m2i.formation.model.Civilite;
import m2i.formation.model.Difficulte;
import m2i.formation.model.Formateur;
import m2i.formation.model.Matiere;
import m2i.formation.model.Stagiaire;

public class FormationMain {

	public static void main(String[] args) throws ParseException {
//		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		IAdresseDao adresseDao = context.getBean(IAdresseDao.class);
		IMatiereDao matiereDao = context.getBean(IMatiereDao.class);
		IPersonneDao personneDao = context.getBean(IPersonneDao.class);

		Matiere html = new Matiere("HTML", 2, Difficulte.FACILE);
		Matiere javascript = new Matiere("Javascript", 3, Difficulte.MOYEN);

		Matiere java = new Matiere("JAVA", 5, Difficulte.FACILE);
		Matiere springCore = new Matiere("Spring Core", 3, Difficulte.MOYEN);
		Matiere angular = new Matiere("Angular", 5, Difficulte.DIFFICILE);

		html = matiereDao.save(html);
		javascript = matiereDao.save(javascript);
		java = matiereDao.save(java);
		springCore = matiereDao.save(springCore);
		angular = matiereDao.save(angular);

		Adresse ericAdresse = new Adresse("1 rue de la Paix", "", "75008", "Paris", "France");
		Adresse benoitAdresse = new Adresse("1 place du centre", "", "59000", "Lille", "France");
		Adresse romainAdresse = new Adresse("1 rue Sainte Catherine", "", "33000", "Bordeaux", "France");
		Adresse alexandreAdresse = new Adresse("1 place du Capitole", "", "86000", "Poitiers", "France");
		Adresse cyrilAdresse = new Adresse("1 plaza de milano", "", "445211", "Parme", "Italie");

		ericAdresse = adresseDao.save(ericAdresse);
		benoitAdresse = adresseDao.save(benoitAdresse);
		romainAdresse = adresseDao.save(romainAdresse);
		alexandreAdresse = adresseDao.save(alexandreAdresse);
		cyrilAdresse = adresseDao.save(cyrilAdresse);

		Formateur eric = new Formateur(Civilite.M, "SULTAN", "Eric", "eric@gmail.com", sdf.parse("01/07/2015"), 22,
				false);
		eric.setAdresse(ericAdresse);

		Formateur benoit = new Formateur(Civilite.M, "ROUTIER", "Benoit", "benoit@gmail.com", sdf.parse("01/07/2011"),
				15, true);

		Stagiaire romain = new Stagiaire(Civilite.M, "VASSEUR", "Romain", "romain@gmail.com", sdf.parse("25/12/1983"));

		romain.setFormateur(eric); // master => il est géré par le DAO

		eric.getStagiaires().add(romain); // slave

		Stagiaire alexandre = new Stagiaire(Civilite.M, "WOLNY", "Alexandre", "alexandre@gmail.com",
				sdf.parse("05/05/1985"));
		Stagiaire cyril = new Stagiaire(Civilite.M, "ROMANO", "Cyril", "cyril@gmail.com", sdf.parse("05/05/1982"));

		eric = personneDao.save(eric); // version = 0
		romain = personneDao.save(romain);
		alexandre = personneDao.save(alexandre);
		benoit = personneDao.save(benoit);
		cyril = personneDao.save(cyril);

		eric.getCompetences().add(springCore);
		eric.getCompetences().add(java);

		benoit.getCompetences().add(springCore);
		benoit.getCompetences().add(html);
		benoit.getCompetences().add(java);

		eric = (Formateur) personneDao.save(eric); // version = 1
		benoit = (Formateur) personneDao.save(benoit);

		eric.setCivilite(Civilite.MME);

		eric = (Formateur) personneDao.save(eric);

		context.close();
	}
}
