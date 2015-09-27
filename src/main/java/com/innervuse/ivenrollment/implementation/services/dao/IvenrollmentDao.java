package com.innervuse.ivenrollment.implementation.services.dao;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.CassandraTemplate;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
import com.innervuse.ivenrollment.entities.Enrollment;

@Path("/greeting")
public class IvenrollmentDao
{
	private static final Logger LOG = LoggerFactory.getLogger(IvenrollmentDao.class);
	
	private static Cluster cluster;
	private static Session session;
	 
    @GET
    public String message()
    {
    	try {

    		   //cluster = Cluster.builder().addContactPoints(InetAddress.getLocalHost()).build();
    		   cluster = Cluster.builder().addContactPoints("127.0.0.1").build();
    		
    		   session = cluster.connect("innervuse");

    		   CassandraOperations cassandraOps = new CassandraTemplate(session);

    		   cassandraOps.insert(new Enrollment("bran", "", "20150927", "10"));

    		   Select s = QueryBuilder.select().all().from("enrollment");
    		   s.where(QueryBuilder.eq("username", "bran"));
    		   
    		   LOG.info(cassandraOps.query(s).all().toString());

    		   //cassandraOps.truncate("enrollment");
    		   
    		   return "ok";

    		  } catch (Exception e) {
    		   e.printStackTrace();
    		   return e.getMessage();
    		  }
    }

    @POST
    public String lowerCase(final String message)
    {
        return "Hi REST!".toLowerCase();
    }
}