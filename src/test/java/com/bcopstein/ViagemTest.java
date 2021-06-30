package com.bcopstein;

import com.bcopstein.entidades.Passageiro;
import com.bcopstein.entidades.Roteiro;
import com.bcopstein.entidades.Viagem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;


public class ViagemTest {
    private Passageiro passageiro;
    private Roteiro roteiro;

    @BeforeEach
    public void setup(){
        roteiro = mock(Roteiro.class);
        passageiro = mock(Passageiro.class);
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

    @Test
    public void testaToString(){
        Viagem viagem = new Viagem(2, LocalDate.now(), roteiro, passageiro, 12.80);
        String txt = "Viagem [valor cobrado=" + viagem.getValorCobrado() + ", dataHora=" + viagem.getDataHora() + ", id=" + viagem.getId() +
                ", passageiro=" + viagem.getPassageiro() + ", roteiro=" + viagem.getRoteiro() + "]";
        assertEquals(viagem.toString(), txt);
    }
}

