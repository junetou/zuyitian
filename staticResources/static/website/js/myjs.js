$(function(){
		var nt = !1;
		$(window).on("scroll",
			function() {
			var st = $(document).scrollTop()-40;
			nt = nt ? nt: $(".mainCentent").offsetTop;
			var sel=$(".leftContent");
			if (nt < st) {
				sel.addClass("fixed");
			} else {
				sel.removeClass("fixed");
			}
	
			if ($(window).scrollTop()>$(window).height()){  
	            $(".floatbar").fadeIn(500);  
	        }  
	        else  
	        {  
	            $(".floatbar").fadeOut(500);  
	        }  
	
		});
	
		 $(".bar_back").click(function(){  
	        $('body,html').animate({scrollTop:0},1000);  
	        return false;  
	    }); 
	    
/*	$('.content03 .bottom .center li a').hover(function(){
		$(this).siblings().css({"color":"#eb4f4f"});

	},function(){
		$(this).siblings('.content03 .bottom .center li a p1').css({"color":"#000"});
		$(this).siblings('.content03 .bottom .center li a p2').css({"color":"#929292"});
	
	});*/


	function banner(){
		var num = 0;
		//加工函数来处理背景图片
		/*$('.banner_in ul li').each(function(index, element) {
			var nu = index+1;
			//var img = $(this).date("img");
			$(element).css('background','url(images/banner0'+ nu +'.jpg) no-repeat center center')
		});
		*/	
		$('.banner ol li').click(function(e) {
			$(this).addClass('current').siblings().removeClass();//完成角标的工作
			
			$('.banner ul li').eq($(this).index()).css({"display":"block"})/*.hide().stop().fadeIn(1000)*/.siblings().css({"display":"none"})/*.hide()*/
			
			num=$(this).index();
		});	
		var num=0;
		var timer=null;	
		timer=setInterval(fn,5000)		
		function fn(){	
			num++
			if(num>3){num=0}
			//$('title').html(num)
			
			$('.banner ol li').eq(num).addClass('current').siblings().removeClass();//完成角标的工作
			$('.banner ul li').eq(num).css({"display":"block"})/*stop().hide().fadeIn(1000)*/.siblings().css({"display":"none"})/*.hide()*/
		}	
		$('.banner ol li').mouseover(function(e) {
			clearInterval(timer)
		}).mouseout(function(e) {
			clearInterval(timer)
			timer=setInterval(fn,5000)
		});
	};
	banner();
	
	
	function index_navhover(){
		
		$('.navs .nav_main').hover(function(e) {

	        var num = $(this).index();
			//$('.nav_bottom').show();/*eq( num ).show().siblings().hide();*/
			
			$(this).children('.navs .nav_bottom').stop().show();
			$(this).siblings('.navs .nav_bottom').stop().hide();			
	    },function(){
			/*$('.navs .nav_bottom li').mouseout(function(e) {*/
			$('.navs .nav_bottom').stop().hide();
	   });	    	
	    
		    
	};
	index_navhover();
	
	
	function index_cont1_tab(){
/*		$('.content01 .bottom li').hover(function(){
			$(this).css('background','#d2d2d2');
		},function(){
			$(this).css('background','#efefef');
		});
		*/
		$('.content01 .bottom .left .list .list-side').click(function(){
			var num = $(this).index();
			$(this).css({"background":"#eb504f","color":"#fff"}).siblings().css({"background":"#efefef","color":"#5b5b5b"});
			$(this).children('.content01 .bottom .left .article').stop().show();
			$(this).siblings().children('.content01 .bottom .left .article').stop().hide();
		});

	}
	index_cont1_tab();
	
	
	function index_cont3_tab(){
		$('.content03 .center .left li:first-child').addClass("tagLeft");
		/*.css('background','url(img/iconChart_tagLeft.png) no-repeat right 10px');*/
		$('.content03 .center .left li').click(function(){
			var num = $(this).index();
			if(num%2==0){
				$(this).addClass("tagLeft").siblings().removeClass("tagLeft ragRight");
			}else{
				$(this).addClass("ragRight").siblings().removeClass("tagLeft ragRight");		
			};
			$(this).children('.content03 .center .centers').stop().show();
			$(this).siblings().children('.content03 .center .centers').stop().hide();
		});
	}
	index_cont3_tab();	
	
	
	function list_nav_Side(){
		$(window).scroll(function(e) {
	        var topH = $(window).scrollTop();
			
			/*$('.mianyiContent01 .left').html(topH)*/
			
			if(topH>207){
				$('.Content-left').css('position','fixed').css('top',0);
			}else{
				$('.Content-left').css('position','absolute').css('top','210px');

			}
	    });
	}
	list_nav_Side();

	$('#nornal-search').on('click',function(event){
		event.stopPropagation();
		$(this).css('display','none');
		$('#active-search').css('display','block');
		$('#search-text').focus();
		$('#hot-search').css('display','block');
	})
	$(document).on('click',function(event){
		event.stopPropagation();
		$('#active-search').css('display','none');
		$('#hot-search').css('display','none');
		$('#nornal-search').css('display','block');
	})
	$('#search-text').on('keydown',function(){
		$('.cross-wrap').css('display','block');
	})
	$('#search-text').on('click',function(event){
		event.stopPropagation();
		$('.cross-wrap').css('display','block');
		$('#active-search').css('display','block');
		$('#search-text').focus();
		$('#hot-search').css('display','block');
	})
	$('.search-box').delegate('.cross-wrap','click',function(event){
		event.stopPropagation();
	    $('#nornal-search').css('display','none');
		$('#active-search').css('display','block');
		$('#search-text').focus();
		$('#hot-search').css('display','block');
		$('#search-text').val("");
		$('.cross-wrap').css('display','none');
	});


});

