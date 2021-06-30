package com.bcopstein;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.bcopstein.entidades.Bairro;
import com.bcopstein.entidades.Roteiro;
import com.bcopstein.entidades.geometria.Ponto;
import com.bcopstein.entidades.geometria.Reta;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RoteiroTest {
    private List<Bairro> bairros;

    @BeforeEach
    public void setup(){
        bairros = new ArrayList<>();
        bairros.add(Bairro.novoBairroRetangular("Bom Fim", new Ponto(10,40), 20, 10, 10.0));
        bairros.add(Bairro.novoBairroRetangular("Independencia", new Ponto(30,40), 20, 10, 20.0));
        bairros.add(Bairro.novoBairroRetangular("Moinhos de Vento", new Ponto(20,30), 20, 10, 30.0));
        bairros.add(Bairro.novoBairroRetangular("Auxiliadora", new Ponto(40,30), 20, 10, 20.0));
        bairros.add(Bairro.novoBairroRetangular("Boa Vista", new Ponto(40,20), 20, 10, 20.0));
    }
    @Test
    public void testaConstrutor(){
        Roteiro roteiro = new Roteiro(bairros.get(0), bairros.get(1), bairros);
        assertEquals(roteiro.getBairroOrigem(), bairros.get(0));
        assertEquals(roteiro.getBairroDestino(), bairros.get(1));
    }

    @Test
    public void testaRota(){
        Roteiro roteiro = new Roteiro(bairros.get(1), bairros.get(4), bairros);
        Reta rotaEsp = new Reta(new Ponto(40,35),new Ponto(50,15));
        assertEquals(rotaEsp,roteiro.getRota());
    }

    @Test
    public void testaBairrosPercorridos(){
        Roteiro roteiro = new Roteiro(bairros.get(1), bairros.get(4), bairros);
        Collection<Bairro> esperado = new ArrayList<>();
        esperado.add(bairros.get(1));
        esperado.add(bairros.get(2));
        esperado.add(bairros.get(3));
        esperado.add(bairros.get(4));
        Collection<Bairro> observado = roteiro.bairrosPercoridos();
        assertEquals(esperado, observado);
    }

    @Test
    public void testaEquals(){
        Roteiro roteiro = new Roteiro(bairros.get(0), bairros.get(1), bairros);
        Roteiro roteiroIgual = new Roteiro(bairros.get(0), bairros.get(1), bairros);
        Roteiro roteiroDiferente = new Roteiro(bairros.get(0), bairros.get(2), bairros);
        assertEquals(roteiroIgual, roteiro);
        assertNotEquals(roteiroDiferente, roteiro);
    }
}