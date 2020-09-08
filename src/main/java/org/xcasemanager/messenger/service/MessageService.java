package org.xcasemanager.messenger.service;

import org.xcasemanager.messenger.web.rest.resource.ExecutionMessageDto;

public interface MessageService {

    void addMessage(ExecutionMessageDto message);

}
