package br.com.caelum.leilao.dominio.teste;

import br.com.caelum.leilao.dominio.Avaliador;
import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.caelum.leilao.dominio.teste.builder.CriadorDeLeilao;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestaAvaliador {

    private Avaliador avaliador;
    private Usuario joao;
    private Usuario maria;
    private Usuario jose;


    @Before
    public void criaAvaliador() {
        this.avaliador = new Avaliador();
        this.joao = new Usuario("Joao");
        this.maria = new Usuario("Maria");
        this.jose = new Usuario("Jose");

    }

    @Test(expected = RuntimeException.class)
    public void naoDeveAvaliarLeilaoSemLances() {
        Leilao leilao = new CriadorDeLeilao()
                .para("PS5")
                .constroi();

        avaliador.avalia(leilao);
    }

    @Test
    public void validaMaiorEMenor() {
        Leilao leilao = new CriadorDeLeilao()
                .para("PS5")
                .lance(joao, 1000)
                .lance(maria, 3000)
                .lance(jose, 2000)
                .constroi();

        avaliador.avalia(leilao);

        assertEquals(3000, avaliador.getMaiorDeTodos(), 0.00001);
        assertEquals(1000, avaliador.getMenorDeTodos(), 0.00001);
    }

    @Test
    public void validaSoComUmLance() {

        Leilao leilao = new CriadorDeLeilao()
                .para("PS5")
                .lance(joao, 1000)
                .constroi();

        avaliador.avalia(leilao);

        assertEquals(1000, avaliador.getMenorDeTodos(), 0.0001);
        assertEquals(1000, avaliador.getMaiorDeTodos(), 0.0001);
    }

    @Test
    public void deveEncontrarOsTresMaioresLances() {

        Leilao leilao = new CriadorDeLeilao()
                .para("PS5")
                .lance(joao, 1000)
                .lance(maria, 3000)
                .lance(joao, 2000)
                .lance(jose, 4000)
                .constroi();

        avaliador.avalia(leilao);

        List<Lance> maiores = avaliador.getMaiores();

        assertEquals(3, maiores.size());
        assertEquals(4000, maiores.get(0).getValor(), 0.00001);
        assertEquals(3000, maiores.get(1).getValor(), 0.00001);
        assertEquals(2000, maiores.get(2).getValor(), 0.00001);
    }

}
