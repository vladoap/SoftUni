package code.first.exercise;




import code.first.exercise.hospital.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Set;

public class Main {
    public static void main(String[] args) {


        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gringotts");
        EntityManager em = emf.createEntityManager();

       em.getTransaction().begin();
        Visitation visitation = new Visitation();
        em.persist(visitation);

        Diagnose diagnose = new Diagnose();
        em.persist(diagnose);

        PrescribedMedicament prescribedMedicament = new PrescribedMedicament();
        em.persist(prescribedMedicament);

        GP gp = new GP();
        em.persist(gp);

        Patient patient = new Patient();
        patient.setFirstName("gosho");
        patient.setLastName("gosho");
        patient.setGp(gp);
        patient.setDiagnoses(Set.of(diagnose));
        patient.setVisitations(Set.of(visitation));
        patient.setPrescribedMedicaments(Set.of(prescribedMedicament));


        em.persist(patient);

        em.getTransaction().commit();





    }
}
