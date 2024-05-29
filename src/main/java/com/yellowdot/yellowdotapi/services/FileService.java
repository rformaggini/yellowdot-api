package com.yellowdot.yellowdotapi.services;

import com.yellowdot.yellowdotapi.entities.Bill;
import com.yellowdot.yellowdotapi.entities.File;

public interface FileService {
    File findByBill(Bill bill);
}
