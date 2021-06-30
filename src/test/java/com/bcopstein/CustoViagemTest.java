package com.bcopstein;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.bcopstein.casosDeUso.Politicas.*;

import com.bcopstein.entidades.Bairro;
import com.bcopstein.entidades.Passageiro;
import com.bcopstein.entidades.Roteiro;
import com.bcopstein.entidades.geometria.Area;
import com.bcopstein.entidades.geometria.Ponto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class CustoViagemTest {
    private Roteiro roteiro, roteiro2, roteiro3;
    private Passageiro passageiro, passageiro1, passageiro2;
    private List<Bairro> bairros;

    @BeforeEach
    public void setup(){

        bairros = new ArrayList<>();
        bairros.add(new Bairro("Vila Nova", new Area(new Ponto(1, 4), new Ponto(4, 1)), 10));
        bairros.add(new Bairro("Boqueirão", new Area(new Ponto(4, 4), new Ponto(7, 1)), 25));
        bairros.add(new Bairro("Zona Leste", new Area(new Ponto(4, 7), new Ponto(7, 4)), 10));
        bairros.add(new Bairro("Caraju", new Area(new Ponto(7, 10), new Ponto(10, 7)), 10));

        roteiro = new Roteiro(bairros.get(0), bairros.get(1), bairros);
        roteiro2 = new Roteiro(bairros.get(0), bairros.get(2), bairros);
        roteiro3 = new Roteiro(bairros.get(0), bairros.get(3), bairros);

        passageiro = Passageiro.passageiroExistente("123.500.780-12", "Carlos", 10, 1);
        passageiro1 = Passageiro.passageiroExistente("976.890.700-16", "Maria", 10, 2);
        passageiro2 = Passageiro.passageiroExistente("000.000.000-99", "Julia", 350, 35);

    }

    @Test
    public void testaCustoViagem(){
        CalculoCustoViagem ccv = mock(CalculoCustoViagemBasico.class);
        when(ccv.custoViagem()).thenReturn(35.0);

        CustoViagem cv = new CustoViagem(ccv);
        double custoObs = cv.custoViagem(null, null);
        double custoEsp = 35.0;
        assertEquals(custoEsp,custoObs,0.001);
    }

    @Test
    public void testaCustoViagemIntegradoVerao(){
        CalculoCustoViagemVerao calculoCustoViagemVerao = new CalculoCustoViagemVerao();
        calculoCustoViagemVerao.definePassageiro(passageiro1);
        calculoCustoViagemVerao.defineRoteiro(roteiro);
        assertEquals(35.0,calculoCustoViagemVerao.custoViagem(),0.001);

        CalculoCustoViagemVerao calculoCustoViagemVerao2 = new CalculoCustoViagemVerao();
        calculoCustoViagemVerao2.definePassageiro(passageiro1);
        calculoCustoViagemVerao2.defineRoteiro(roteiro2);
        assertEquals(40.5,calculoCustoViagemVerao2.custoViagem(),0.001);
    }

    @Test
    public void testaCustoViagemIntegradoRelampago(){
        CalculoCustoViagemRelampago calculoCustoViagemRelampago = new CalculoCustoViagemRelampago();
        calculoCustoViagemRelampago.definePassageiro(passageiro1);
        calculoCustoViagemRelampago.defineRoteiro(roteiro2);
        assertEquals(45.0,calculoCustoViagemRelampago.custoViagem(),0.001);

        CalculoCustoViagemRelampago calculoCustoViagemRelampago2 = new CalculoCustoViagemRelampago();
        calculoCustoViagemRelampago2.definePassageiro(passageiro1);
        calculoCustoViagemRelampago2.defineRoteiro(roteiro3);
        assertEquals(52.25,calculoCustoViagemRelampago2.custoViagem(),0.001);
    }


}
