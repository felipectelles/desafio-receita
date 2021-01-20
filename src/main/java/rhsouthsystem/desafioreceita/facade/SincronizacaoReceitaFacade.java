package rhsouthsystem.desafioreceita.facade;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import rhsouthsystem.desafioreceita.model.Conta;
import rhsouthsystem.desafioreceita.service.ReceitaService;
import rhsouthsystem.desafioreceita.util.DecimalFormatter;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class SincronizacaoReceitaFacade {

    @Autowired
    ReceitaService receitaService;

    public List<Conta> sincronizacaoReceita(MultipartFile file) throws IOException, CsvException {
        CSVParser parser = new CSVParserBuilder().withSeparator(';').build();
        List<Conta> contas = new ArrayList<>();
        byte[] bytes = file.getBytes();
        ByteArrayInputStream inputFilestream = new ByteArrayInputStream(bytes);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputFilestream)); CSVReader reader = new CSVReaderBuilder(br).withSkipLines(1).withCSVParser(parser)
                .build()) {
            String[] nextRecord;
            while ((nextRecord = reader.readNext()) != null) {
                BigDecimal saldo = DecimalFormatter.toBigDecimal(nextRecord[2]);
                contas.add(receitaService.atualizarConta(nextRecord[0], nextRecord[1], saldo, nextRecord[3]));
            }
        }
        return contas;
    }

}
