package com.demo.tourist;

import com.demo.UrlMapping;
import com.demo.tourist.model.dto.TouristDTO;
import com.demo.report.ReportService;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping(UrlMapping.TOURIST)
@RequiredArgsConstructor
public class TouristController {

    private final TouristService touristService;
    private final ReportService reportService;

    @GetMapping
    public List<TouristDTO> allTourist() {
        return touristService.findAll();
    }

    @PostMapping
    public TouristDTO create(@RequestBody TouristDTO tourist) {
        return touristService.create(tourist);
    }

    @PatchMapping
    public TouristDTO edit(@RequestBody TouristDTO tourist) {
        return touristService.edit(tourist);
    }

    @DeleteMapping
    public void deleteAll() { touristService.deleteAll(); }

    @DeleteMapping(UrlMapping.ID)
    public void deleteById(@PathVariable String id) {
        touristService.deleteById(Long.parseLong(id));}


    @GetMapping(UrlMapping.EXPORT_REPORT)
    public ResponseEntity<Resource> exportReport() throws IOException, JRException {
        File file = reportService.getExportFile();
        Path path = Paths.get(file.getAbsolutePath());
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

    return ResponseEntity.ok()
            .headers(headers)
            .contentLength(file.length())
            .contentType(MediaType.APPLICATION_OCTET_STREAM)
            .body(resource);
    }
}
