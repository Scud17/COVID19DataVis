package com.profHacks.AngularChartJS;

import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintWriter;
import java.util.List;

public class WriteCsvToResponse {

    private static final Logger LOGGER = LoggerFactory.getLogger(WriteCsvToResponse.class);

    public static void writeCountryAndCounts(PrintWriter writer, List<CountryAndCount> countryAndCounts) {

        try {

            ColumnPositionMappingStrategy<CountryAndCount> mapStrategy
                    = new ColumnPositionMappingStrategy<>();

            mapStrategy.setType(CountryAndCount.class);

            String[] columns = new String[]{"country", "count"};
            mapStrategy.setColumnMapping(columns);

            StatefulBeanToCsv<CountryAndCount> btcsv = new StatefulBeanToCsvBuilder<CountryAndCount>(writer)
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                    .withMappingStrategy(mapStrategy)
                    .withSeparator(',')
                    .build();

            btcsv.write(countryAndCounts);

        } catch (CsvException ex) {

            LOGGER.error("Error mapping Bean to CSV", ex);
        }
    }
}