package br.com.casacriativa.dao;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.com.casacriativa.model.Idea;

@Repository
@Transactional
public class IdeaDAO {
	
	@PersistenceContext
	private EntityManager em;
	
	public void save(Idea idea) {		
		if(idea.getId() == null) {
			em.persist(idea);
			return;
		}
		em.merge(idea);
	}
	
	public List<Idea> getAll(){
		return em.createQuery("SELECT i FROM Idea i ORDER BY i.id DESC", Idea.class).getResultList();
	}

	public void remove(Integer id) {
		em.remove(em.find(Idea.class, id));
	}

	public Object find(Integer id) {
		return em.find(Idea.class, id);
	}

	public List<Idea> read(String search, Integer categoryId) {
		
		if (search.isEmpty() && categoryId == null) {
			return Collections.emptyList();
		}
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Idea> query = builder.createQuery(Idea.class);
		Root<Idea> root = query.from(Idea.class);

		Predicate titleLike = builder.like(root.get("title"), "%" + search +"%");
		Predicate descLike = builder.like(root.get("description"), "%" + search +"%");
		Predicate orPredicates = builder.or(titleLike,descLike);

		if(categoryId != null) {			
			Predicate category = builder.equal(root.get("category"), categoryId);
			query.select(root).where(builder.and(category, orPredicates));
		} else {
			query.select(root).where(orPredicates);
		}
		
		TypedQuery<Idea> typedQuery = em.createQuery(query);
		return typedQuery.getResultList();
	}
	
}
