package com.bcopstein;

import com.bcopstein.casosDeUso.Politicas.CalculoCustoViagemRelampago;
import com.bcopstein.entidades.Bairro;
import com.bcopstein.entidades.Passageiro;
import com.bcopstein.entidades.Roteiro;
import com.bcopstein.entidades.geometria.Area;
import com.bcopstein.entidades.geometria.Ponto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class CalculoCustoViagemRelampagoTest {
    private Roteiro roteiro, roteiro2;
    private Passageiro passageiro;
    private List<Bairro> bairros;

    @BeforeEach
    public void setup(){

        bairros = new ArrayList<>();
        bairros.add(new Bairro("Vila Nova", new Area(new Ponto(1, 4), new Ponto(4, 1)), 10));
        bairros.add(new Bairro("Boqueirão", new Area(new Ponto(4, 4), new Ponto(7, 1)), 25));
        bairros.add(new Bairro("Zona Leste", new Area(new Ponto(4, 7), new Ponto(7, 4)), 10));
        bairros.add(new Bairro("Beira Mar", new Area(new Ponto(7, 10), new Ponto(10, 7)), 10));
        bairros.add(new Bairro("Noronha", new Area(new Ponto(7, 10), new Ponto(15, 7)), 15));

        roteiro = new Roteiro(bairros.get(0), bairros.get(1), bairros);
        roteiro2 = new Roteiro(bairros.get(0), bairros.get(4), bairros);

        passageiro = Passageiro.passageiroExistente("123.500.780-12", "Carlos", 350, 50);

    }

    @Test
    public void testaDescontoPontuacao(){
        CalculoCustoViagemRelampago calculoCustoViagemRelampago = new CalculoCustoViagemRelampago();
        calculoCustoViagemRelampago.definePassageiro(passageiro);
        calculoCustoViagemRelampago.defineRoteiro(roteiro);
        assertEquals(1.75, calculoCustoViagemRelampago.descontoPontuacao());
    }

    @Test
    public void testaDescontoPromocaoSazonal(){
        CalculoCustoViagemRelampago calculoCustoViagemRelampago = new CalculoCustoViagemRelampago();
        calculoCustoViagemRelampago.definePassageiro(passageiro);
        calculoCustoViagemRelampago.defineRoteiro(roteiro2);
        assertEquals(3.5, calculoCustoViagemRelampago.descontoPromocaoSazonal());
    }
}
