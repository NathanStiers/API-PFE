package be.vinci.ipl;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;

import org.junit.Test;
import static org.junit.Assert.assertNotNull;

import be.vinci.ipl.MyResource;
import be.vinci.ipl.resource.ItemResource;

public class ItemResourceTest extends JerseyTest {

	@Override
	protected Application configure() {
		return new ResourceConfig(ItemResource.class);
	}

	/**
	 * Test to see that the images are not empty.
	 */
	@Test
	public void testImages() {
		Response responseMsg = target().path("images").request().get();
		assertNotNull(responseMsg);
	}
}
