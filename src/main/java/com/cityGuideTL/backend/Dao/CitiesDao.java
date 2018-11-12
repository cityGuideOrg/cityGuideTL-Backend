package com.cityGuideTL.backend.Dao;

import com.cityGuideTL.backend.Models.Cities;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CitiesDao {

	@Autowired
	private SessionFactory sessionFactory;
	public void createCities(Cities cities){
		Session session= null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			Integer id =(Integer) session.save(cities);
			System.out.println("CitiesHib is created With Id::"+id);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
