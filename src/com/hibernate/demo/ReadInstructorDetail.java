package com.hibernate.demo;

import javax.persistence.CascadeType;
import javax.persistence.OneToOne;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.demo.entity.Instructor;
import com.hibernate.demo.entity.InstructorDetails;

public class ReadInstructorDetail {

	public static void main(String[] args) {
		//Create the session factory
		SessionFactory factory = new Configuration().configure().addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetails.class).buildSessionFactory();
		
		//get the current session
		Session currentSession = factory.getCurrentSession();
		
		try {
			//begin transaction
			currentSession.beginTransaction();
			
			//get the instructor detail object
			int theId = 15;
			InstructorDetails theInstructorDetails = currentSession.get(InstructorDetails.class, theId);
			
			//print the instructor detail
			System.out.println("Instructor details is " +theInstructorDetails);
			
			//print the associated instructor
			System.out.println("Associated Instructor is "+theInstructorDetails.getInstructor());
			
			//commit  the transaction
			currentSession.getTransaction().commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception occur");
		}finally {
			currentSession.close();
			factory.close();
		}

	}

}
