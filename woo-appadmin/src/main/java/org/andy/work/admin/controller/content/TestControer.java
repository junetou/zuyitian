package org.andy.work.admin.controller.content;

import javax.annotation.Resource;

import org.andy.work.appserver.model.INewtest;
import org.andy.work.appserver.model.ITest;
import org.andy.work.appserver.model.impl.Newtest;
import org.andy.work.appserver.model.impl.Test;
import org.andy.work.appserver.service.INewtestMain;
import org.andy.work.appserver.service.ITestMain;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
@RequestMapping(value="/test")
public class TestControer {
	
	/*
	@Resource
	private ITestMain itestMain;
	
	@RequestMapping(value="/{id}")
	@ResponseBody
	public String AddTest(@PathVariable(value="id")Integer id1, Test tes){
		System.out.println("id1 = " + id1 + ", id = " + tes.getId());
		System.out.println("name = " + tes.getName() + ", id = " + tes.getId());
		ITest test = this.itestMain.test(tes.getId());//读取test数据库里id
		System.out.println(test.getName());//获取数据库
		return "success";
	}
	*/
	/*
	@Resource
	private ITestMain iTestM;
	
	@RequestMapping(value="show/{id}")
	@ResponseBody
	public String Test(@PathVariable(value="id")Integer id2,Test tex){
	
		System.out.println("id1=" + id2 + ",id=" + tex.getId());
		ITest test1=this.iTestM.test(tex.getId());
		System.out.println(test1.getName());
		return "success";
	}
	
    */
	
	@Resource
	private INewtestMain iTestMain;
	
	@RequestMapping(value="show/{id}")
	@ResponseBody
	public String AddTest(@PathVariable(value="id")Integer id1, Newtest tes){
		System.out.println("id1 = " + id1 + ", id = " + tes.getId());
		System.out.println("name = " + tes.getId() + ", id = " + tes.getlat());
		INewtest test1 = this.iTestMain.test(tes.getId());//读取test数据库里id
		System.out.println(test1.getId());//获取数据库
		System.out.println(test1.getlat());
		System.out.println(test1.getlng());
		return "success";
	}
	
	
}
