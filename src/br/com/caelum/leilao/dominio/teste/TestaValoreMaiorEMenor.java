package br.com.caelum.leilao.dominio.teste;

import br.com.caelum.leilao.dominio.Avaliador;
import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestaValoreMaiorEMenor {
    @Test
    public void validaMaiorEMenor() {
        Usuario joao = new Usuario("João");
        Usuario maria = new Usuario("Maria");
        Usuario rodolfo = new Usuario("Rodolfo");

        Leilao leilao = new Leilao("PS5");

        leilao.propoe(new Lance(joao, 1000));
        leilao.propoe(new Lance(maria, 3000));
        leilao.propoe(new Lance(rodolfo, 2000));

        Avaliador avaliador = new Avaliador();

        avaliador.avalia(leilao);

        double maiorEsperado = 3000;
        double menorEsperado = 1000;

        assertEquals(maiorEsperado, avaliador.getMaiorDeTodos(), 0.00001);
        assertEquals(menorEsperado, avaliador.getMenorDeTodos(), 0.00001);
    }

    @Test
    public void validaSoComUmLance() {
        Usuario joao = new Usuario("João");

        Leilao leilao = new Leilao("PS5");

        leilao.propoe(new Lance(joao, 1000));

        Avaliador avaliador = new Avaliador();

        avaliador.avalia(leilao);

        assertEquals(1000, avaliador.getMenorDeTodos(), 0.0001);
        assertEquals(1000, avaliador.getMaiorDeTodos(), 0.0001);
    }

    @Test
    public void deveEncontrarOsTresMaioresLances() {
        Usuario joao = new Usuario("João");
        Usuario maria = new Usuario("Maria");
        Usuario rodolfo = new Usuario("Rodolfo");

        Leilao leilao = new Leilao("PS5");

        leilao.propoe(new Lance(joao, 1000));
        leilao.propoe(new Lance(maria, 3000));
        leilao.propoe(new Lance(rodolfo, 2000));
        leilao.propoe(new Lance(rodolfo, 4000));

        Avaliador avaliador = new Avaliador();

        avaliador.avalia(leilao);

        List<Lance> maiores = avaliador.getMaiores();

        assertEquals(3, maiores.size());
        assertEquals(4000, maiores.get(0).getValor(), 0.00001);
        assertEquals(3000, maiores.get(1).getValor(), 0.00001);
        assertEquals(2000, maiores.get(2).getValor(), 0.00001);
    }

}
