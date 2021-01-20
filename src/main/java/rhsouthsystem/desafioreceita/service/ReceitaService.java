package rhsouthsystem.desafioreceita.service;

import org.springframework.stereotype.Service;
import rhsouthsystem.desafioreceita.model.Conta;

import java.math.BigDecimal;

@Service
public class ReceitaService {

    public Conta atualizarConta(String agencia, String numeroConta, BigDecimal saldo, String status) {
        Conta conta = new Conta();

        conta.setAgencia(agencia);
        conta.setConta(numeroConta);
        conta.setSaldo(saldo);
        conta.setStatus(status);
        conta.setResultado("Ok");

        return conta;
    }

}
