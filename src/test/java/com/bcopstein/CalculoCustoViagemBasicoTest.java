package com.bcopstein;

import com.bcopstein.casosDeUso.Politicas.CalculoCustoViagemBasico;
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

public class CalculoCustoViagemBasicoTest {
    private Roteiro roteiro;
    private Passageiro passageiro;
    private List<Bairro> bairros;

    @BeforeEach
    public void setup(){

        bairros = new ArrayList<>();
        bairros.add(new Bairro("Vila Nova", new Area(new Ponto(1, 4), new Ponto(4, 1)), 12));
        bairros.add(new Bairro("Boqueirão", new Area(new Ponto(4, 4), new Ponto(7, 1)), 24));

        roteiro = new Roteiro(bairros.get(0), bairros.get(1), bairros);
        passageiro = Passageiro.passageiroExistente("123.500.780-12", "Carlos", 9, 8);
    }

    @Test
    public void testaCalculoCustoBasico(){
        CalculoCustoViagemBasico calculo = new CalculoCustoViagemBasico();
        calculo.definePassageiro(passageiro);
        calculo.defineRoteiro(roteiro);
        assertEquals(36, calculo.calculoCustoBasico());
    }

}
