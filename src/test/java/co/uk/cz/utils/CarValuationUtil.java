package co.uk.cz.utils;

import co.uk.cz.model.CarDetails;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class CarValuationUtil {

    private static final Logger logger = LoggerFactory.getLogger(CarValuationUtil.class);

    public static List<String> getRegistrationNumbersFromInputFile(String inputFile) throws IOException {
        String content = Files.readString(Paths.get(inputFile));
        // Regex pattern to match UK car registration numbers
        String regex = "\\b[A-Z]{2}\\d{2}\\s?[A-Z]{3}\\b|\\b[A-Z]{3}\\d{4}\\b";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(content);
        List<String> regNumbers = new ArrayList<>();
        while (matcher.find()) {
            regNumbers.add(matcher.group());
        }
        return regNumbers;
    }

    public static Map<String, CarDetails> getCarDetailsFromOutputFile(String outputFile) {
        Map<String, CarDetails> carDetailsMap = null;
        try (FileReader fileReader = new FileReader(outputFile)) {
            CsvToBean<CarDetails> csvToBean = new CsvToBeanBuilder<CarDetails>(fileReader)
                    .withType(CarDetails.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            List<CarDetails> carDetailsList = csvToBean.parse();
            carDetailsMap = carDetailsList.stream()
                    .collect(Collectors.toMap(CarDetails::getRegistration, car -> car));

        } catch (IOException ex) {
            logger.error("Error while retrieving data from output file",ex);
        }
        return carDetailsMap;
    }
}
