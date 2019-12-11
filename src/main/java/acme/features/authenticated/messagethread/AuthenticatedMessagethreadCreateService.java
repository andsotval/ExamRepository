
package acme.features.authenticated.messagethread;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.messagethreads.Messagethread;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedMessagethreadCreateService implements AbstractCreateService<Authenticated, Messagethread> {

	@Autowired
	AuthenticatedMessagethreadRepository repository;


	@Override
	public boolean authorise(final Request<Messagethread> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Messagethread> request, final Messagethread entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "creationMoment");
	}

	@Override
	public void unbind(final Request<Messagethread> request, final Messagethread entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "owner", "title");
	}

	@Override
	public Messagethread instantiate(final Request<Messagethread> request) {
		Messagethread result;

		result = new Messagethread();

		return result;
	}

	@Override
	public void validate(final Request<Messagethread> request, final Messagethread entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void create(final Request<Messagethread> request, final Messagethread entity) {
		Date moment;
		Collection<Authenticated> users = null;
		moment = new Date(System.currentTimeMillis() - 1);
		entity.setCreationMoment(moment);
		users.add(entity.getOwner());
		entity.setUsers(users);
		this.repository.save(entity);

	}
}
