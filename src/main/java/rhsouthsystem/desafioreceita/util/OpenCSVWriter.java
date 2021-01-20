package rhsouthsystem.desafioreceita.util;

import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import rhsouthsystem.desafioreceita.model.Conta;

import java.io.StringWriter;
import java.util.List;

public class OpenCSVWriter {

    private static final char SEMICOLON = ';';

    public static String writeDataToCsvWithListObjects(List<Conta> contas) {
        String[] CSV_HEADER = {"agencia", "conta", "saldo", "status", "resultado"};
        StatefulBeanToCsv<Conta> beanToCsv = null;
        StringWriter sw = new StringWriter();
        try (
                CSVWriter csvWriter = new CSVWriter(sw,
                        SEMICOLON,
                        CSVWriter.NO_QUOTE_CHARACTER,
                        CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                        CSVWriter.DEFAULT_LINE_END);
        ) {
            csvWriter.writeNext(CSV_HEADER);
            ColumnPositionMappingStrategy<Conta> mappingStrategy =
                    new ColumnPositionMappingStrategy<Conta>();

            mappingStrategy.setType(Conta.class);
            mappingStrategy.setColumnMapping(CSV_HEADER);

            beanToCsv = new StatefulBeanToCsvBuilder<Conta>(sw)
                    .withMappingStrategy(mappingStrategy)
                    .withSeparator(SEMICOLON)
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                    .build();

            beanToCsv.write(contas);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sw.toString();
    }
}
