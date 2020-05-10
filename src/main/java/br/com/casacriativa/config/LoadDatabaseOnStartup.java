package br.com.casacriativa.config;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import br.com.casacriativa.dao.CategoryDAO;
import br.com.casacriativa.dao.IdeaDAO;
import br.com.casacriativa.model.Category;
import br.com.casacriativa.model.Idea;

@Component
public class LoadDatabaseOnStartup{
		
	@Autowired
	private CategoryDAO categoryDao;

	@Autowired
	private IdeaDAO ideaDao;

	@PostConstruct
	public void onApplicationEvent() {
		Category food = new Category("Alimentação");
		Category health = new Category("Saúde");
		Category hobby = new Category("Hobby");
		Category pro = new Category("Profissional");
		categoryDao.save(food);
		categoryDao.save(hobby);
		categoryDao.save(pro);
		categoryDao.save(health);
		
		String lorem = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis vel erat eleifend, ornare lacus non, convallis augue. Curabitur suscipit id nisi et eleifend. Mauris et placerat nisl. Donec ac dolor ornare, vestibulum nisl porta, finibus nibh.";
		String ipsum = "Vivamus tempus consequat quam nec luctus. Duis mattis, nunc eget feugiat luctus, metus mauris egestas mi, non pharetra dui tortor egestas tortor. Nam porttitor ultrices mattis. Nulla quis sem at nulla malesuada bibendum in nec magna.";
		
		ideaDao.save(new Idea("Siga Receitas", food, lorem, "https://image.flaticon.com/icons/svg/2729/2729077.svg", "https://www.flaticon.com/free-icon/recipe_2729077"));
		ideaDao.save(new Idea("Faça Esses Exercícios", health, lorem, "https://image.flaticon.com/icons/svg/2729/2729030.svg", "https://www.flaticon.com/free-icon/puzzle_2729030" ));
		ideaDao.save(new Idea("Faça Vídeos", hobby, ipsum, "https://image.flaticon.com/icons/svg/2729/2729060.svg" , "https://www.flaticon.com/free-icon/puzzle_2729030" ));
		ideaDao.save(new Idea("Faça Esse Curso", pro, ipsum, "https://image.flaticon.com/icons/svg/2729/2729018.svg", "https://www.flaticon.com/free-icon/puzzle_2729030"));
	}
	
	@Bean
	public List<Category> categories(){
		return categoryDao.getAll();
	}
    	
}
