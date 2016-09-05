package org.andy.work.admin.controller.content;

import javax.annotation.Resource;

import org.andy.work.appserver.model.impl.Newhour;
import org.andy.work.appserver.service.INewhourMain;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/newhour")

public class NewhourController {

	@Resource
	INewhourMain iNewhourMain;
	
	@RequestMapping(value="/newadd/{name}")
	@ResponseBody
	public ModelAndView add(@PathVariable(value="name")String name,ModelAndView model){
		Newhour newhour = new Newhour();
		newhour.setName(name);
		iNewhourMain.add(newhour);
		System.out.println(name);
	//	System.out.println(newhour.getName()+"---------------->");
        model.setViewName("test");
		return model;
	}

	
	
	
	
}
