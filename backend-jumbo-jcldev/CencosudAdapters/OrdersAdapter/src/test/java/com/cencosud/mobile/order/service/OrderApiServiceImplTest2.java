package com.cencosud.mobile.order.service;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ClientBuilder.class, Response.class})
public class OrderApiServiceImplTest2 {

    static OrderApiServiceImpl orderApiServiceImpl;

    @BeforeClass
    public static void initialSetup() {
    	orderApiServiceImpl = new OrderApiServiceImpl();
    }

    @Test
    public void orderSearchGet() throws Exception {
        String orders = "lol-luiggi0925@gmail.com";
        Response response = mock(Response.class);
        doReturn(orders).when(response).readEntity(String.class);
        doReturn(200).when(response).getStatus();

        Invocation.Builder builder = mock(Invocation.Builder.class);
        doReturn(null).when(builder).header(anyString(), anyString());
        doReturn(response).when(builder).get();

        WebTarget fullTextTarget = mock(WebTarget.class);
        doReturn(fullTextTarget).when(fullTextTarget).path(anyString());
        doReturn(fullTextTarget).when(fullTextTarget).queryParam(anyString(), anyString());
        doReturn(builder).when(fullTextTarget).request(APPLICATION_JSON);
        Client client = mock(Client.class);
        when(client.target(anyString())).thenReturn(fullTextTarget);
        mockStatic(ClientBuilder.class);
        when(ClientBuilder.newClient()).thenReturn(client);

        mockStatic(Response.class);

        Response finalResponse = mock(Response.class);
        doReturn(orders).when(finalResponse).readEntity(String.class);
        ResponseBuilder responseBuilder = mock(ResponseBuilder.class);
        doReturn(responseBuilder).when(responseBuilder).entity(anyObject());
        doReturn(finalResponse).when(responseBuilder).build();
        when(Response.status(200)).thenReturn(responseBuilder);

        Response res = orderApiServiceImpl.findOrders("r2","v1", "luiggi0925@gmail.com");
        assertThat(res, notNullValue());
        assertThat(res.readEntity(String.class), containsString("luiggi0925@gmail.com"));
    }
}
