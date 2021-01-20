package rhsouthsystem.desafioreceita.controller;

import com.opencsv.exceptions.CsvException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import rhsouthsystem.desafioreceita.facade.SincronizacaoReceitaFacade;
import rhsouthsystem.desafioreceita.model.Conta;
import rhsouthsystem.desafioreceita.util.OpenCSVWriter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
public class SincronizacaoReceitaController {

    @Autowired
    private SincronizacaoReceitaFacade sincronizacaoReceitaFacade;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm");

    @PostMapping(path = {"/sincronizacao/receita"}, produces = "text/csv")
    public ResponseEntity<String> sincronizacaoReceita(@RequestParam("file") MultipartFile file) {
        HttpHeaders headers = new HttpHeaders();
        String filename = "contas-" + LocalDateTime.now().format(formatter) + ".csv";
        headers.setContentDispositionFormData("attachment", filename);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        List<Conta> contas;
        try {
            contas = sincronizacaoReceitaFacade.sincronizacaoReceita(file);
            return new ResponseEntity<>(
                    OpenCSVWriter.writeDataToCsvWithListObjects(contas),
                    headers,
                    HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
