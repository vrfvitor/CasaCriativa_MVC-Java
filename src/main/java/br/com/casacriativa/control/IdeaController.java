package br.com.casacriativa.control;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casacriativa.dao.CategoryDAO;
import br.com.casacriativa.dao.IdeaDAO;
import br.com.casacriativa.model.Category;
import br.com.casacriativa.model.Idea;

@Controller
@RequestMapping("/")
public class IdeaController {
	
	@Autowired
	private IdeaDAO ideaDao;

	@Autowired
	private CategoryDAO categoryDao;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView view = new ModelAndView("ideas/home");
		List<Idea> list = ideaDao.getAll().stream().limit(2).collect(Collectors.toList());
		view.addObject("ideas",list);
		view.addObject("categories", categoryDao.getAll());
		return view;
	}
	
	@RequestMapping("/pageIdeas")
	@Cacheable(value = "listIdeas")
	public ModelAndView pageIdeas(@ModelAttribute("result") String result) {
		ModelAndView view = new ModelAndView("ideas/ideas");
		view.addObject("result", result);
		view.addObject("ideas", ideaDao.getAll());
		view.addObject("categories", categoryDao.getAll());
		return view;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@CacheEvict(value = "listIdeas",  allEntries = true)
	public ModelAndView save(Idea idea) {
		ideaDao.save(idea);
		return new ModelAndView("redirect:/pageIdeas");	
	}
	
	@RequestMapping("/pageIdeas/idea")
	public ModelAndView read(String search, Integer categoryId, RedirectAttributes redirect) {
		ModelAndView view = new ModelAndView("ideas/ideas");
		List<Idea> read = ideaDao.read(search, categoryId);
		if(read.isEmpty()) {
			redirect.addFlashAttribute("result", "Nenhum item correspondente a pesquisa...");
			return new ModelAndView("redirect:/pageIdeas");
		} else {
			view.addObject("ideas", read);
			view.addObject("categories", categoryDao.getAll());
		}
		return view;
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView update(Integer ideaId) {
		ModelAndView view = new ModelAndView("ideas/update");
		view.addObject("idea", ideaDao.find(ideaId));
		view.addObject("categories", categoryDao.getAll());
		return view;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@CacheEvict(value = "listIdeas",  allEntries = true)
	public ModelAndView delete(Integer ideaId) {
		ideaDao.remove(ideaId);
		return new ModelAndView("redirect:/pageIdeas");
	}
	
	@RequestMapping("/charge")
	public ModelAndView charge() {
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
		return new ModelAndView("redirect:/");
	}
	
}
