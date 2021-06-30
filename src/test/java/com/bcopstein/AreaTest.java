package com.bcopstein;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.bcopstein.entidades.geometria.Area;
import com.bcopstein.entidades.geometria.Ponto;
import com.bcopstein.entidades.geometria.Reta;
import com.bcopstein.entidades.geometria.SituacaoReta;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class AreaTest {
    private Area area;

    @BeforeEach
    public void setup(){
        area = new Area(new Ponto(10,50),new Ponto(60,10));
    }

    @Test
    public void testaPontoCentral(){
        Ponto p = area.pontoCentral();
        assertEquals(35,p.getX());
        assertEquals(30,p.getY());
    }

    @ParameterizedTest
    @CsvSource({"15,40,35,40,TODA_DENTRO",
                "15, 5,35, 5,TODA_FORA",
                "15, 5,25,10,INTERSECTA"})
    public void testaClassifica(int x1,int y1,int x2,int y2,String classificacao){
        Reta reta = new Reta(new Ponto(x1,y1), new Ponto(x2,y2));
        SituacaoReta sitEsp = switch(classificacao){
            case "TODA_DENTRO" -> SituacaoReta.TODA_DENTRO;
            case "TODA_FORA" -> SituacaoReta.TODA_FORA;
            case "INTERSECTA" -> SituacaoReta.INTERSECTA;
            default -> SituacaoReta.TODA_DENTRO;
        };
        SituacaoReta sitObs = area.classifica(reta);
        assertEquals(sitEsp, sitObs);
    }

    @ParameterizedTest
    @CsvSource({"40,55,1",
                "40,5,2",
                "70,15,4",
                "5,15,8",
                "70,55,5",
                "70,5,6",
                "5,55,9",
                "5,5,10"})
    public void testaCodificaPonto(int x1, int y1,byte expected){
        Ponto p1 = new Ponto(x1,y1);
        byte value = area.codificaPonto(p1);
        assertEquals(expected,value);
    }

    @ParameterizedTest
    @CsvSource({"10,50,60,10,true",
                "20,60,70,20,false"})
    public void testaEquals(int x1,int y1,int x2,int y2,boolean expected){
        Area area = new Area(new Ponto(x1,y1), new Ponto(x2,y2));
        boolean value = this.area.equals(area);
        assertEquals(value,expected);
    }

    @Test
    public void testaEqualsDiferentObject(){
        Ponto ponto = new Ponto(20,60);
        boolean value = this.area.equals(ponto);
        assertEquals(value,false);
    }
}