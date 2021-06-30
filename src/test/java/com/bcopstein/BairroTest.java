package com.bcopstein;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.bcopstein.entidades.Bairro;
import com.bcopstein.entidades.geometria.Area;
import com.bcopstein.entidades.geometria.Ponto;
import com.bcopstein.entidades.geometria.Reta;
import com.bcopstein.entidades.geometria.SituacaoReta;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BairroTest {
    private Area a1, a2, a3, a4;
    private Reta reta, reta1;
    private Ponto p1, p2, p4;

    @BeforeEach
    public void setup(){
        reta = new Reta(new Ponto(10,10),new Ponto(20,10));
        reta1 = new Reta(new Ponto(10,10),new Ponto(20,20));
        a1 = mock(Area.class);
        when(a1.classifica(reta)).thenReturn(SituacaoReta.TODA_DENTRO);

        a2 = mock(Area.class);
        when(a2.classifica(reta)).thenReturn(SituacaoReta.TODA_FORA);
        when(a2.classifica(reta1)).thenReturn(SituacaoReta.INTERSECTA);
        when(a2.pontoCentral()).thenReturn(new Ponto(20,20));

         p1 = new Ponto(10,10);
         p2 = new Ponto(15,5);
         p4 = new Ponto(18,5);

         a3 = new Area(p1,p2);
         a4 = new Area(p1,p4);
    }

    @Test
    // Teste unitário: testa exclusivamente o funcionamento de bairro
    // Usa um duble de "area"
    // Verifica se "Bairro" chama adequadamente os métodos de "Area"
    public void testaPontoCentral(){
        Bairro bairro = new Bairro("Auxiliadora",a2,10);
        Ponto ptCentralEsperado = new Ponto(20,20);
        Ponto ptCentralObservado = bairro.getPontoCentral();
        assertEquals(ptCentralEsperado, ptCentralObservado);
    }

    @Test
    // Teste unitário: testa exclusivamente o funcionamento de bairro
    // Usa um duble de "area"
    // Verifica se "Bairro" chama adequadamente os métodos de "Area"
    public void testaClassificaReta(){
        Bairro bairro = new Bairro("Auxiliadora",a2,10);
        SituacaoReta sitEsp = SituacaoReta.INTERSECTA;
        SituacaoReta sitObs = bairro.getClassificacao(reta1);
        assertEquals(sitEsp, sitObs);
    }

    @Test
    public void testaNovoBairroQuadrado(){
        Bairro bairro = Bairro.novoBairroQuadrado("São José", p1, 5, 2.60);
        assertAll(
                () -> assertEquals("São José", bairro.getNome()),
                () -> assertEquals(a3, bairro.getArea()),
                () -> assertEquals(2.60, bairro.getCustoTransporte())
        );
    }

    @Test
    public void testaNovoBairroRetangular(){
        Bairro bairro = Bairro.novoBairroRetangular("Bela Vista", p2,8,5,5.89);
        assertAll(
                () -> assertEquals("Bela Vista", bairro.getNome()),
                () -> assertEquals(a4, bairro.getArea()),
                () -> assertEquals(5.89, bairro.getCustoTransporte())
        );
    }

    @Test
    public void testaAlteraCustoTransporteSucesso(){
        Bairro bairro = new Bairro("Moinhos", a1, 9.85);
        bairro.alteraCustoTransporte(12.60);

        assertEquals(12.60,bairro.getCustoTransporte());
    }

    @Test
    void testaAlteraCustoTransporteFalha() {
        Bairro bairro = new Bairro("Moinhos", a1, 9.85);
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class, () ->
                    bairro.alteraCustoTransporte(-0.60));

        assertEquals(thrown.getMessage(), "Valor invalido");
    }
}
