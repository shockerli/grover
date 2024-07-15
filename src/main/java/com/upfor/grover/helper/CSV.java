package com.upfor.grover.helper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;

import javax.servlet.http.HttpServletResponse;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class CSV {

    public static String sysTmpDir() {
        return System.getProperty("java.io.tmpdir");
    }

    public static void writeStringToCsv(String filePath, List<String> data) throws IOException {
        try (FileWriter csvWriter = new FileWriter(filePath)) {
            for (String rowData : data) {
                csvWriter.append(rowData);
                csvWriter.append("\n");
            }
        }
    }

    public static void writeListToCsv(String filePath, List<List<String>> data) throws IOException {
        List<String> items = new ArrayList<>();
        for (List<String> datum : data) {
            items.add(String.join(",", datum));
        }
        writeStringToCsv(filePath, items);
    }

    public static void writeDataToCsv(String filePath, List<CsvLineBuilder> data) throws IOException {
        List<List<String>> items = new ArrayList<>();
        if (!data.isEmpty()) {
            List<String> header = data.get(0).buildCsvHeader();
            if (header != null && !header.isEmpty()) items.add(header);
        }
        for (CsvLineBuilder datum : data) {
            items.add(datum.buildCsvLine());
        }
        writeListToCsv(filePath, items);
    }

    public static void writeDataToTmpCsv(String filename, List<CsvLineBuilder> data) throws IOException {
        writeDataToCsv(sysTmpDir() + filename, data);
    }

    public static void writeHeaders(HttpServletResponse response, String filename) {
        try {
            filename = URLEncoder.encode(filename, StandardCharsets.UTF_8.name());
            response.setContentType("application/csv");
            response.setCharacterEncoding(StandardCharsets.UTF_8.name());
            response.setHeader(HttpHeaders.PRAGMA, "public");
            response.setHeader(HttpHeaders.CACHE_CONTROL, "max-age=30");
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename);
            response.getOutputStream().write(new byte[]{(byte) 0xEF, (byte) 0xBB, (byte) 0xBF}); // BOM头
        } catch (IOException e) {
            log.error("writeHeaders ioStream error:{}", e.getMessage(), e);
        }
    }

    public static void writeLine(HttpServletResponse response, List<String> line) {
        try {
            response.getOutputStream().write((String.join(",", line) + "\r\n").getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            log.error("writeLine ioStream error:{}", e.getMessage(), e);
        }
    }

    public static void writeData(HttpServletResponse response, List<CsvLineBuilder> data) {
        if (data == null || data.isEmpty()) {
            return;
        }
        List<String> header = data.get(0).buildCsvHeader();
        if (header != null && !header.isEmpty()) {
            writeLine(response, header);
        }
        for (CsvLineBuilder datum : data) {
            writeLine(response, datum.buildCsvLine());
        }
    }

    // 实现本接口，可便捷写入和导出CSV
    public interface CsvLineBuilder {
        List<String> buildCsvHeader();

        List<String> buildCsvLine();
    }

}
