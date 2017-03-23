package com.example.controller;

import java.net.InetAddress;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

import com.example.model.Employee;
import com.google.gson.Gson;

@RestController
public class ElasticOperations {

	private PreBuiltTransportClient preBuiltTransportClient;

	@RequestMapping(value = "/uploadData", method = RequestMethod.POST)
	public ResponseEntity<String> uploadData(@RequestBody Employee emp) {
		try {
			Settings settings = Settings.builder().put("cluster.name", "elasticsearch_nisumconsulting").build();
			preBuiltTransportClient = new PreBuiltTransportClient(settings);
			TransportClient client = preBuiltTransportClient
					.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
			client.prepareIndex("sample", "data", String.valueOf(emp.getEmpNo()))
					.setSource(new Gson().toJson(emp))
	        .get();
			return new ResponseEntity<String>("Successfully Uploaded to elastic Search", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("ExceptionOccured while Uploading", HttpStatus.BAD_REQUEST);
		}
	}

}
