package com.bcopstein;

import com.bcopstein.casosDeUso.Politicas.CalculoCustoViagem;
import com.bcopstein.casosDeUso.Politicas.CalculoCustoViagemBasico;
import com.bcopstein.casosDeUso.Politicas.CalculoCustoViagemVerao;
import com.bcopstein.casosDeUso.Politicas.CustoViagem;
import com.bcopstein.casosDeUso.Servicos.ServicosPassageiro;
import com.bcopstein.entidades.Bairro;
import com.bcopstein.entidades.Passageiro;
import com.bcopstein.entidades.Roteiro;
import com.bcopstein.interfaces.Persistencia.RepositorioBairrosImplMem;
import com.bcopstein.interfaces.Persistencia.RepositorioPassageirosImplMem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class CalculoCustoViagemVeraoTest {
    CalculoCustoViagemVerao calculoCustoViagemVerao;
    private RepositorioBairrosImplMem repositorioBairros;
    Roteiro roteiro;
    Roteiro roteiro1;
    Passageiro passageiro;
    Passageiro passageiro1;

    @BeforeEach
    public void setup() {
        repositorioBairros = new RepositorioBairrosImplMem();
        CalculoCustoViagemBasico calculoCustoViagemBasico = mock(CalculoCustoViagemBasico.class);
        passageiro = Passageiro.passageiroExistente("123","Julia",30,2);
        passageiro1 = Passageiro.passageiroExistente("123","Julia",1,1);

        roteiro = new Roteiro(repositorioBairros.recuperaPorNome("Ipiranga"),repositorioBairros.recuperaPorNome("Solidao"),repositorioBairros.recuperaListaBairros());
        roteiro1 = new Roteiro(repositorioBairros.recuperaPorNome("Gavea"),repositorioBairros.recuperaPorNome("Boa Vista"),repositorioBairros.recuperaListaBairros());

    }

    @Test
    public void testaDescontoPontuacao(){
        calculoCustoViagemVerao = new CalculoCustoViagemVerao();
        CalculoCustoViagemBasico calculoCustoViagemBasico = new CalculoCustoViagemBasico();

        calculoCustoViagemBasico.defineRoteiro(roteiro1);
        calculoCustoViagemVerao.definePassageiro(passageiro);
        calculoCustoViagemVerao.defineRoteiro(roteiro1);
        double desconto = calculoCustoViagemVerao.descontoPontuacao();
        double desconto1 = calculoCustoViagemBasico.calculoCustoBasico()*0.9;

        calculoCustoViagemVerao.definePassageiro(passageiro1);
        double desconto2 = calculoCustoViagemVerao.descontoPontuacao();

        assertEquals(desconto,desconto1);
        assertEquals(0.0,desconto2);

    }

    @Test
    public void testaDdescontoPromocaoSazonal(){
        calculoCustoViagemVerao = new CalculoCustoViagemVerao();
        CalculoCustoViagemBasico calculoCustoViagemBasico = new CalculoCustoViagemBasico();

        calculoCustoViagemVerao.defineRoteiro(roteiro);
        calculoCustoViagemVerao.definePassageiro(passageiro);
        calculoCustoViagemBasico.defineRoteiro(roteiro);
        double desconto = calculoCustoViagemVerao.descontoPromocaoSazonal();
        double desconto1 = calculoCustoViagemBasico.calculoCustoBasico()*0.1;

        assertEquals(desconto,desconto1);
    }

}
