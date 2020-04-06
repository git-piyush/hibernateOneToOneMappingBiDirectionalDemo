package com.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.demo.entity.Instructor;
import com.hibernate.demo.entity.InstructorDetails;

public class CreateInstructorDemo {

	public static void main(String[] args) {
		//Create the session factory
		SessionFactory factory = new Configuration().configure().addAnnotatedClass(Instructor.class).addAnnotatedClass(InstructorDetails.class).buildSessionFactory();
		
		//get the current session factory
		Session currentSession = factory.getCurrentSession();
		
		try {
			//begin transaction
			currentSession.beginTransaction();
			
			//create instructor object
			Instructor theInstructor = new Instructor("Jeevan", "Kumar", "Jeevan@gmail.com");
			
			//create instructor details object
			InstructorDetails theInstructorDetails = new InstructorDetails("Jeevanyoutubechannel@youtube.com","Coading");
			
			//set instructor details in instructor
			//theInstructor.setInstructorDetailId(theInstructorDetails);
			theInstructorDetails.setInstructor(theInstructor);
			
			//save instructor
			currentSession.save(theInstructorDetails);
			System.out.println(theInstructorDetails);
			//commit transaction
			currentSession.getTransaction().commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception occurs");
		}finally {
			currentSession.close();
		}

	}

}
