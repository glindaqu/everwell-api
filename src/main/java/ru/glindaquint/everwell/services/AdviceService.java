package ru.glindaquint.everwell.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import org.springframework.stereotype.Service;

@Service
public class AdviceService {
  private static final Path ADVICE_FILE_PATH = Paths.get("src/main/resources/advices.txt");
  private List<String> cachedAdvices;

  public AdviceService() {
    this.cachedAdvices = Collections.unmodifiableList(loadAdvices());
  }

  private List<String> loadAdvices() {
    if (!Files.exists(ADVICE_FILE_PATH)) {
      return getDefaultAdvices();
    }

    List<String> loadedAdvices = new ArrayList<>();

    try (BufferedReader reader = Files.newBufferedReader(ADVICE_FILE_PATH)) {
      String line;
      while ((line = reader.readLine()) != null) {
        if (!line.trim().isEmpty()) {
          loadedAdvices.add(line);
        }
      }
    } catch (IOException e) {
      System.err.println("Ошибка чтения файла: " + e.getMessage());
      return getDefaultAdvices();
    }

    return loadedAdvices.isEmpty() ? getDefaultAdvices() : loadedAdvices;
  }

  private List<String> getDefaultAdvices() {
    return List.of(
        "Пей больше воды! 💧",
        "Сделай 5-минутную разминку 🏃",
        "Проветри комнату 🌬️",
        "Выпрями спину 🪑",
        "Улыбнись своему отражению 😊");
  }

  public String getRandomAdvice() {
    return cachedAdvices.isEmpty()
        ? "Сегодня без советов — просто отдохни!"
        : cachedAdvices.get(ThreadLocalRandom.current().nextInt(cachedAdvices.size()));
  }

  public synchronized void refreshCache() {
    this.cachedAdvices = Collections.unmodifiableList(loadAdvices());
  }
}
