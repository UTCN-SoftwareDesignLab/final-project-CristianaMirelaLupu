package com.demo.report;
import com.demo.tourist.TouristService;
import com.demo.tourist.model.dto.TouristDTO;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.List;


@Service
public class CSVReportService implements ReportService {

    private final TouristService touristService;
    private static final String FILE_NAME = "Report.csv";

    public CSVReportService(TouristService touristService) {
        this.touristService = touristService;
    }

    @Override
    public File getExportFile() {

        List<TouristDTO> loyalTourists = touristService.findLoyal();

        File file = new File(FILE_NAME);
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(file, false)) ) {

            StringBuilder sb = new StringBuilder();

            sb.append("id");
            sb.append(',');
            sb.append("Name");
            sb.append(',');
            sb.append("No of bookings");
            sb.append('\n');
            writer.write(sb.toString());

            for (TouristDTO t: loyalTourists) {

                StringBuilder lineSb = new StringBuilder();
                lineSb.append(t.getId().toString());
                lineSb.append(',');
                lineSb.append(t.getName());
                lineSb.append(',');
                lineSb.append(t.getContorBooking());
                lineSb.append('\n');
                writer.write(lineSb.toString());
            }

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return file;
    }
}
