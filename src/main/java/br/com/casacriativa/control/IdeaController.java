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

import br.com.casacriativa.dao.IdeaDAO;
import br.com.casacriativa.model.Idea;

@Controller
@RequestMapping("/")
public class IdeaController {
	
	@Autowired
	private IdeaDAO ideaDao;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView view = new ModelAndView("ideas/home");
		List<Idea> list = ideaDao.getAll().stream().limit(2).collect(Collectors.toList());
		view.addObject("ideas",list);
		return view;
	}
	
	@RequestMapping("/pageIdeas")
	@Cacheable(value = "listIdeas")
	public ModelAndView pageIdeas(@ModelAttribute("result") String result) {
		ModelAndView view = new ModelAndView("ideas/ideas");
		view.addObject("result", result);
		view.addObject("ideas", ideaDao.getAll());
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
		}
		return view;
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView update(Integer ideaId) {
		ModelAndView view = new ModelAndView("ideas/update");
		view.addObject("idea", ideaDao.find(ideaId));
		return view;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@CacheEvict(value = "listIdeas",  allEntries = true)
	public ModelAndView delete(Integer ideaId) {
		ideaDao.remove(ideaId);
		return new ModelAndView("redirect:/pageIdeas");
	}

}
