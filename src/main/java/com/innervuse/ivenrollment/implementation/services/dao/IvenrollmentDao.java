package com.innervuse.ivenrollment.implementation.services.dao;

//import java.net.InetAddress;
//import java.net.UnknownHostException;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.CassandraTemplate;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
import com.google.gson.Gson;
import com.innervuse.ivenrollment.entities.Enrollment;

@Path( "/enrollment" )
public class IvenrollmentDao
{
	private static final Logger LOGGER = LoggerFactory.getLogger(IvenrollmentDao.class);
	
	private static Cluster cluster;
	private static Session session;
	 
    @GET
    @Path( "/{username}" )
    public String message( @PathParam ( "username" ) String username )
    {
    	try {

    		   //cluster = Cluster.builder().addContactPoints(InetAddress.getLocalHost()).build();
    		   cluster = Cluster.builder().addContactPoints("127.0.0.1").build();
    		
    		   session = cluster.connect( "innervuse" );

    		   CassandraOperations cassandraOps = new CassandraTemplate( session );

    		   cassandraOps.insert( new Enrollment( username, "", "20150927", "10" ) );

    		   Select s = QueryBuilder.select().all().from( "enrollment" );
    		   s.where( QueryBuilder.eq( "username", username ) );
    		   
    		   List<Row> results = cassandraOps.query(s).all();
    		   
    		   String json = null;
    		   for( Row row : results )
    		   {
    			   Enrollment enrollment = new Enrollment( row.getString( "username" )
    					                                  ,row.getString( "enddate" )
    					                                  ,row.getString( "startdate" )
    					                                  ,row.getString( "userlevel" ) );
    			   
    			  json = new Gson().toJson( enrollment );
    		   }
    		   LOGGER.info(cassandraOps.query(s).all().toString());
    		       		   
    		   return json;

    		  } 
    	catch ( Exception e ) 
    	{
    		LOGGER.error( e.getStackTrace().toString() );
    		
    		return e.getMessage();
    	}
    }

    @POST
    public String lowerCase(final String message)
    {
        return "Hi REST!".toLowerCase();
    }
}