package com.hibernate.demo;

import javax.persistence.CascadeType;
import javax.persistence.OneToOne;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.demo.entity.Instructor;
import com.hibernate.demo.entity.InstructorDetails;

public class DeleteInstructorDemo {

	public static void main(String[] args) {
		//Create the session factory
		SessionFactory factory = new Configuration().configure().addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetails.class).buildSessionFactory();
		
		//get the current session
		Session currentSession = factory.getCurrentSession();
		
		try {
			//begin transaction
			currentSession.beginTransaction();
			
			//get the instructor by primary key
			InstructorDetails theInstructorDetails = currentSession.get(InstructorDetails.class, 5);
			
			//delete the instructor
			//it will delete the respective instructor_details also because of @OneToOne(cascade=CascadeType.ALL)
			currentSession.delete(theInstructorDetails);
			
			//commit  the transaction
			currentSession.getTransaction().commit();
			
		} catch (Exception e) {
			System.out.println("Exception occur");
		}finally {
			currentSession.close();
		}

	}

}
