package com.demo.report;

import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;


public interface ReportService {
    File getExportFile() throws JRException, FileNotFoundException;

}
