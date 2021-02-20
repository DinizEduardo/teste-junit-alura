package br.com.caelum.leilao.dominio.teste;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.caelum.leilao.dominio.teste.builder.CriadorDeLeilao;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestaLances {

    Usuario steve;
    Usuario bill;

    @Before
    public void criaAvaliador() {
        this.steve = new Usuario("Steve Jobs");
        this.bill = new Usuario("Bill gates");
    }


    @Test
    public void testaSoUmLance() {
        Leilao leilao = new CriadorDeLeilao()
                .para("PS5")
                .lance(steve, 1000)
                .constroi();

        assertEquals(1, leilao.getLances().size());

        assertEquals(1000, leilao.getLances().get(0).getValor(), 0.0001);
    }

    @Test
    public void testeDeveReceberVariosValores() {
        Leilao leilao = new CriadorDeLeilao()
                .para("PS5")
                .lance(steve, 1000)
                .lance(bill, 2000)
                .constroi();

        assertEquals(2, leilao.getLances().size());

        assertEquals(1000, leilao.getLances().get(0).getValor(), 0.0001);
        assertEquals(2000, leilao.getLances().get(1).getValor(), 0.0001);
    }

    @Test
    public void naoDeveAceitarDoisLancesSeguidosDoMesmoUsuario() {
        Leilao leilao = new CriadorDeLeilao()
                .para("PS5")
                .lance(steve, 1000)
                .lance(steve, 2000)
                .constroi();

        assertEquals(1, leilao.getLances().size());
        assertEquals(1000, leilao.getLances().get(0).getValor(), 0.0001);
    }

    @Test
    public void naoDeveAceitarMaisDeCincoLancesDoMesmoUsuario() {
        Leilao leilao = new CriadorDeLeilao()
                .para("PS5")
                .lance(steve, 1000)
                .lance(bill, 2000)
                .lance(steve, 3000)
                .lance(bill, 4000)
                .lance(steve, 5000)
                .lance(bill, 6000)
                .lance(steve, 7000)
                .lance(bill, 8000)
                .lance(steve, 9000)
                .lance(bill, 10000)
                .lance(steve, 11000)
                .constroi();

        assertEquals(10, leilao.getLances().size());
        assertEquals(10000, leilao.ultimoLance().getValor(), 0.0001);

    }

}
