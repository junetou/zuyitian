package org.andy.work.admin.controller.content;

import javax.annotation.Resource;

import org.andy.work.appserver.model.impl.Taoge;
import org.andy.work.appserver.service.ITaogeMain;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/taoge")
public class TaogeControer {
	
	@Resource
	ITaogeMain iTaogeMain;
	
	@RequestMapping(value="/add/{name}")
	@ResponseBody
	public String add(@PathVariable(value="name")String name){
		System.out.println("---------------->");
		Taoge taoge = new Taoge();
		taoge.setName(name);
		iTaogeMain.add(taoge);
		System.out.println(taoge.getName()+"---------------->");
		return "Success";
	}

} 
