package com.app.person;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import com.jk.core.util.JK;
import com.jk.data.dataaccess.JKDataAccessFactory;
import com.jk.data.dataaccess.orm.JKObjectDataAccess;
import com.jk.services.server.JKAbstractRestController;

@Path("/persons")
public class Controller extends JKAbstractRestController{
	JKObjectDataAccess da = JKDataAccessFactory.getObjectDataAccessService();

	@GET
	public Response getAll() {
		List<Model> list = da.getList(Model.class);
		return Response.status(200).entity(list).build();
	}

	@GET
	@Path("/{id}")
	public Response find(@PathParam("id") int id) {
		Model person = da.find(Model.class, id);
		if (person == null) {
			return Response.status(404).build();
		}
		return Response.status(200).entity(person).build();
	}

	@POST
	public Response insert(Model person) {
		da.insert(person);
		return Response.status(201).entity(person).build();
	}

	@PUT
	@Path("/{id}")
	public Response update(@PathParam("id") int id, Model person) {
		JK.fixMe("Validate againest the provided id, and check if exists");
		da.update(person);
		return Response.status(201).entity(person).build();
	}

	@DELETE
	@Path("/{id}")
	public Response delete(@PathParam("id") int id) {
		da.delete(Model.class, id);
		return Response.status(201).build();
	}

}