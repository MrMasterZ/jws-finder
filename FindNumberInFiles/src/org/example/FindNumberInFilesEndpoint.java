package org.example;

import org.example.findNumberInFiles.ServiceRequestDocument;
import org.example.findNumberInFiles.ServiceRequestDocument.ServiceRequest;
import org.example.findNumberInFiles.ServiceResponseDocument;
import org.example.findNumberInFiles.ServiceResponseDocument.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;

@Endpoint
public class FindNumberInFilesEndpoint {
	private static final String namespaceUri = "http://www.example.org/FindNumberInFiles";
	private FindNumberInFiles findNumberInFiles;

	@Autowired
	public void findNumberInFiles(FindNumberInFiles findNumber) {
		this.findNumberInFiles = findNumber;
	}

	@PayloadRoot(localPart = "ServiceRequest", namespace = namespaceUri)
	public ServiceResponseDocument getService(ServiceRequestDocument request) throws Exception {
		ServiceRequestDocument reqDoc = request;
		ServiceRequest req = reqDoc.getServiceRequest();
		ServiceResponseDocument respDoc = ServiceResponseDocument.Factory.newInstance();
		ServiceResponse resp = respDoc.addNewServiceResponse();

		int userNumber = req.getNumber();
		String findMessage = findNumberInFiles.findNumber(userNumber).toString();

		resp.setReply(findMessage);

		return respDoc;
	}
}