package com.bcopstein;

import com.bcopstein.casosDeUso.Politicas.CalculoCustoViagem;
import com.bcopstein.casosDeUso.Politicas.CalculoCustoViagemBasico;
import com.bcopstein.casosDeUso.Politicas.CustoViagem;
import com.bcopstein.casosDeUso.Repositorios.RepositorioBairros;
import com.bcopstein.casosDeUso.Repositorios.RepositorioPassageiros;
import com.bcopstein.casosDeUso.Servicos.ServicosPassageiro;
import com.bcopstein.entidades.Bairro;
import com.bcopstein.entidades.Passageiro;
import com.bcopstein.entidades.Roteiro;
import com.bcopstein.entidades.Viagem;
import com.bcopstein.interfaces.Persistencia.RepositorioBairrosImplMem;
import com.bcopstein.interfaces.Persistencia.RepositorioPassageirosImplMem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServicosPassageiroTest {

    private ServicosPassageiro servicosPassageiro;
    private RepositorioBairrosImplMem repositorioBairros;
    private RepositorioPassageirosImplMem repositorioPassageiros;
    private CustoViagem custoViagem;

    @BeforeEach
    public void setup() {
        custoViagem = mock(CustoViagem.class);
        repositorioBairros = new RepositorioBairrosImplMem();
        repositorioPassageiros = new RepositorioPassageirosImplMem();
        CalculoCustoViagem calculoCustoViagem = mock(CalculoCustoViagem.class);
        servicosPassageiro = new ServicosPassageiro(repositorioBairros,repositorioPassageiros,calculoCustoViagem);
    }

    @Test
    public void testaCriaRoteiro(){
        Bairro inicio = repositorioBairros.recuperaPorNome("Petropolis");
        Bairro fim = repositorioBairros.recuperaPorNome("Vila Nova");

        Roteiro roteiro = new Roteiro(inicio,fim,repositorioBairros.recuperaListaBairros());
        Roteiro roteiro1 = servicosPassageiro.criaRoteiro(inicio.getNome(),fim.getNome());
        assertEquals(roteiro1, roteiro);
    }

    @Test
    public void testaCriaViagem(){
        int id = 1;
        LocalDate localDate = LocalDate.now();
        Bairro inicio = repositorioBairros.recuperaPorNome("Petropolis");
        Bairro fim = repositorioBairros.recuperaPorNome("Vila Nova");
        Roteiro roteiro = new Roteiro(inicio,fim,repositorioBairros.recuperaListaBairros());
        Passageiro passageiro = repositorioPassageiros.recuperaPorCPF("123456789");
        double valor = custoViagem.custoViagem(roteiro,passageiro);

        Viagem viagem = new Viagem(id,localDate,roteiro,passageiro,valor);
        Viagem viagem1 = servicosPassageiro.criaViagem(id,roteiro,passageiro.getCPF());

        assertEquals(viagem.toString(), viagem1.toString());
    }

}
