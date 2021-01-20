package rhsouthsystem.desafioreceita.model;

import com.opencsv.bean.CsvNumber;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class Conta {

    private String agencia;
    private String conta;
    @CsvNumber("0,00")
    private BigDecimal saldo;
    private String status;
    private String resultado;

}
