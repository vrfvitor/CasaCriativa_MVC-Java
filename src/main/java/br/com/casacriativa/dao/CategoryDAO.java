package br.com.casacriativa.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.com.casacriativa.model.Category;

@Repository
@Transactional
public class CategoryDAO {

	@PersistenceContext
	private EntityManager manager;
	
	public void save(Category category) {
		if(category.getId() == null) {
			manager.persist(category);
			return;
		}
		manager.merge(category);
	}
	
	public List<Category> getAll(){
		return manager.createQuery("SELECT c FROM Category c", Category.class).getResultList();
	}
	
	public Category get(Integer id) {
		return manager.find(Category.class, id);
	}
}
