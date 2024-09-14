package com.bit.final_project.services.impl;

import com.bit.final_project.exceptions.http.EntityExistsException;
import com.bit.final_project.models.Windows;
import com.bit.final_project.repositories.Window.WindowRepository;
import com.bit.final_project.services.WindowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;

@Service
public class WindowServiceImpl implements WindowService {
    @Autowired
    WindowRepository windowRepository;
    @Override
    public Windows getWindowById(String id) {
        return windowRepository.findById(id).orElseThrow(()-> new EntityExistsException("Door not found with id: " + id));
    }
}
