package com.yellowdot.yellowdotapi.services.impl;

import com.yellowdot.yellowdotapi.entities.Bill;
import com.yellowdot.yellowdotapi.entities.File;
import com.yellowdot.yellowdotapi.services.FileService;
import org.springframework.stereotype.Service;

@Service
public class FileServiceImpl implements FileService {

    @Override
    public File findByBill(Bill bill) {
        return null;
    }
}
