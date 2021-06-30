package com.bcopstein;

import com.bcopstein.entidades.Bairro;
import com.bcopstein.entidades.Passageiro;
import com.bcopstein.entidades.Roteiro;
import com.bcopstein.entidades.Viagem;
import com.bcopstein.entidades.geometria.Ponto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ViagemTest {
    private Passageiro passageiro;
    private Roteiro roteiro;
    private List<Bairro> bairros;

    @BeforeEach
    public void setup(){
        bairros = new ArrayList<>();
        bairros.add(Bairro.novoBairroRetangular("Bom Fim", new Ponto(10,40), 20, 10, 10.0));
        bairros.add(Bairro.novoBairroRetangular("Independencia", new Ponto(30,40), 20, 10, 20.0));
        bairros.add(Bairro.novoBairroRetangular("Moinhos de Vento", new Ponto(20,30), 20, 10, 30.0));
        bairros.add(Bairro.novoBairroRetangular("Auxiliadora", new Ponto(40,30), 20, 10, 20.0));
        bairros.add(Bairro.novoBairroRetangular("Boa Vista", new Ponto(40,20), 20, 10, 20.0));

        passageiro = Passageiro.novoPassageiro("09876545678", "Mathias");
        roteiro = new Roteiro(bairros.get(1), bairros.get(3), bairros);
    }

    @Test
    public void testaCriaViagem(){
        Viagem viagem = new Viagem(1, LocalDate.now(), roteiro, passageiro, 18.80);
        assertEquals(1, viagem.getId());
        assertEquals(roteiro, viagem.getRoteiro());
        assertEquals(passageiro, viagem.getPassageiro());
        assertEquals(18.80, viagem.getValorCobrado());
        assertEquals(LocalDate.now(), viagem.getDataHora());
    }
}
