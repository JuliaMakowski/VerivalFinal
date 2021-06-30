package com.bcopstein;

import com.bcopstein.entidades.Bairro;
import com.bcopstein.entidades.Passageiro;
import com.bcopstein.entidades.Roteiro;
import com.bcopstein.entidades.geometria.Ponto;
import com.bcopstein.entidades.geometria.Reta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class PassageiroTest {
    Passageiro p1;
    Passageiro p2;
    Passageiro p3;
    @BeforeEach
    public void setup(){
        p1 = Passageiro.novoPassageiro("123","Vitor");
        p2 = Passageiro.novoPassageiro("456","Willian");
        p3 = Passageiro.novoPassageiro("789","Luiza");
    }

    @Test
    public void testaPassageiroExistente(){
        Passageiro passageiro1 = Passageiro.passageiroExistente("123","Vitor",10,2);
        Passageiro passageiro2 = Passageiro.passageiroExistente("555","Eduarda",8,1);

        assertEquals(p1.getNome(),passageiro1.getNome());
        assertNotEquals(p2.getNome(),passageiro2.getNome());
    }

    @Test
    public void testaInfoPontuacao(){
        int pontAcu1 = p3.getPontuacaoAcumulada();
        int qtdAval1 = p3.getQtdadeAvaliacoes();
        p3.infoPontuacao(200);
        int pontAcu2 = p3.getPontuacaoAcumulada();
        int qtdAval2 = p3.getQtdadeAvaliacoes();
        assertNotEquals(pontAcu1,pontAcu2);
        assertNotEquals(qtdAval1,qtdAval2);
    }

}
