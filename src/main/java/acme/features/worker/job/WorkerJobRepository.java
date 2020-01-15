
package acme.features.worker.job;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.jobs.Job;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface WorkerJobRepository extends AbstractRepository {

	@Query("select j from Job j where j.id = ?1")
	Job findOneJobById(int id);

	@Query("select j.job from Application j where j.worker.id = ?1")
	Collection<Job> findManyByWorkerId(int activeRoleId);

	@Query("select j from Job j where j.finalMode = 1 and j.deadline > ?1 ")
	Collection<Job> findManyActiveJob(Date d);

}
