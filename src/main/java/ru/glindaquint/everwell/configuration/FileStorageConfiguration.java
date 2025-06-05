package ru.glindaquint.everwell.configuration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FileStorageConfiguration {

  @Value("${file.upload-dir}")
  private String uploadDir;

  @Bean
  public Path fileStorageLocation() throws IOException {
    Path path = Paths.get(uploadDir);
    Files.createDirectories(path); // Создаем папку, если её нет
    return path;
  }
}
