package org.andy.work.appserver.model.impl;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.andy.work.appserver.model.IComment;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@SuppressWarnings("serial")
@Entity
@Table(name="comment")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region="org.andy.work.appserver.model.impl.Comment")
public class Comment implements Serializable,IComment  {
	
   public Integer commentid;
   public String comment;
   public Integer number;
   public String time;
   public Integer everythingnumber;
   
   
   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   public Integer getcommentid(){
	   return this.commentid;
   }
   public void setcommentid(Integer commentid){
	   this.commentid=commentid;
   }
	
   @Column(name="comment",length=200,nullable=false)
   public String getcomment(){
	   return this.comment;
   }
   public void setcomment(String comment){
	   this.comment=comment;
   }
   
   @Column(name="number",unique=false)
   public Integer getthingsid(){
	   return this.number;
   }
   public void setthingsid(Integer thingsid){
	   this.number=thingsid;
   }
   
   @Column(name="time",length=50,unique=false)
   public String gettime(){
	   return this.time;
   }
   public void settime(String time){
	   this.time=time;
   }
   
   @Column(name="everythingnumber",length=50)
   public Integer geteverythingnumber(){
	   return this.everythingnumber;
   }
   public void seteverythingnumber(Integer everythingnumber){
	   this.everythingnumber = everythingnumber;
   }
   
   
   
}
