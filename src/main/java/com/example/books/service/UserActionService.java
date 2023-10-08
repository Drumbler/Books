package com.example.books.service;


import com.example.books.entity.User;
import com.example.books.entity.UserAction;
import com.example.books.repository.UserActionRepository;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class UserActionService {
    private UserActionRepository userActionRepository;

    private enum ActionType {
        ADD,
        REMOVE,
        EDIT
    }

    public List<UserAction> getAll() {
        return userActionRepository.findAll();
    }

    public void addAction(Task task, User user) {
        saveAction(ActionType.ADD.name(), task, user);
    }

    public void deleteAction(Task task, User user) {
        saveAction(ActionType.REMOVE.name(), task, user);
    }

    public void editAction(Task task, User user) {
        saveAction(ActionType.EDIT.name(), task, user);
    }

    private void saveAction(String action, Task task, User user) {
        UserAction userAction = new UserAction();
        userAction.setDescription(action + " " + task.toString());
        userAction.setUser(user);
        userAction.setDateActions(new Date(System.currentTimeMillis()));
        userActionRepository.save(userAction);
    }


}
