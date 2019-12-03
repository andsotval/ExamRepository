
package acme.features.authenticated.messagethread;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.messagethreads.Messagethread;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedMessagethreadRepository extends AbstractRepository {

	@Query("select a from Messagethread a where a.id = ?1")
	Messagethread findOneById(int id);

	//"select mt from Messagethread mt where ?1 in mt.users
	//@Query("select mt from Messagethread mt join mt.users u where u.id=?1")
	@Query("select mt from Messagethread mt inner join mt.users u with u.id=?1")
	Collection<Messagethread> findManyMine(int id);

}
