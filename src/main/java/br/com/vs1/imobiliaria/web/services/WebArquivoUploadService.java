/*
 * Copyright (c)  @vsantos1 - https://github.com/vsantos1
 * 2022-2023.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package br.com.vs1.imobiliaria.web.services;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.logging.Logger;

@Service
public class WebArquivoUploadService {
    private String uploadDir = System.getProperty("user.home");

    private final Logger logger = Logger.getLogger(WebArquivoUploadService.class.getName());

    private final List<String> ALLOWED_IMAGE_EXTENSIONS = List.of("jpg", "jpeg", "png", "gif", "bmp", "svg", "webp");


    protected void createDirectory(String path) {
        File uploadDir = new File(path);

        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }
    }

    protected void setPathContext(String folderName) {
        String DIR = this.uploadDir + File.separator + "uploads";


        if (!folderName.isEmpty() || !folderName.isBlank()) {
            this.uploadDir = this.uploadDir + File.separator + folderName;

        } else {
            this.uploadDir = DIR;
        }
    }


    /**
     * @param fileName String
     * @param part     Part
     * @return void
     */
    protected void saveFile(String fileName, MultipartFile part) {

        File file = new File(uploadDir, fileName);

        try (InputStream input = part.getInputStream()) {
            Files.copy(input, file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * @param unique   boolean
     * @param fileName String
     * @return String
     */
    protected String generateUniqueFilename(boolean unique, String fileName) {


        if (unique) {
            String fileExtension = fileName.substring(fileName.lastIndexOf('.') + 1);
            fileName = fileName.replace(fileName.substring(fileName.lastIndexOf('.')), "");
            return fileName + "_" + UUID.randomUUID() + "." + fileExtension;
        }

        return fileName;

    }

    protected void isSizeAllowed(MultipartFile part, int maxFileSize) throws IOException {

        // 1MB
        int DEFAULT_FILE_SIZE = 1024 * 1024;

        maxFileSize = DEFAULT_FILE_SIZE * maxFileSize;

        logger.info("File size: " + part.getSize() * DEFAULT_FILE_SIZE);

        if (part.getSize() > maxFileSize) {
            throw new IOException("File size is too large");
        }
    }

    /**
     * @param folderName String
     * @param fileName   String
     * @param part       Part
     */
    protected String execute(String folderName, String fileName, MultipartFile part, boolean unique) {
        fileName = this.generateUniqueFilename(unique, fileName);

        this.setPathContext(folderName);

        this.createDirectory(this.uploadDir);

        this.saveFile(fileName, part);
        return fileName;
    }


    /**
     * @param part        MultipartFile
     * @param folderName  String
     * @param unique      boolean
     * @param maxFileSize int (MB)
     * @return void
     * @throws IOException
     */
    public String imageUpload(MultipartFile part, String folderName, boolean unique, int maxFileSize) throws IOException {

        // Get the filename and extension
        String fileName = Paths.get(Objects.requireNonNull(part.getOriginalFilename())).getFileName().toString();
        String fileExtension = fileName.substring(fileName.lastIndexOf('.') + 1);

        if (!ALLOWED_IMAGE_EXTENSIONS.contains(fileExtension)) {
            throw new IOException("Invalid image file extension type");
        }

        // Check if the file size is allowed
        this.isSizeAllowed(part, maxFileSize);

        // Do the magic
        return this.execute(folderName, fileName, part, unique);
    }

    public void downloadFileFromDisk(String fileName, HttpServletResponse response) throws IOException {
        String filePath = this.uploadDir + File.separator + "uploads" + File.separator + fileName;

        Path file = Paths.get(filePath);

        if (Files.exists(file)) {
            response.setContentType("application/octet-stream");
            response.setStatus(HttpStatus.OK.value());
            response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

            try (InputStream in = Files.newInputStream(file)) {
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = in.read(buffer)) != -1) {
                    response.getOutputStream().write(buffer, 0, bytesRead);
                }
            }
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "File not found");
        }
    }

    public byte[] getFileFromDisk(String fileName) throws IOException {
        String filePath = this.uploadDir + File.separator + "uploads" + File.separator + fileName;

        Path file = Paths.get(filePath);

        if (Files.exists(file)) {
            return Files.readAllBytes(file);
        } else {
            throw new IOException("File not found");
        }
    }

    public void deleteFileFromDisk(String fileName) throws IOException {
        String filePath = this.uploadDir + File.separator + "uploads" + File.separator + fileName;

        Path file = Paths.get(filePath);

        if (Files.exists(file)) {
            Files.delete(file);
        } else {
            throw new IOException("File not found");
        }
    }
}